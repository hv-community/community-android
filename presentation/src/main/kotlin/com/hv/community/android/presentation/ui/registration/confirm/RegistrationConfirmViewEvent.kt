package com.hv.community.android.presentation.ui.registration.confirm

sealed interface RegistrationConfirmViewEvent {
    sealed interface Validation : RegistrationConfirmViewEvent {
        data object InvalidVerificationCode : Validation
    }

    sealed interface Registration : RegistrationConfirmViewEvent {
        data object Success : Registration
        data object Fail : Registration
        data object Error : Registration
    }

    sealed interface Resend : RegistrationConfirmViewEvent {
        data object Prevent : Resend
        data object Success : Resend
        data object Fail : Resend
        data object Error : Resend
    }

    data object GoBack : RegistrationConfirmViewEvent
}
