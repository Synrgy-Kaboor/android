package com.synrgy.kaboor.ticket.plane

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityPassengerDetailBinding

class PassengerDetailActivity : KaboorActivity<ActivityPassengerDetailBinding>() {

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, PassengerDetailActivity::class.java))
        }
    }


    override fun getViewBinding(): ActivityPassengerDetailBinding =
        ActivityPassengerDetailBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() {}

}