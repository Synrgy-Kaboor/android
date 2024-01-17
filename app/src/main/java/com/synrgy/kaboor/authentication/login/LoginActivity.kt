package com.synrgy.kaboor.authentication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.authentication.register.RegisterActivity
import com.synrgy.kaboor.authentication.forgot.ForgotPasswordActivity
import com.synrgy.kaboor.databinding.ActivityLoginBinding
import com.wahidabd.library.utils.exts.onClick

class LoginActivity : KaboorActivity<ActivityLoginBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() = with(binding) {
        btnLogin.onClick { LoginPasswordActivity.start(this@LoginActivity, "") } // Add email from edittext
        tvForgotPassword.onClick { ForgotPasswordActivity.start(this@LoginActivity) }
        tvCreateAccount.onClick { RegisterActivity.start(this@LoginActivity) }
    }

    override fun initProcess() {
        super.initProcess()
    }

    override fun initObservers() {
        super.initObservers()
    }
}