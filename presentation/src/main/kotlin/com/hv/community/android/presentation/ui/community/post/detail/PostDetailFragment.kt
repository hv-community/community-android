package com.hv.community.android.presentation.ui.community.post.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.EventFlowSlot
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentPostDetailBinding
import com.hv.community.android.presentation.ui.community.post.detail.more.PostDetailMoreResult
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>(FragmentPostDetailBinding::inflate) {

    override val viewModel: PostDetailViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@PostDetailFragment
        }
    }

    override fun initObserver() {
        fun loadPost(event: PostDetailViewEvent.LoadPost) {
            when (event) {
                is PostDetailViewEvent.LoadPost.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "커뮤니티 에러",
                        message = event.exception.message
                    ).show()
                }

                is PostDetailViewEvent.LoadPost.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        fun deletePost(event: PostDetailViewEvent.DeletePost) {
            when (event) {
                is PostDetailViewEvent.DeletePost.Success -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "게시글이 삭제되었습니다.",
                        onDismiss = {
                            findNavController().navigateUp()
                        }
                    ).show()
                }

                is PostDetailViewEvent.DeletePost.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "커뮤니티 에러",
                        message = event.exception.message
                    ).show()
                }

                is PostDetailViewEvent.DeletePost.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        fun writeComment(event: PostDetailViewEvent.WriteComment) {
            when (event) {
                is PostDetailViewEvent.WriteComment.Success -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "댓글이 작성되었습니다."
                    ).show()
                }

                is PostDetailViewEvent.WriteComment.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "커뮤니티 에러",
                        message = event.exception.message
                    ).show()
                }

                is PostDetailViewEvent.WriteComment.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        fun editComment(event: PostDetailViewEvent.EditComment) {
            when (event) {
                is PostDetailViewEvent.EditComment.Success -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "댓글이 수정되었습니다."
                    ).show()
                }

                is PostDetailViewEvent.EditComment.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "커뮤니티 에러",
                        message = event.exception.message
                    ).show()
                }

                is PostDetailViewEvent.EditComment.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        fun deleteComment(event: PostDetailViewEvent.DeleteComment) {
            when (event) {
                is PostDetailViewEvent.DeleteComment.Success -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "댓글이 삭제되었습니다."
                    ).show()
                }

                is PostDetailViewEvent.DeleteComment.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "커뮤니티 에러",
                        message = event.exception.message
                    ).show()
                }

                is PostDetailViewEvent.DeleteComment.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    is PostDetailViewEvent.LoadPost -> {
                        loadPost(event)
                    }

                    is PostDetailViewEvent.DeletePost -> {
                        deletePost(event)
                    }

                    is PostDetailViewEvent.WriteComment -> {
                        writeComment(event)
                    }

                    is PostDetailViewEvent.EditComment -> {
                        editComment(event)
                    }

                    is PostDetailViewEvent.DeleteComment -> {
                        deleteComment(event)
                    }

                    PostDetailViewEvent.GoBack -> {
                        findNavController().navigateUp()
                    }

                    PostDetailViewEvent.ShowMoreActions -> {
                        findNavController().navigate(PostDetailFragmentDirections.actionPostDetailToPostDetailMore())
                    }
                }
            }
        }

        repeatOnStarted {
            findNavController().currentBackStackEntry
                ?.savedStateHandle
                ?.getStateFlow<EventFlowSlot<PostDetailMoreResult>>(
                    PostDetailContract.KEY_ACTION,
                    EventFlowSlot(PostDetailMoreResult.Noting)
                )?.collect { event ->
                    event.getContentIfNotHandled<PostDetailMoreResult>()?.let { action ->
                        when (action) {
                            PostDetailMoreResult.Edit -> {
                                // TODO : Edit Post
                            }

                            PostDetailMoreResult.Delete -> {
                                if (viewModel.postDetail.value.nickname.isNotEmpty()) {
                                    // TODO : 유동 비밀번호 필요
                                } else {
                                    viewModel.deletePost(
                                        postId = viewModel.arguments.postId,
                                        password = ""
                                    )
                                }
                            }

                            is PostDetailMoreResult.Fail -> {
                                AlertDialogFragmentProvider.makeAlertDialog(
                                    title = "커뮤니티 에러",
                                    message = action.exception.message
                                ).show()
                            }

                            is PostDetailMoreResult.Error -> {
                                AlertDialogFragmentProvider.makeAlertDialog(
                                    title = "앗, 에러가 발생했어요!"
                                ).show()
                            }

                            PostDetailMoreResult.Noting -> Unit
                        }
                    }
                }
        }
    }
}
