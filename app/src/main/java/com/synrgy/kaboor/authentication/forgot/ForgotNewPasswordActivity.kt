package com.synrgy.kaboor.authentication.forgot

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.databinding.ActivityForgotNewPasswordBinding

class ForgotNewPasswordActivity : KaboorActivity<ActivityForgotNewPasswordBinding>() {

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, ForgotNewPasswordActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityForgotNewPasswordBinding {
        return ActivityForgotNewPasswordBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() = with(binding){

    }
}