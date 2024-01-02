package com.hv.community.android.presentation.ui.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.R
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentSplashBinding
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override val viewModel: SplashViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@SplashFragment
        }
    }

    override fun initObserver() {
        fun login(event: SplashViewEvent.Login) {
            when (event) {
                SplashViewEvent.Login.Success -> {
                    // TODO : 게시판으로 이동
                }

                SplashViewEvent.Login.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "로그인 에러",
                        message = "로그인에 실패했습니다.",
                        onDismiss = {
                            requireActivity().finishAffinity()
                        }
                    ).show()
                }

                SplashViewEvent.Login.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "로그인 에러",
                        message = "로그인에 실패했습니다.",
                        onDismiss = {
                            requireActivity().finishAffinity()
                        }
                    ).show()
                }
            }
        }

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    is SplashViewEvent.Login -> {
                        login(event)
                    }

                    SplashViewEvent.GoLogin -> {
                        findNavController().navigate(R.id.action_splash_to_login)
                    }
                }
            }
        }
    }
}
