package com.synrgy.kaboor.history

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorActivity

import com.synrgy.kaboor.R
import com.synrgy.kaboor.booking.PriceAlertActivity
import com.synrgy.kaboor.databinding.ActivityDetailHistoryBinding
import com.synrgy.kaboor.databinding.ActivityPriceAlertBinding

class DetailHistoryActivity : KaboorActivity<ActivityDetailHistoryBinding>() {

    companion object {
        fun start(
            context: Context,
        ) {
            context.startActivity(Intent(context, DetailHistoryActivity::class.java))
        }
    }
    override fun getViewBinding(): ActivityDetailHistoryBinding {
        return ActivityDetailHistoryBinding.inflate(layoutInflater)
    }

    override fun initUI() {

    }
}