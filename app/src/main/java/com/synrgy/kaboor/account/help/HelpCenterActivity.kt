package com.synrgy.kaboor.account.help

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.Selectable
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.kaboor.account.adapter.HelpCenterAdapter
import com.synrgy.kaboor.databinding.ActivityHelpCenterBinding
import com.synrgy.kaboor.utils.constant.ConstantDummy

class HelpCenterActivity : KaboorActivity<ActivityHelpCenterBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, HelpCenterActivity::class.java))
        }
    }

    private val helpCenterAdapter by lazy { HelpCenterAdapter(this) }

    override fun getViewBinding(): ActivityHelpCenterBinding {
        return ActivityHelpCenterBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        binding.rvHelpCenter.adapter = helpCenterAdapter
    }

    override fun initAction() = with(binding){
        appbar.setOnBackClickListener { onBackPress() }
    }

    override fun initProcess() {
        super.initProcess()

        helpCenterAdapter.setData = ConstantDummy.helpCenters().map { Selectable(it) }
    }

}