package com.synrgy.kaboor.booking.status

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityPaymentStatusBinding

class PaymentStatusActivity : KaboorActivity<ActivityPaymentStatusBinding>() {

    companion object {
        fun start(
            context: Context
        ) {
            context.startActivity(Intent(context, PaymentStatusActivity::class.java))
        }
    }
    override fun getViewBinding(): ActivityPaymentStatusBinding {
        return ActivityPaymentStatusBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        TODO("Not yet implemented")
    }
}