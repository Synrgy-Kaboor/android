package com.synrgy.kaboor.authentication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.databinding.ActivityLoginBinding

class LoginActivity : KaboorActivity<ActivityLoginBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    // TODO: For handle intent (Data, etc)
    override fun initIntent() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    // TODO: For UI
    override fun initUI() {}

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {
        super.initProcess()
    }

    // TODO: For Observer (LiveData, etc)
    override fun initObservers() {
        super.initObservers()
    }
}