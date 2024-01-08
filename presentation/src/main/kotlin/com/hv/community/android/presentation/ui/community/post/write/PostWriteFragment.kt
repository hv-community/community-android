package com.hv.community.android.presentation.ui.community.post.write

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentPostWriteBinding
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostWriteFragment : BaseFragment<FragmentPostWriteBinding>(
    FragmentPostWriteBinding::inflate
) {

    override val viewModel: PostWriteViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@PostWriteFragment
        }
    }

    override fun initObserver() {
        fun loadPost(event: PostWriteViewEvent.LoadPost) {
            when (event) {
                is PostWriteViewEvent.LoadPost.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "커뮤니티 에러",
                        message = event.exception.message,
                        onDismiss = {
                            findNavController().navigateUp()
                        }
                    ).show()
                }

                is PostWriteViewEvent.LoadPost.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!",
                        onDismiss = {
                            findNavController().navigateUp()
                        }
                    ).show()
                }
            }
        }

        fun writePost(event: PostWriteViewEvent.WritePost) {
            when (event) {
                is PostWriteViewEvent.WritePost.Success -> {
                    findNavController().navigate(
                        PostWriteFragmentDirections.actionPostWriteToPostDetail(
                            communityId = viewModel.arguments.communityId,
                            postId = event.id,
                            title = viewModel.arguments.title
                        )
                    )
                }

                is PostWriteViewEvent.WritePost.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "커뮤니티 에러",
                        message = event.exception.message
                    ).show()
                }

                is PostWriteViewEvent.WritePost.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    is PostWriteViewEvent.LoadPost -> {
                        loadPost(event)
                    }

                    is PostWriteViewEvent.WritePost -> {
                        writePost(event)
                    }

                    PostWriteViewEvent.GoBack -> {
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }
}
