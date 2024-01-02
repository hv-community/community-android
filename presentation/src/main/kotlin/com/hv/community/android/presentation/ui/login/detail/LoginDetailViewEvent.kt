package com.hv.community.android.presentation.ui.login.detail

sealed interface LoginDetailViewEvent {
    sealed interface Validation : LoginDetailViewEvent {
        data object InvalidEmail : Validation
        data object InvalidPassword : Validation
    }

    sealed interface Login : LoginDetailViewEvent {
        data object Success : Login
        data class Fail(val exception: Throwable) : Login
        data class Error(val exception: Throwable) : Login
    }

    data object GoRegistration : LoginDetailViewEvent
    data object GoRegistrationConfirm : LoginDetailViewEvent

    data object GoBack : LoginDetailViewEvent
}
