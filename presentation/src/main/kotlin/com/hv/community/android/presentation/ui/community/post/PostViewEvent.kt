package com.hv.community.android.presentation.ui.community.post

sealed interface PostViewEvent {
    sealed interface LoadPost : PostViewEvent {
        data class Fail(val exception: Throwable) : LoadPost
        data class Error(val exception: Throwable) : LoadPost
    }

    data object GoBack : PostViewEvent
    data object GoPostWrite : PostViewEvent
}
