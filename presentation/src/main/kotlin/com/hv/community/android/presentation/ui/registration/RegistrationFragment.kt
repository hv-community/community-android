package com.hv.community.android.presentation.ui.registration

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hv.community.android.presentation.R
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.common.util.showKeyboard
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

                is RegistrationViewEvent.Registration.Fail -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "회원가입 에러",
                        message = event.exception.message,
                        onDismiss = {
                            requireActivity().finishAffinity()
                        }
                    ).show()
                }

                is RegistrationViewEvent.Registration.Error -> {
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "앗, 에러가 발생했어요!"
                    ).show()
                }
            }
        }

        fun validation(event: RegistrationViewEvent.Validation) {
            when (event) {
                RegistrationViewEvent.Validation.InvalidNickname -> {
                    binding.inputNickname.showKeyboard()
                }

                RegistrationViewEvent.Validation.InvalidEmail -> {
                    binding.inputEmail.showKeyboard()
                }

                RegistrationViewEvent.Validation.InvalidPassword -> {
                    binding.inputPassword.showKeyboard()
                }

                RegistrationViewEvent.Validation.InvalidPasswordConfirm -> {
                    binding.inputPasswordConfirm.showKeyboard()
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
