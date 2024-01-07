package com.synrgy.kaboor.ticket.plane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityPassengerDetailBinding

class PassengerDetailActivity : KaboorActivity<ActivityPassengerDetailBinding>() {

    override fun getViewBinding(): ActivityPassengerDetailBinding =
        ActivityPassengerDetailBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() {}

}