package com.hv.community.android.presentation.ui.registration

sealed interface RegistrationState {
    data object Init : RegistrationState
    data object Loading : RegistrationState
}
