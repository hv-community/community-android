package com.hv.community.android.presentation.ui.community

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentCommunityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding>(FragmentCommunityBinding::inflate) {

    override val viewModel: CommunityViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@CommunityFragment

            list.adapter = CommunityListAdapter(
                onClick = { item ->
                    findNavController().navigate(
                        CommunityFragmentDirections.actionCommunityToPost(item.id)
                    )
                },
                onLongClick = { item ->
                    showMessageSnackBar(
                        message = item.description
                    )
                }
            )
        }
    }

    override fun initObserver() {
        fun loadCommunity(event: CommunityViewEvent.LoadCommunity) {
            when (event) {
                is CommunityViewEvent.LoadCommunity.Fail -> {

                }

                is CommunityViewEvent.LoadCommunity.Error -> {

                }
            }
        }

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    is CommunityViewEvent.LoadCommunity -> {
                        loadCommunity(event)
                    }
                }
            }
        }
    }
}
