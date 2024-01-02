package com.hv.community.android.presentation.ui.registration.confirm

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
                    // TODO: 게시판으로 이동
                }

                RegistrationConfirmViewEvent.Registration.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "회원가입 에러",
                        message = "회원가입에 실패했습니다.",
                        onDismiss = {
                            requireActivity().finishAffinity()
                        }
                    ).show()
                }

                RegistrationConfirmViewEvent.Registration.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "회원가입 에러",
                        message = "회원가입에 실패했습니다.",
                        onDismiss = {
                            requireActivity().finishAffinity()
                        }
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

                RegistrationConfirmViewEvent.Resend.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "회원가입 에러",
                        message = "회원가입에 실패했습니다.",
                        onDismiss = {
                            requireActivity().finishAffinity()
                        }
                    ).show()
                }

                RegistrationConfirmViewEvent.Resend.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "회원가입 에러",
                        message = "회원가입에 실패했습니다.",
                        onDismiss = {
                            requireActivity().finishAffinity()
                        }
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
