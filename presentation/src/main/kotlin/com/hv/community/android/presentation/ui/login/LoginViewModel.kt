package com.hv.community.android.presentation.ui.login

import com.hv.community.android.presentation.model.login.LoginMethod
import com.hv.community.android.presentation.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Init)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    val loginMethodList: List<LoginMethod> = listOf(
        LoginMethod.Anonymous,
        LoginMethod.Kakao,
        LoginMethod.Google,
        LoginMethod.Email
    )
}
