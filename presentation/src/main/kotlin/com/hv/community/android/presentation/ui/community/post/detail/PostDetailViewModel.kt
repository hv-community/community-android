package com.hv.community.android.presentation.ui.community.post.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hv.community.android.domain.model.community.PostDetail
import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.usecase.community.CheckPostPasswordUseCase
import com.hv.community.android.domain.usecase.community.CheckReplyPasswordUseCase
import com.hv.community.android.domain.usecase.community.CreateReplyUseCase
import com.hv.community.android.domain.usecase.community.DeletePostUseCase
import com.hv.community.android.domain.usecase.community.DeleteReplyUseCase
import com.hv.community.android.domain.usecase.community.GetPostDetailUseCase
import com.hv.community.android.domain.usecase.community.UpdateReplyUseCase
import com.hv.community.android.domain.usecase.user.UserIsLoginedUseCase
import com.hv.community.android.presentation.common.base.BaseViewModel
import com.hv.community.android.presentation.common.util.coroutine.event.EventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.MutableEventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
    private val userIsLoginedUseCase: UserIsLoginedUseCase
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

    private val _postDetail: MutableStateFlow<PostDetail> = MutableStateFlow(PostDetail())
    val postDetail: StateFlow<PostDetail> = _postDetail.asStateFlow()

    val nickname: StateFlow<String> = postDetail.map { postDetail ->
        "작성자 : ${postDetail.member.ifEmpty { postDetail.nickname }}"
    }.stateIn(viewModelScope, SharingStarted.Eagerly, "")

    init {
        launch {
            refresh()
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
        reply: String
    ) {
        launch {
            _state.value = PostDetailState.Updating

            createReplyUseCase(
                nickname = nickname,
                password = password,
                postId = postId,
                reply = reply
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
        reply: String,
        replyId: Long
    ) {
        launch {
            _state.value = PostDetailState.Updating
            updateReplyUseCase(
                password = password,
                reply = reply,
                replyId = replyId
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
                password = password,
                replyId = replyId
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

    private suspend fun refresh() {
        _state.value = PostDetailState.Loading

        getPostDetailUseCase(
            arguments.postId
        ).onSuccess { post ->
            _postDetail.value = post
        }.onFailure { exception ->
            when (exception) {
                is ServerException -> {
                    _event.emit(PostDetailViewEvent.LoadPost.Fail(exception))
                }

                else -> {
                    _event.emit(PostDetailViewEvent.LoadPost.Error(exception))
                }
            }
        }

        _state.value = PostDetailState.Init
    }
}
