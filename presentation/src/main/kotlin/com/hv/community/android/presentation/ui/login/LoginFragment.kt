package com.hv.community.android.presentation.ui.login

import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.hv.community.android.presentation.databinding.FragmentLoginBinding
import com.hv.community.android.presentation.model.login.LoginMethod
import com.hv.community.android.presentation.common.base.BaseFragment
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

    private fun login(method: LoginMethod) {
        when (method) {
            else -> {
                showMessageSnackBar(
                    message = "미구현입니다."
                )
            }
        }
    }
}
