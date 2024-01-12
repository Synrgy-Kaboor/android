package com.synrgy.kaboor.authentication.forgot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : KaboorActivity<ActivityForgotPasswordBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityForgotPasswordBinding =
        ActivityForgotPasswordBinding.inflate(layoutInflater)

    // TODO: For UI
    override fun initUI() {}

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}


}