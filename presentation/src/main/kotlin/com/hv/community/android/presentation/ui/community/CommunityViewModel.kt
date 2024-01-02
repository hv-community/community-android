package com.hv.community.android.presentation.ui.community

import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.domain.model.error.ServerException
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
class CommunityViewModel @Inject constructor(
    private val getCommunityListUseCase: GetCommunityListUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<CommunityState> = MutableStateFlow(CommunityState.Init)
    val state: StateFlow<CommunityState> = _state.asStateFlow()

    private val _event: MutableEventFlow<CommunityViewEvent> = MutableEventFlow()
    val event: EventFlow<CommunityViewEvent> = _event.asEventFlow()

    private val _communityList: MutableStateFlow<List<Community>> = MutableStateFlow(emptyList())
    val communityList: StateFlow<List<Community>> = _communityList.asStateFlow()

    init {
        launch {
            _state.value = CommunityState.Loading

            getCommunityListUseCase()
                .onSuccess { communityList ->
                    _state.value = CommunityState.Init
                    _communityList.value = communityList
                }.onFailure { exception ->
                    _state.value = CommunityState.Error
                    when (exception) {
                        is ServerException -> {
                            _event.emit(CommunityViewEvent.LoadCommunity.Fail(exception))
                        }

                        else -> {
                            _event.emit(CommunityViewEvent.LoadCommunity.Error(exception))
                        }
                    }
                }
        }
    }
}
