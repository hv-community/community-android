package com.hv.community.android.presentation.ui.community.post.detail.more

import androidx.lifecycle.SavedStateHandle
import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.usecase.user.GetMyProfileUseCase
import com.hv.community.android.domain.usecase.user.UserIsLoginedUseCase
import com.hv.community.android.presentation.common.base.BaseViewModel
import com.hv.community.android.presentation.common.util.coroutine.event.EventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.MutableEventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.asEventFlow
import com.hv.community.android.presentation.model.community.post.detail.more.PostDetailMoreAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostDetailMoreViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userIsLoginedUseCase: UserIsLoginedUseCase,
    private val getMyProfileUseCase: GetMyProfileUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<PostDetailMoreState> = MutableStateFlow(PostDetailMoreState.Init)
    val state: StateFlow<PostDetailMoreState> = _state.asStateFlow()

    private val _event: MutableEventFlow<PostDetailMoreViewEvent> = MutableEventFlow()
    val event: EventFlow<PostDetailMoreViewEvent> = _event.asEventFlow()

    val isLogined: Boolean by lazy {
        userIsLoginedUseCase()
    }

    private val _actionList: MutableStateFlow<List<PostDetailMoreAction>> = MutableStateFlow(listOf())
    val actionList: StateFlow<List<PostDetailMoreAction>> = _actionList.asStateFlow()

    init {
        launch {
            _state.value = PostDetailMoreState.Loading
            getMyProfileUseCase().onSuccess { profile ->
                // TODO : id check
                _actionList.value = listOf(
                    PostDetailMoreAction.Edit,
                    PostDetailMoreAction.Delete
                )
            }.onFailure { exception ->
                when (exception) {
                    is ServerException -> {
                        _event.emit(PostDetailMoreViewEvent.Load.Fail(exception))
                    }

                    else -> {
                        _event.emit(PostDetailMoreViewEvent.Load.Error(exception))
                    }
                }
            }
            _state.value = PostDetailMoreState.Init
        }
    }
}
