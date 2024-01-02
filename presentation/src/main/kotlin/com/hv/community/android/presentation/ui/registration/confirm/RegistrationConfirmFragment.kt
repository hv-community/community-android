package com.hv.community.android.presentation.ui.registration.confirm

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.R
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentRegistrationConfirmBinding
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationConfirmFragment :
    BaseFragment<FragmentRegistrationConfirmBinding>(FragmentRegistrationConfirmBinding::inflate) {

    override val viewModel: RegistrationConfirmViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@RegistrationConfirmFragment
        }
    }

    override fun initObserver() {
        fun registration(event: RegistrationConfirmViewEvent.Registration) {
            when (event) {
                RegistrationConfirmViewEvent.Registration.Success -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "회원가입 성공",
                        message = "회원가입을 완료했습니다.\n로그인해주세요.",
                        onDismiss = {
                            findNavController().navigate(R.id.action_registration_confirm_to_login_detail)
                        }
                    ).show()
                }

                is RegistrationConfirmViewEvent.Registration.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "회원가입 에러",
                        message = event.exception.message
                    ).show()
                }

                is RegistrationConfirmViewEvent.Registration.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        fun resend(event: RegistrationConfirmViewEvent.Resend) {
            when (event) {
                RegistrationConfirmViewEvent.Resend.Prevent -> {
                    showMessageSnackBar(
                        anchorView = binding.confirm,
                        message = "잠시 후 다시 시도해주세요."
                    )
                }

                RegistrationConfirmViewEvent.Resend.Success -> {
                    showMessageSnackBar(
                        anchorView = binding.confirm,
                        message = "인증 메일을 재전송했습니다."
                    )
                }

                is RegistrationConfirmViewEvent.Resend.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "회원가입 에러",
                        message = event.exception.message
                    ).show()
                }

                is RegistrationConfirmViewEvent.Resend.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        fun validation(event: RegistrationConfirmViewEvent.Validation) {
            when (event) {
                RegistrationConfirmViewEvent.Validation.InvalidVerificationCode -> {
                    binding.inputVerificationCode.requestFocus()
                }
            }
        }

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    is RegistrationConfirmViewEvent.Registration -> {
                        registration(event)
                    }

                    is RegistrationConfirmViewEvent.Resend -> {
                        resend(event)
                    }

                    is RegistrationConfirmViewEvent.Validation -> {
                        validation(event)
                    }

                    RegistrationConfirmViewEvent.GoBack -> {
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }
}
