package com.synrgy.kaboor.booking

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.kaboor.databinding.ActivityPassengerDetailBinding
import com.synrgy.kaboor.payment.PaymentMethodActivity
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.wahidabd.library.utils.exts.onClick

class PassengerDetailActivity : KaboorActivity<ActivityPassengerDetailBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PassengerDetailActivity::class.java))
        }
    }

    private val planeTicketAdapter by lazy {
        PlaneTicketAdapter(
            this,
            onClick = {}
        )
    }

    override fun getViewBinding(): ActivityPassengerDetailBinding =
        ActivityPassengerDetailBinding.inflate(layoutInflater)

    override fun initUI() {
        initPlaneTicket()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        btnOrder.onClick { PaymentMethodActivity.start(this@PassengerDetailActivity) }
    }

    override fun initProcess() {
        planeTicketAdapter.setData = ConstantDummy.roundTripPlaneTicket()
    }

    private fun initPlaneTicket() = with(binding) {
        val layoutManager =
            LinearLayoutManager(
                this@PassengerDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        rvTicket.layoutManager = layoutManager
        rvTicket.adapter = planeTicketAdapter
    }
}