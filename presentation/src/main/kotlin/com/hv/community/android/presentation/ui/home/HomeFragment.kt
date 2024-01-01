package com.hv.community.android.presentation.ui.home

import androidx.fragment.app.viewModels
import com.hv.community.android.presentation.databinding.FragmentHomeBinding
import com.hv.community.android.presentation.ui.common.base.BaseFragment
import com.hv.community.android.presentation.util.coroutine.event.eventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@HomeFragment
        }
    }

    override fun initObserver() {

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    HomeViewEvent.Confirm -> {
                        showMessageSnackBar(
                            message = "Confirm Clicked"
                        )
                    }
                }
            }
        }
    }
}
