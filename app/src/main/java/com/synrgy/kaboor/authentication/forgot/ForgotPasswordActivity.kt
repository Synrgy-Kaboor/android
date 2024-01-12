package com.synrgy.kaboor.authentication.forgot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.enums.OtpType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.kaboor.authentication.OtpActivity
import com.synrgy.kaboor.databinding.ActivityForgotPasswordBinding
import com.wahidabd.library.utils.exts.onClick

class ForgotPasswordActivity : KaboorActivity<ActivityForgotPasswordBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityForgotPasswordBinding =
        ActivityForgotPasswordBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() = with(binding) {
        btnVerify.onClick {
            OtpActivity.start(
                this@ForgotPasswordActivity,
                OtpType.FORGOT_PASSWORD
            )
        }
        appbar.setOnBackClickListener { onBackPress() }
    }


}