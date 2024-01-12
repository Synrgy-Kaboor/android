package com.synrgy.kaboor.authentication

import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.kaboor.databinding.ActivityOtpBinding

class OtpActivity : KaboorActivity<ActivityOtpBinding>() {

    override fun getViewBinding(): ActivityOtpBinding =
        ActivityOtpBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
    }
}