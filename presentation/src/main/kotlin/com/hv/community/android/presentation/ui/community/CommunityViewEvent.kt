package com.hv.community.android.presentation.ui.community

sealed interface CommunityViewEvent {
    data class Fail(val exception: Throwable) : CommunityViewEvent
    data class Error(val exception: Throwable) : CommunityViewEvent
}
