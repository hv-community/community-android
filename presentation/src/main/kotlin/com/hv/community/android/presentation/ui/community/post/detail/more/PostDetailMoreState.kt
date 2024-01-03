package com.hv.community.android.presentation.ui.community.post.detail.more

sealed interface PostDetailMoreState {
    data object Init : PostDetailMoreState
    data object Loading : PostDetailMoreState
}
