package com.hv.community.android.presentation.ui.login.detail

sealed interface LoginDetailState {
    data object Init : LoginDetailState
    data object Loading : LoginDetailState
}
