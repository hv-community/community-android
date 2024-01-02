package com.hv.community.android.presentation.ui.login

import com.hv.community.android.domain.usecase.user.UserHasEmailTokenUseCase
import com.hv.community.android.presentation.common.base.BaseViewModel
import com.hv.community.android.presentation.common.util.coroutine.event.EventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.MutableEventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.asEventFlow
import com.hv.community.android.presentation.model.login.LoginMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userHasEmailTokenUseCase: UserHasEmailTokenUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Init)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _event: MutableEventFlow<LoginViewEvent> = MutableEventFlow()
    val event: EventFlow<LoginViewEvent> = _event.asEventFlow()

    val loginMethodList: List<LoginMethod> = listOf(
        LoginMethod.Anonymous,
        LoginMethod.Kakao,
        LoginMethod.Google,
        LoginMethod.Email
    )

    init {
        launch {
            val hasEmailToken = userHasEmailTokenUseCase()

            if (hasEmailToken) {
                _event.emit(LoginViewEvent.GoRegistrationConfirm)
            }
        }
    }
}
