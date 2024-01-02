package com.hv.community.android.presentation.ui.login

sealed interface LoginViewEvent {

    data object GoRegistrationConfirm : LoginViewEvent
}
