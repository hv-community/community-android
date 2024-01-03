package com.hv.community.android.presentation.ui.community.post.detail

sealed interface PostDetailState {
    data object Init : PostDetailState
    data object Loading : PostDetailState
    data object Updating : PostDetailState
}
