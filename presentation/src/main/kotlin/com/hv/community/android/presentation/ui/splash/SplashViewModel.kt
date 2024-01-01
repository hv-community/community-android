package com.hv.community.android.presentation.ui.splash

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
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState.Init)
    val state: StateFlow<SplashState> = _state.asStateFlow()

    private val _event: MutableEventFlow<SplashViewEvent> = MutableEventFlow()
    val event: EventFlow<SplashViewEvent> = _event.asEventFlow()

    init {
        launch {
            // 1. SharedPreference 에 저장된 email & password 로 로그인하기
            val isSuccess = false

            // 2. 성공할 경우 메인 화면으로 이동
            if (isSuccess) {
                _event.emit(SplashViewEvent.Login.Success)
            }

            // 3. 실패할 경우 로그인 화면으로 이동
            else {
                _event.emit(SplashViewEvent.Login.Fail)
            }
        }
    }
}
