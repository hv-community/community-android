package com.hv.community.android.presentation.ui

import androidx.activity.viewModels
import com.hv.community.android.presentation.databinding.ActivityMainBinding
import com.hv.community.android.presentation.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }
    }
}
