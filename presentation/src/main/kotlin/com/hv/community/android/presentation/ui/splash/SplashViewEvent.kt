package com.hv.community.android.presentation.ui.splash

sealed interface SplashViewEvent {
    sealed interface Login : SplashViewEvent {
        data object Success : Login
        data object Fail : Login
        data object Error : Login
    }

    data object GoLogin : SplashViewEvent
    data object GoRegistrationConfirm : SplashViewEvent
}
