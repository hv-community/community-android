package com.hv.community.android.presentation.ui.community

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.R
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentCommunityListBinding
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityListFragment : BaseFragment<FragmentCommunityListBinding>(FragmentCommunityListBinding::inflate) {

    override val viewModel: CommunityListViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@CommunityListFragment

            list.adapter = CommunityListListAdapter(
                onClick = { item ->
                    showMessageSnackBar(
                        message = "미구현입니다."
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
