package com.hv.community.android.presentation.ui.community

sealed interface CommunityListState {
    data object Init : CommunityListState
}
