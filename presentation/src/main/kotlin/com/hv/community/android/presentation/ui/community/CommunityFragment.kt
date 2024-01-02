package com.hv.community.android.presentation.ui.community

import androidx.fragment.app.viewModels
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
                    showMessageSnackBar(
                        message = "미구현입니다."
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
        repeatOnStarted {
            viewModel.event.eventObserve { event ->

            }
        }
    }
}
