package com.hv.community.android.presentation.ui.invalid

import android.content.Intent
import androidx.activity.viewModels
import com.hv.community.android.presentation.common.base.BaseActivity
import com.hv.community.android.presentation.databinding.ActivityInvalidTokenBinding
import com.hv.community.android.presentation.ui.MainActivity
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvalidTokenActivity : BaseActivity<ActivityInvalidTokenBinding>(
    ActivityInvalidTokenBinding::inflate
) {

    override val viewModel: InvalidTokenViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@InvalidTokenActivity
        }

        AlertDialogFragmentProvider.makeAlertDialog(
            title = "로그인 에러",
            message = "로그인이 필요한 서비스입니다.",
            onDismiss = {
                startActivity(
                    Intent(
                        this@InvalidTokenActivity,
                        MainActivity::class.java
                    )
                )
            }
        ).apply {
            isCancelable = false
        }.show()
    }
}
