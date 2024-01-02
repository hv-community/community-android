package com.hv.community.android.presentation.ui.registration.confirm

sealed interface RegistrationConfirmViewEvent {
    sealed interface Validation : RegistrationConfirmViewEvent {
        data object InvalidVerificationCode : Validation
    }

    sealed interface Registration : RegistrationConfirmViewEvent {
        data object Success : Registration
        data class Fail(val exception: Throwable) : Registration
        data class Error(val exception: Throwable) : Registration
    }

    sealed interface Resend : RegistrationConfirmViewEvent {
        data object Prevent : Resend
        data object Success : Resend
        data class Fail(val exception: Throwable) : Resend
        data class Error(val exception: Throwable) : Resend
    }

    data object GoBack : RegistrationConfirmViewEvent
}
