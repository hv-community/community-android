package com.hv.community.android.presentation.ui.community.post

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentPostBinding
import com.ray.rds.window.alert.AlertDialogFragmentProvider
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
                    findNavController().navigate(
                        PostFragmentDirections.actionPostToPostDetail(
                            item.id
                        )
                    )
                }
            )
        }
    }

    override fun initObserver() {
        fun loadPost(event: PostViewEvent.LoadPost) {
            when (event) {
                is PostViewEvent.LoadPost.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "커뮤니티 에러",
                        message = event.exception.message
                    ).show()
                }

                is PostViewEvent.LoadPost.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
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

                    PostViewEvent.GoPostWrite -> {
                        findNavController().navigate(
                            PostFragmentDirections.actionPostToPostWrite(
                                communityId = viewModel.arguments.communityId,
                                title = viewModel.arguments.title
                            )
                        )
                    }
                }
            }
        }
    }
}
