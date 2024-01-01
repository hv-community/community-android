package com.hv.community.android.presentation.ui.registration

sealed interface RegistrationViewEvent {
    sealed interface Validation : RegistrationViewEvent {
        data object InvalidNickname : Validation
        data object InvalidEmail : Validation
        data object InvalidPassword : Validation
        data object InvalidPasswordConfirm : Validation
    }

    sealed interface Registration : RegistrationViewEvent {
        data object Success : Registration
        data object Fail : Registration
        data object Error : Registration
    }

    data object GoBack : RegistrationViewEvent
}
