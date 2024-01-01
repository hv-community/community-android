package com.hv.community.android.presentation.ui.login

sealed interface LoginState {
    data object Init : LoginState
}
