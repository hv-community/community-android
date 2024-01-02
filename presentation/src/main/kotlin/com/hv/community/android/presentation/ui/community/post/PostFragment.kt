package com.hv.community.android.presentation.ui.community.post

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>(FragmentPostBinding::inflate) {

    override val viewModel: PostViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@PostFragment

            list.adapter = PostListAdapter(
                onClick = { item ->
                    showMessageSnackBar(
                        message = "미구현입니다."
                    )
                }
            )
        }
    }

    override fun initObserver() {
        fun loadPost(event: PostViewEvent.LoadPost) {
            when (event) {
                is PostViewEvent.LoadPost.Fail -> {

                }

                is PostViewEvent.LoadPost.Error -> {

                }
            }
        }
        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    is PostViewEvent.LoadPost -> {
                        loadPost(event)
                    }

                    PostViewEvent.GoBack -> {
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }
}
