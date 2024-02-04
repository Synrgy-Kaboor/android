package com.synrgy.kaboor.base

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.Constant
import com.synrgy.kaboor.databinding.ActivitySplashBinding

class SplashActivity : KaboorActivity<ActivitySplashBinding>() {

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, SplashActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        with(binding) {
            logo.alpha = 0F
            logo.animate().alpha(1F).setDuration(Constant.SPLASH_TIMER).withEndAction {
                MainActivity.start(this@SplashActivity)
                finishAffinity()
            }
        }
    }

}