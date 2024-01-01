package com.hv.community.android.presentation.ui.splash

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.R
import com.hv.community.android.presentation.databinding.FragmentSplashBinding
import com.hv.community.android.presentation.ui.common.base.BaseFragment
import com.hv.community.android.presentation.util.coroutine.event.eventObserve
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
                    // TODO: 메인 화면으로 이동
                }

                SplashViewEvent.Login.Fail -> {
                    findNavController().navigate(R.id.action_splash_to_login)
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
                }
            }
        }
    }

    fun fragmentNavigatorExtras(
        vararg sharedElements: Pair<View, String>
    ): FragmentNavigator.Extras = FragmentNavigator.Extras.Builder().apply {
        sharedElements.forEach { (view, name) ->
            addSharedElement(view, name)
        }
    }.build()
}
