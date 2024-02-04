package com.synrgy.kaboor.account.help

import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.Selectable
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.kaboor.account.adapter.HelpCenterAdapter
import com.synrgy.kaboor.databinding.ActivityHelpCenterBinding
import com.synrgy.kaboor.databinding.DialogHelpDeskBinding
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.wahidabd.library.utils.exts.getCompatColor
import com.wahidabd.library.utils.exts.getCompatDrawable
import com.wahidabd.library.utils.exts.onClick
import com.synrgy.common.R as comR

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

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        tvContactUs.onClick { showDialogHelpDesk() }
    }

    override fun initProcess() {
        super.initProcess()

        helpCenterAdapter.setData = ConstantDummy.helpCenters().map { Selectable(it) }
    }

    private fun showDialogHelpDesk() {
        val dialogBinding = DialogHelpDeskBinding.inflate(LayoutInflater.from(this))
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogBinding.root)
            .setBackground(getCompatDrawable(comR.drawable.bg_rectangle_stroke_white))
            .create()

        val instructionString = getString(comR.string.span_contact_us)
            .split("__", ignoreCase = true)
        dialogBinding.tvContactUs.text = (buildSpannedString {
            append(instructionString.first())
            bold {
                append(
                    instructionString[1],
                    ForegroundColorSpan(getCompatColor(comR.color.secondary01)),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            append(instructionString[2])
        })

        dialogBinding.imgClose.onClick { dialog.dismiss() }

        dialog.show()
    }
}