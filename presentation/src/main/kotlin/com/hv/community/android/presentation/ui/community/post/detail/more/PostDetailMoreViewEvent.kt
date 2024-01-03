package com.hv.community.android.presentation.ui.community.post.detail.more

sealed interface PostDetailMoreViewEvent {
    sealed interface Load : PostDetailMoreViewEvent {
        data class Fail(val exception: Throwable) : Load
        data class Error(val exception: Throwable) : Load
    }
}
