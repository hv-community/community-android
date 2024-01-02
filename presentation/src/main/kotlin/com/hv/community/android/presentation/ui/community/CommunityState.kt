package com.hv.community.android.presentation.ui.community

sealed interface CommunityState {
    data object Init : CommunityState
    data object Error : CommunityState
    data object Loading : CommunityState
}
