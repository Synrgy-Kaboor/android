package com.synrgy.kaboor.account.passport

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.toDateFormat
import com.synrgy.kaboor.databinding.ActivityAddPassportBinding

class AddPassportActivity : KaboorActivity<ActivityAddPassportBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AddPassportActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityAddPassportBinding {
        return ActivityAddPassportBinding.inflate(layoutInflater)
    }

    override fun initUI() = with(binding) {

    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        llExpiredDate.setOnClickListener { showDatePicker() }
    }

    private fun showDatePicker() = with(binding) {
        showDatePicker { date ->
            tvExpiredDate.text = date.toDateFormat()
        }
    }
}