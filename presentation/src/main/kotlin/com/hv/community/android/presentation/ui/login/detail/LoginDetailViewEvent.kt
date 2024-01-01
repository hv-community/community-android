package com.hv.community.android.presentation.ui.login.detail

sealed interface LoginDetailViewEvent {
    sealed interface Validation : LoginDetailViewEvent {
        data object InvalidEmail : Validation
        data object InvalidPassword : Validation
    }

    sealed interface Login : LoginDetailViewEvent {
        data object Success : Login
        data object Fail : Login
        data object Error : Login
    }

    data object GoRegistration : LoginDetailViewEvent

    data object GoBack : LoginDetailViewEvent
}
