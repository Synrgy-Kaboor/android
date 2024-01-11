package com.synrgy.kaboor.authentication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.databinding.ActivityLoginPasswordBinding

class LoginPasswordActivity : KaboorActivity<ActivityLoginPasswordBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, LoginPasswordActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityLoginPasswordBinding =
        ActivityLoginPasswordBinding.inflate(layoutInflater)

    // TODO: For handle intent (Data, etc)
    override fun initIntent() {}

    // TODO: For UI
    override fun initUI() {}

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {}

    // TODO: For Observer (LiveData, etc)
    override fun initObservers() {}
}