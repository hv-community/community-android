package com.hv.community.android.presentation.ui.community

import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.domain.usecase.community.GetCommunityListUseCase
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
class CommunityListViewModel @Inject constructor(
    private val getCommunityListUseCase: GetCommunityListUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<CommunityListState> = MutableStateFlow(CommunityListState.Init)
    val state: StateFlow<CommunityListState> = _state.asStateFlow()

    private val _event: MutableEventFlow<CommunityListViewEvent> = MutableEventFlow()
    val event: EventFlow<CommunityListViewEvent> = _event.asEventFlow()

    private val _communityList: MutableStateFlow<List<Community>> = MutableStateFlow(emptyList())
    val communityList: StateFlow<List<Community>> = _communityList.asStateFlow()

    init {
        launch {
            getCommunityListUseCase().onSuccess { communityList ->
                _communityList.value = communityList
            }
        }
    }
}
