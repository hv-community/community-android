package com.hv.community.android.presentation.ui.home

import com.hv.community.android.presentation.ui.common.base.BaseViewModel
import com.hv.community.android.presentation.util.coroutine.event.EventFlow
import com.hv.community.android.presentation.util.coroutine.event.MutableEventFlow
import com.hv.community.android.presentation.util.coroutine.event.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Init)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _event: MutableEventFlow<HomeViewEvent> = MutableEventFlow()
    val event: EventFlow<HomeViewEvent> = _event.asEventFlow()

    fun onConfirm() {
        launch {
            _event.emit(HomeViewEvent.Confirm)
        }
    }
}
