package com.hv.community.android.presentation.ui.login.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.common.util.showKeyboard
import com.hv.community.android.presentation.databinding.FragmentLoginDetailBinding
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginDetailFragment :
    BaseFragment<FragmentLoginDetailBinding>(FragmentLoginDetailBinding::inflate) {

    override val viewModel: LoginDetailViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@LoginDetailFragment
        }
    }

    override fun initObserver() {
        fun login(event: LoginDetailViewEvent.Login) {
            when (event) {
                LoginDetailViewEvent.Login.Success -> {
                    findNavController().navigate(
                        LoginDetailFragmentDirections.actionLoginDetailToCommunity()
                    )
                }

                is LoginDetailViewEvent.Login.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "로그인 에러",
                        message = event.exception.message
                    ).show()
                }

                is LoginDetailViewEvent.Login.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        fun validation(event: LoginDetailViewEvent.Validation) {
            when (event) {
                LoginDetailViewEvent.Validation.InvalidEmail -> {
                    binding.inputEmail.showKeyboard()
                }

                LoginDetailViewEvent.Validation.InvalidPassword -> {
                    binding.inputPassword.showKeyboard()
                }
            }
        }

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    is LoginDetailViewEvent.Login -> {
                        login(event)
                    }

                    is LoginDetailViewEvent.Validation -> {
                        validation(event)
                    }

                    LoginDetailViewEvent.GoRegistration -> {
                        findNavController().navigate(
                            LoginDetailFragmentDirections.actionLoginDetailToRegistration()
                        )
                    }

                    LoginDetailViewEvent.GoRegistrationConfirm -> {
                        findNavController().navigate(
                            LoginDetailFragmentDirections.actionLoginDetailToRegistrationConfirm()
                        )
                    }

                    LoginDetailViewEvent.GoBack -> {
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }
}
