package com.hv.community.android.presentation.ui.splash

import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.usecase.user.UserGetLoginDataUseCase
import com.hv.community.android.domain.usecase.user.UserHasEmailTokenUseCase
import com.hv.community.android.domain.usecase.user.UserSignInUseCase
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
class SplashViewModel @Inject constructor(
    private val userGetLoginDataUseCase: UserGetLoginDataUseCase,
    private val userSignInUseCase: UserSignInUseCase,
    private val userHasEmailTokenUseCase: UserHasEmailTokenUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState.Init)
    val state: StateFlow<SplashState> = _state.asStateFlow()

    private val _event: MutableEventFlow<SplashViewEvent> = MutableEventFlow()
    val event: EventFlow<SplashViewEvent> = _event.asEventFlow()

    init {
        launch {
            val (email, password) = userGetLoginDataUseCase()
            val hasEmailToken = userHasEmailTokenUseCase()

            when {
                email.isNotEmpty() && password.isNotEmpty() && !hasEmailToken -> {
                    userSignInUseCase(email, password)
                        .onSuccess {
                            _event.emit(SplashViewEvent.Login.Success)
                        }.onFailure { exception ->
                            if (exception is ServerException) {
                                _event.emit(SplashViewEvent.Login.Fail(exception))
                            } else {
                                _event.emit(SplashViewEvent.Login.Error(exception))
                            }
                        }
                }

                else -> {
                    _event.emit(SplashViewEvent.GoLogin)
                }
            }
        }
    }
}
