package com.hv.community.android.presentation.ui.community.post.write

sealed interface PostWriteState {
    data object Init : PostWriteState
    data object Loading : PostWriteState
}
