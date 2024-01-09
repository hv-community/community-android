package com.hv.community.android.presentation.ui.community.post.write

import androidx.lifecycle.SavedStateHandle
import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.usecase.community.CreatePostUseCase
import com.hv.community.android.domain.usecase.community.GetPostDetailUseCase
import com.hv.community.android.domain.usecase.user.UserIsLoginedUseCase
import com.hv.community.android.presentation.common.base.BaseViewModel
import com.hv.community.android.presentation.common.util.coroutine.event.EventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.MutableEventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostWriteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val createPostUseCase: CreatePostUseCase,
    private val userIsLoginedUseCase: UserIsLoginedUseCase,
    private val getPostDetailUseCase: GetPostDetailUseCase,
) : BaseViewModel() {

    private val _state: MutableStateFlow<PostWriteState> = MutableStateFlow(PostWriteState.Init)
    val state: StateFlow<PostWriteState> = _state.asStateFlow()

    private val _event: MutableEventFlow<PostWriteViewEvent> = MutableEventFlow()
    val event: EventFlow<PostWriteViewEvent> = _event.asEventFlow()

    val arguments: PostWriteFragmentArgs by lazy {
        PostWriteFragmentArgs.fromSavedStateHandle(savedStateHandle)
    }

    val isLogined: Boolean by lazy {
        userIsLoginedUseCase()
    }

    val title: MutableStateFlow<String> = MutableStateFlow("")

    val content: MutableStateFlow<String> = MutableStateFlow("")

    val nickname: MutableStateFlow<String> = MutableStateFlow("")

    val password: MutableStateFlow<String> = MutableStateFlow("")

    init {
        if (arguments.postId != -1L) {
            launch {
                _state.value = PostWriteState.Loading

                getPostDetailUseCase(
                    communityId = arguments.communityId,
                    postId = arguments.postId
                ).onSuccess { post ->
                    _state.value = PostWriteState.Init

                    title.value = post.title
                    content.value = post.content
                    nickname.value = post.nickname
                }.onFailure { exception ->
                    _state.value = PostWriteState.Init

                    when (exception) {
                        is ServerException -> {
                            _event.emit(PostWriteViewEvent.LoadPost.Fail(exception))
                        }

                        else -> {
                            _event.emit(PostWriteViewEvent.LoadPost.Error(exception))
                        }
                    }
                }
            }
        }
    }

    fun onWrite() {
        launch {
            createPost(
                communityId = arguments.communityId,
                title = title.value,
                content = content.value,
                nickname = nickname.value.takeIf { isLogined }.orEmpty(),
                password = password.value.takeIf { isLogined }.orEmpty()
            )
        }
    }

    fun onBack() {
        launch {
            _event.emit(PostWriteViewEvent.GoBack)
        }
    }

    private suspend fun createPost(
        communityId: Long,
        title: String,
        content: String,
        nickname: String,
        password: String
    ) {
        _state.value = PostWriteState.Loading

        createPostUseCase(
            communityId = communityId,
            content = content,
            title = title,
            nickname = nickname,
            password = password,
        ).onSuccess { id ->
            _event.emit(PostWriteViewEvent.WritePost.Success(id))
        }.onFailure { exception ->
            when (exception) {
                is ServerException -> {
                    _event.emit(PostWriteViewEvent.WritePost.Fail(exception))
                }

                else -> {
                    _event.emit(PostWriteViewEvent.WritePost.Error(exception))
                }
            }
        }

        _state.value = PostWriteState.Init
    }
}
