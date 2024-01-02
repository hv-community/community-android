package com.hv.community.android.presentation.ui.registration.confirm

sealed interface RegistrationConfirmState {
    data object Init : RegistrationConfirmState
    data object Loading : RegistrationConfirmState
}
