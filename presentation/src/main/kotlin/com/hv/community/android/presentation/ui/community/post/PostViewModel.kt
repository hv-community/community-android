package com.hv.community.android.presentation.ui.community.post

import androidx.lifecycle.SavedStateHandle
import com.hv.community.android.domain.model.community.Post
import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.usecase.community.GetPostListUseCase
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
class PostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPostListUseCase: GetPostListUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<PostState> = MutableStateFlow(PostState.Init)
    val state: StateFlow<PostState> = _state.asStateFlow()

    private val _event: MutableEventFlow<PostViewEvent> = MutableEventFlow()
    val event: EventFlow<PostViewEvent> = _event.asEventFlow()

    private val args: PostFragmentArgs by lazy {
        PostFragmentArgs.fromSavedStateHandle(savedStateHandle)
    }

    private val _communityList: MutableStateFlow<List<Post>> = MutableStateFlow(emptyList())
    val communityList: StateFlow<List<Post>> = _communityList.asStateFlow()

    init {
        launch {
            _state.value = PostState.Loading

            getPostListUseCase(args.communityId)
                .onSuccess { communityList ->
                    _state.value = PostState.Init
                    _communityList.value = communityList
                }.onFailure { exception ->
                    _state.value = PostState.Error
                    when (exception) {
                        is ServerException -> {
                            _event.emit(PostViewEvent.LoadPost.Fail(exception))
                        }

                        else -> {
                            _event.emit(PostViewEvent.LoadPost.Error(exception))
                        }
                    }
                }
        }
    }

    fun onBack() {
        launch {
            _event.emit(PostViewEvent.GoBack)
        }
    }
}
