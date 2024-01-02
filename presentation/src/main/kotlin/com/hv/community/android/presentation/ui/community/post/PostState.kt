package com.hv.community.android.presentation.ui.community.post

sealed interface PostState {
    data object Init : PostState
    data object Error : PostState
    data object Loading : PostState
}
