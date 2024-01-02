package com.hv.community.android.presentation.ui.login.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.R
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
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
                    // TODO : 게시판으로 이동
                }

                LoginDetailViewEvent.Login.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "로그인 에러",
                        message = "로그인에 실패했습니다.",
                        onDismiss = {
                            requireActivity().finishAffinity()
                        }
                    ).show()
                }

                LoginDetailViewEvent.Login.Error -> {
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

        fun validation(event: LoginDetailViewEvent.Validation) {
            when (event) {
                LoginDetailViewEvent.Validation.InvalidEmail -> {
                    binding.inputEmail.requestFocus()
                }

                LoginDetailViewEvent.Validation.InvalidPassword -> {
                    binding.inputPassword.requestFocus()
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
                            R.id.action_login_detail_to_registration
                        )
                    }

                    LoginDetailViewEvent.GoRegistrationConfirm -> {
                        findNavController().navigate(
                            R.id.action_login_detail_to_registration_confirm
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
