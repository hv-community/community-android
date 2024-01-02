package com.hv.community.android.presentation.ui.community

sealed interface CommunityViewEvent {
    sealed interface LoadCommunity : CommunityViewEvent {
        data class Fail(val exception: Throwable) : LoadCommunity
        data class Error(val exception: Throwable) : LoadCommunity
    }
}
