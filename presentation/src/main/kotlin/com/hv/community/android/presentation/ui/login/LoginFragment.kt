package com.hv.community.android.presentation.ui.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.hv.community.android.presentation.common.base.BaseFragment
import com.hv.community.android.presentation.common.util.coroutine.event.eventObserve
import com.hv.community.android.presentation.databinding.FragmentLoginBinding
import com.hv.community.android.presentation.model.login.LoginMethod
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override val viewModel: LoginViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@LoginFragment

            list.adapter = LoginListAdapter(
                onClick = { method ->
                    login(method)
                }
            )
            list.addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun initObserver() {
        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    LoginViewEvent.GoRegistrationConfirm -> {
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginToRegistrationConfirm()
                        )
                    }
                }
            }
        }
    }

    private fun login(method: LoginMethod) {
        when (method) {
            LoginMethod.Anonymous -> {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginToCommunity()
                )
            }

            LoginMethod.Email -> {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginToLoginDetail()
                )
            }

            else -> {
                showMessageSnackBar(
                    message = "미구현입니다."
                )
            }
        }
    }
}
