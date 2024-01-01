package com.hv.community.android.presentation.ui.home

sealed class HomeViewEvent {
    data object Confirm : HomeViewEvent()
}
