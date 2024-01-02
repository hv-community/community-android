package com.hv.community.android.presentation.ui.splash

sealed interface SplashViewEvent {
    sealed interface Login : SplashViewEvent {
        data object Success : Login
        data class Fail(val exception: Throwable) : Login
        data class Error(val exception: Throwable) : Login
    }

    data object GoLogin : SplashViewEvent
}
