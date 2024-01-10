package com.hv.community.android.presentation.ui.community.post.detail

import androidx.lifecycle.SavedStateHandle
import com.hv.community.android.domain.model.community.PostDetail
import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.model.user.UserProfile
import com.hv.community.android.domain.usecase.community.CheckPostPasswordUseCase
import com.hv.community.android.domain.usecase.community.CheckReplyPasswordUseCase
import com.hv.community.android.domain.usecase.community.CreateReplyUseCase
import com.hv.community.android.domain.usecase.community.DeletePostUseCase
import com.hv.community.android.domain.usecase.community.DeleteReplyUseCase
import com.hv.community.android.domain.usecase.community.GetPostDetailUseCase
import com.hv.community.android.domain.usecase.community.GetReplyListUseCase
import com.hv.community.android.domain.usecase.community.UpdateReplyUseCase
import com.hv.community.android.domain.usecase.user.GetMyProfileUseCase
import com.hv.community.android.domain.usecase.user.UserIsLoginedUseCase
import com.hv.community.android.presentation.common.base.BaseViewModel
import com.hv.community.android.presentation.common.util.coroutine.event.EventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.MutableEventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.asEventFlow
import com.hv.community.android.presentation.model.community.post.detail.PostDetailFooterModel
import com.hv.community.android.presentation.model.community.post.detail.PostDetailHeaderModel
import com.hv.community.android.presentation.model.community.post.detail.ReplyModel
import com.hv.community.android.presentation.model.community.post.detail.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPostDetailUseCase: GetPostDetailUseCase,
    val checkPostPasswordUseCase: CheckPostPasswordUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val createReplyUseCase: CreateReplyUseCase,
    val checkReplyPasswordUseCase: CheckReplyPasswordUseCase,
    private val updateReplyUseCase: UpdateReplyUseCase,
    private val deleteReplyUseCase: DeleteReplyUseCase,
    private val userIsLoginedUseCase: UserIsLoginedUseCase,
    private val getMyProfileUseCase: GetMyProfileUseCase,
    private val getReplyListUseCase: GetReplyListUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<PostDetailState> = MutableStateFlow(PostDetailState.Init)
    val state: StateFlow<PostDetailState> = _state.asStateFlow()

    private val _event: MutableEventFlow<PostDetailViewEvent> = MutableEventFlow()
    val event: EventFlow<PostDetailViewEvent> = _event.asEventFlow()

    val arguments: PostDetailFragmentArgs by lazy {
        PostDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)
    }

    val isLogined: Boolean by lazy {
        userIsLoginedUseCase()
    }

    private var refreshCount: Long = 0L

    private val _headerModel: MutableStateFlow<PostDetailHeaderModel> = MutableStateFlow(PostDetailHeaderModel())
    val headerModel: StateFlow<PostDetailHeaderModel> = _headerModel.asStateFlow()

    private val _commentList: MutableStateFlow<List<ReplyModel>> = MutableStateFlow(emptyList())
    val commentList: StateFlow<List<ReplyModel>> = _commentList.asStateFlow()

    private val _footerModel: MutableStateFlow<PostDetailFooterModel> = MutableStateFlow(PostDetailFooterModel())
    val footerModel: StateFlow<PostDetailFooterModel> = _footerModel.asStateFlow()

    var postDetail: PostDetail = PostDetail()
        private set

    var profile: UserProfile = UserProfile()
        private set

    init {
        launch {
            initialize()
        }

        launch {
            state.collect { state ->
                _headerModel.value = headerModel.value.copy(
                    isInit = state == PostDetailState.Init
                )
                _footerModel.value = footerModel.value.copy(
                    isInit = state == PostDetailState.Init
                )
            }
        }
    }

    fun onMore() {
        launch {
            _event.emit(PostDetailViewEvent.ShowMoreActions)
        }
    }

    fun onBack() {
        launch {
            _event.emit(PostDetailViewEvent.GoBack)
        }
    }

    fun deletePost(
        postId: Long,
        password: String
    ) {
        launch {
            _state.value = PostDetailState.Updating

            deletePostUseCase(
                communityId = arguments.communityId,
                postId = postId,
                password = password
            ).onSuccess {
                _event.emit(PostDetailViewEvent.DeletePost.Success)
            }.onFailure { exception ->
                when (exception) {
                    is ServerException -> {
                        _event.emit(PostDetailViewEvent.DeletePost.Fail(exception))
                    }

                    else -> {
                        _event.emit(PostDetailViewEvent.DeletePost.Error(exception))
                    }
                }
            }

            _state.value = PostDetailState.Init
        }
    }

    fun writeComment(
        nickname: String,
        password: String,
        postId: Long,
        content: String
    ) {
        launch {
            _state.value = PostDetailState.Updating

            createReplyUseCase(
                communityId = arguments.communityId,
                postId = postId,
                nickname = nickname,
                password = password,
                content = content
            ).onSuccess {
                refresh()
                _event.emit(PostDetailViewEvent.WriteComment.Success)
            }.onFailure { exception ->
                _state.value = PostDetailState.Init
                when (exception) {
                    is ServerException -> {
                        _event.emit(PostDetailViewEvent.WriteComment.Fail(exception))
                    }

                    else -> {
                        _event.emit(PostDetailViewEvent.WriteComment.Error(exception))
                    }
                }
            }
        }
    }

    fun editComment(
        password: String,
        content: String,
        replyId: Long
    ) {
        launch {
            _state.value = PostDetailState.Updating
            updateReplyUseCase(
                communityId = arguments.communityId,
                postId = arguments.postId,
                replyId = replyId,
                content = content,
                password = password
            ).onSuccess {
                refresh()
                _event.emit(PostDetailViewEvent.EditComment.Success)
            }.onFailure { exception ->
                _state.value = PostDetailState.Init
                when (exception) {
                    is ServerException -> {
                        _event.emit(PostDetailViewEvent.EditComment.Fail(exception))
                    }

                    else -> {
                        _event.emit(PostDetailViewEvent.EditComment.Error(exception))
                    }
                }
            }
        }
    }

    fun deleteComment(
        password: String,
        replyId: Long
    ) {
        launch {
            _state.value = PostDetailState.Updating
            deleteReplyUseCase(
                communityId = arguments.communityId,
                postId = arguments.postId,
                replyId = replyId,
                password = password
            ).onSuccess {
                refresh()
                _event.emit(PostDetailViewEvent.DeleteComment.Success)
            }.onFailure { exception ->
                _state.value = PostDetailState.Init
                when (exception) {
                    is ServerException -> {
                        _event.emit(PostDetailViewEvent.DeleteComment.Fail(exception))
                    }

                    else -> {
                        _event.emit(PostDetailViewEvent.DeleteComment.Error(exception))
                    }
                }
            }
        }
    }

    fun onCommentPasswordChanged(
        id: Long,
        password: String
    ) {
        _commentList.value = ArrayList(commentList.value).apply {
            val index = indexOfFirst { it.id == id }.takeIf { it != -1 } ?: return@apply
            set(index, get(index).copy(password = password))
        }
    }

    fun onCommentContentChanged(
        id: Long,
        fixedContent: String
    ) {
        _commentList.value = ArrayList(commentList.value).apply {
            val index = indexOfFirst { it.id == id }.takeIf { it != -1 } ?: return@apply
            set(index, get(index).copy(fixedContent = fixedContent))
        }
    }

    fun onCommentExpand(
        id: Long,
        isExpanded: Boolean
    ) {
        _commentList.value = ArrayList(commentList.value).apply {
            val index = indexOfFirst { it.id == id }.takeIf { it != -1 } ?: return@apply
            set(index, get(index).copy(isExpanded = isExpanded))
        }
    }

    fun onCommentEditing(
        id: Long,
        isEditing: Boolean
    ) {
        _commentList.value = ArrayList(commentList.value).apply {
            val index = indexOfFirst { it.id == id }.takeIf { it != -1 } ?: return@apply
            set(index, get(index).copy(isEditing = isEditing))
        }
    }

    fun onCommentDeleting(
        id: Long,
        isDeleting: Boolean
    ) {
        _commentList.value = ArrayList(commentList.value).apply {
            val index = indexOfFirst { it.id == id }.takeIf { it != -1 } ?: return@apply
            set(index, get(index).copy(isDeleting = isDeleting))
        }
    }

    fun onNewCommentNicknameChanged(nickname: String) {
        _footerModel.value = footerModel.value.copy(
            nickname = nickname
        )
    }

    fun onNewCommentPasswordChanged(password: String) {
        _footerModel.value = footerModel.value.copy(
            password = password
        )
    }

    fun onNewCommentContentChanged(content: String) {
        _footerModel.value = footerModel.value.copy(
            content = content
        )
    }

    private suspend fun initialize() {
        if (isLogined) {
            _state.value = PostDetailState.Loading

            getMyProfileUseCase().onSuccess { profile ->
                _state.value = PostDetailState.Init

                this.profile = profile
                refresh()
            }.onFailure { exception ->
                _state.value = PostDetailState.Init

                when (exception) {
                    is ServerException -> {
                        _event.emit(PostDetailViewEvent.LoadPost.Fail(exception))
                    }

                    else -> {
                        _event.emit(PostDetailViewEvent.LoadPost.Error(exception))
                    }
                }
            }
        } else {
            refresh()
        }
    }

    private suspend fun refresh() {
        _state.value = PostDetailState.Loading

        withContext(Dispatchers.IO) {
            val postDetail = async {
                getPostDetailUseCase(
                    communityId = arguments.communityId,
                    postId = arguments.postId
                )
            }
            val replyList = async {
                getReplyListUseCase(
                    communityId = arguments.communityId,
                    postId = arguments.postId
                )
            }
            runCatching {
                postDetail.await().getOrThrow() to replyList.await().getOrThrow()
            }
        }.onSuccess { (postDetail, replyList) ->
            _state.value = PostDetailState.Init

            this.postDetail = postDetail

            refreshCount++
            _headerModel.value = PostDetailHeaderModel(
                id = refreshCount,
                title = postDetail.title,
                nickname = postDetail.nickname,
                isInit = state.value == PostDetailState.Init,
                content = postDetail.content
            )
            _commentList.value = replyList.map { reply ->
                reply.toUiModel(
                    isExpandEnabled = reply.memberId == -1L || profile.id == reply.memberId
                )
            }
            _footerModel.value = PostDetailFooterModel(
                id = refreshCount,
                isLogined = isLogined,
                nickname = profile.nickname,
                isInit = state.value == PostDetailState.Init
            )
        }.onFailure { exception ->
            _state.value = PostDetailState.Init

            when (exception) {
                is ServerException -> {
                    _event.emit(PostDetailViewEvent.LoadPost.Fail(exception))
                }

                else -> {
                    _event.emit(PostDetailViewEvent.LoadPost.Error(exception))
                }
            }
        }
    }
}
