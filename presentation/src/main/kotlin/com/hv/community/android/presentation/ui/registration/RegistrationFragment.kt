package com.hv.community.android.presentation.ui.registration

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.R
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentRegistrationBinding
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    override val viewModel: RegistrationViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@RegistrationFragment
        }
    }

    override fun initObserver() {
        fun registration(event: RegistrationViewEvent.Registration) {
            when (event) {
                RegistrationViewEvent.Registration.Success -> {
                    findNavController().navigate(
                        R.id.action_registration_to_registration_confirm
                    )
                }

                RegistrationViewEvent.Registration.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "회원가입 에러",
                        message = "회원가입에 실패했습니다.",
                        onDismiss = {
                            requireActivity().finishAffinity()
                        }
                    ).show()
                }

                RegistrationViewEvent.Registration.Error -> {
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

        fun validation(event: RegistrationViewEvent.Validation) {
            when (event) {
                RegistrationViewEvent.Validation.InvalidNickname -> {
                    binding.inputNickname.requestFocus()
                }

                RegistrationViewEvent.Validation.InvalidEmail -> {
                    binding.inputEmail.requestFocus()
                }

                RegistrationViewEvent.Validation.InvalidPassword -> {
                    binding.inputPassword.requestFocus()
                }

                RegistrationViewEvent.Validation.InvalidPasswordConfirm -> {
                    binding.inputPasswordConfirm.requestFocus()
                }
            }
        }

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    is RegistrationViewEvent.Registration -> {
                        registration(event)
                    }

                    is RegistrationViewEvent.Validation -> {
                        validation(event)
                    }

                    RegistrationViewEvent.GoBack -> {
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }
}
