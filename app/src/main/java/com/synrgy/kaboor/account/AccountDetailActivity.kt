package com.synrgy.kaboor.account

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.toDateFormat
import com.synrgy.kaboor.databinding.ActivityAccountDetailBinding
import com.wahidabd.library.utils.exts.onClick

class AccountDetailActivity : KaboorActivity<ActivityAccountDetailBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AccountDetailActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityAccountDetailBinding =
        ActivityAccountDetailBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() = with(binding) {
        llDateOfBirth.setOnClickListener { showDatePicker() }
        btnSave.onClick { }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    private fun showDatePicker() = with(binding) {
        showDatePicker { date ->
            tvDateOfBirth.text = date.toDateFormat()
        }
    }

}