package com.hv.community.android.presentation.ui.community.post.detail.more

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.hv.community.android.presentation.common.base.BaseDialogFragment
import com.hv.community.android.presentation.common.util.coroutine.event.EventFlowSlot
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.DialogPostDetailMoreBinding
import com.hv.community.android.presentation.model.community.post.detail.more.PostDetailMoreAction
import com.hv.community.android.presentation.ui.community.post.detail.PostDetailContract
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class PostDetailMoreDialogFragment : BaseDialogFragment<DialogPostDetailMoreBinding>(
    DialogPostDetailMoreBinding::inflate
) {
    override val viewModel: PostDetailMoreViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            list.adapter = PostDetailMoreListAdapter(
                onClick = { action ->
                    when (action) {
                        is PostDetailMoreAction.Edit -> {
                            notifyAction(PostDetailMoreResult.Edit)
                        }

                        is PostDetailMoreAction.Delete -> {
                            notifyAction(PostDetailMoreResult.Delete)
                        }
                    }
                }
            )
            list.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun initObserver() {
        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    is PostDetailMoreViewEvent.Load.Fail -> {
                        notifyAction(PostDetailMoreResult.Fail(event.exception))
                    }

                    is PostDetailMoreViewEvent.Load.Error -> {
                        notifyAction(PostDetailMoreResult.Error(event.exception))
                    }
                }
            }
        }
    }

    private fun notifyAction(action: PostDetailMoreResult) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            PostDetailContract.KEY_ACTION,
            EventFlowSlot(action)
        )
    }
}
