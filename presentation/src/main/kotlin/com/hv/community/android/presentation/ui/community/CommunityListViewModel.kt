package com.hv.community.android.presentation.ui.community

import com.hv.community.android.domain.model.community.CommunityItem
import com.hv.community.android.presentation.common.base.BaseViewModel
import com.hv.community.android.presentation.common.util.coroutine.event.EventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.MutableEventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CommunityListViewModel @Inject constructor() : BaseViewModel() {

    private val _state: MutableStateFlow<CommunityListState> = MutableStateFlow(CommunityListState.Init)
    val state: StateFlow<CommunityListState> = _state.asStateFlow()

    private val _event: MutableEventFlow<CommunityListViewEvent> = MutableEventFlow()
    val event: EventFlow<CommunityListViewEvent> = _event.asEventFlow()

    val communityList: List<CommunityItem> = listOf(
        CommunityItem(
            id = 1L,
            title = "블루 아카이브 채널",
            thumbnail = "https://play-lh.googleusercontent.com/xUQ8wVNuE-ZdubxWjs9K4MXTAFRDp1hpcpB8ozLUV5MK0HKzrWr12r4lJbLgiWDpDPo=w240-h480-rw"
        ),
        CommunityItem(
            id = 2L,
            title = "트릭컬 RE:VIVE 채널",
            thumbnail = "https://cdn-www.bluestacks.com/bs-images/999ff719bc4ab24360e500d4564d5f2e.png"
        )
    )
}
