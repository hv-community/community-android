package com.hv.community.android.presentation.ui.splash

import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.usecase.user.UserGetLoginDataUseCase
import com.hv.community.android.domain.usecase.user.UserSignInUseCase
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
class SplashViewModel @Inject constructor(
    private val userGetLoginDataUseCase: UserGetLoginDataUseCase,
    private val userSignInUseCase: UserSignInUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState.Init)
    val state: StateFlow<SplashState> = _state.asStateFlow()

    private val _event: MutableEventFlow<SplashViewEvent> = MutableEventFlow()
    val event: EventFlow<SplashViewEvent> = _event.asEventFlow()

    init {
        launch {
            val (email, password) = userGetLoginDataUseCase()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                userSignInUseCase(email, password)
                    .onSuccess {
                        _event.emit(SplashViewEvent.Login.Success)
                    }.onFailure {
                        if (it is ServerException) {
                            // ID / PW 불일치
                            _event.emit(SplashViewEvent.Login.Fail)
                        } else {
                            // 서버 에러
                            _event.emit(SplashViewEvent.Login.Error)
                        }
                    }
            } else {
                _event.emit(SplashViewEvent.Login.Fail)
            }
        }
    }
}
