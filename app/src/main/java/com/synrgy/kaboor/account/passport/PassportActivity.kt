package com.synrgy.kaboor.account.passport

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.kaboor.account.AccountDetailActivity
import com.synrgy.kaboor.account.adapter.PassportAdapter
import com.synrgy.kaboor.databinding.ActivityPassportBinding
import com.synrgy.kaboor.utils.constant.ConstantDummy

class PassportActivity : KaboorActivity<ActivityPassportBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PassportActivity::class.java))
        }
    }

    private val passportAdapter by lazy { PassportAdapter(this) }

    override fun getViewBinding(): ActivityPassportBinding {
        return ActivityPassportBinding.inflate(layoutInflater)
    }

    override fun initUI() = with(binding) {
        rvPassport.adapter = passportAdapter
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        llAddPassport.setOnClickListener { AddPassportActivity.start(this@PassportActivity) }
    }

    override fun initProcess() {
        super.initProcess()
        passportAdapter.setData = ConstantDummy.passport()
    }

}