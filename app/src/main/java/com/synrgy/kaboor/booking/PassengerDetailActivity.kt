package com.synrgy.kaboor.booking

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.enums.DetailPassengerType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.booking.dialog.DetailPassengerBottomSheetFragment
import com.synrgy.kaboor.databinding.ActivityPassengerDetailBinding
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.synrgy.common.utils.constant.ConstantTag
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
        cvBookerDetail.onClick { showDetailInfo(DetailPassengerType.BOOKER) }
        ivEdit.onClick {
            if (!btnSwitch.isChecked) showDetailInfo(DetailPassengerType.PASSENGER)
        }
        btnOrder.onClick { ExtraProtectionActivity.start(this@PassengerDetailActivity) }
    }

    override fun initProcess() {
        planeTicketAdapter.setData = ConstantDummy.roundTripPlaneFlight()
    }

    private fun showDetailInfo(type: DetailPassengerType) {
        DetailPassengerBottomSheetFragment.newInstance(type)
            .show(supportFragmentManager, ConstantTag.TAG_DETAIL_PASSENGER)
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