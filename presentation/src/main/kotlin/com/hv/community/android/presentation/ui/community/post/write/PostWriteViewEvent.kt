package com.hv.community.android.presentation.ui.community.post.write

sealed interface PostWriteViewEvent {
    sealed interface WritePost : PostWriteViewEvent {
        data class Success(val id: Long) : WritePost
        data class Fail(val exception: Throwable) : WritePost
        data class Error(val exception: Throwable) : WritePost
    }

    sealed interface LoadPost : PostWriteViewEvent {
        data class Fail(val exception: Throwable) : LoadPost
        data class Error(val exception: Throwable) : LoadPost
    }

    data object GoBack : PostWriteViewEvent
}
