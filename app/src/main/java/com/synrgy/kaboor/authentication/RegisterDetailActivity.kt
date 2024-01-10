package com.synrgy.kaboor.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityDetailRegisterBinding
import com.synrgy.kaboor.databinding.ActivityForgotPasswordBinding

class RegisterDetailActivity : KaboorActivity<ActivityDetailRegisterBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityDetailRegisterBinding =
        ActivityDetailRegisterBinding.inflate(layoutInflater)

    // TODO: For handle intent (Data, etc)
    override fun initIntent() {}

    // TODO: For UI
    override fun initUI() {
        val email = intent.getStringExtra("email")
        binding.etEmail.text.append(email)
    }

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {}

    // TODO: For Observer (LiveData, etc)
    override fun initObservers() {}

}