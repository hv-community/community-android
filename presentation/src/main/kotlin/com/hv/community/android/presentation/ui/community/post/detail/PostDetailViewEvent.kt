package com.hv.community.android.presentation.ui.community.post.detail

sealed interface PostDetailViewEvent {
    sealed interface LoadPost : PostDetailViewEvent {
        data class Fail(val exception: Throwable) : LoadPost
        data class Error(val exception: Throwable) : LoadPost
    }

    sealed interface DeletePost : PostDetailViewEvent {
        data object Success : DeletePost
        data class Fail(val exception: Throwable) : DeletePost
        data class Error(val exception: Throwable) : DeletePost
    }

    sealed interface WriteComment : PostDetailViewEvent {
        data object Success : WriteComment
        data class Fail(val exception: Throwable) : WriteComment
        data class Error(val exception: Throwable) : WriteComment
    }

    sealed interface EditComment : PostDetailViewEvent {
        data object Success : EditComment
        data class Fail(val exception: Throwable) : EditComment
        data class Error(val exception: Throwable) : EditComment
    }

    sealed interface DeleteComment : PostDetailViewEvent {
        data object Success : DeleteComment
        data class Fail(val exception: Throwable) : DeleteComment
        data class Error(val exception: Throwable) : DeleteComment
    }

    data object GoBack : PostDetailViewEvent
    data object ShowMoreActions : PostDetailViewEvent
}
