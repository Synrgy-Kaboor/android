package com.synrgy.common.presentation

import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.synrgy.common.R
import com.wahidabd.library.presentation.activity.BaseActivity


/**
 * Created by wahid on 12/27/2023.
 * Github github.com/wahidabd.
 */


abstract class KaboorActivity<VB: ViewBinding> : BaseActivity<VB>(){

    private var loadingDialog: AlertDialog? = null

    override fun initIntent() {}
    override fun initProcess() {}
    override fun initObservers() {}

    override fun showLoading() {
        super.showLoading()
        loadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
            loadingDialog = null
        }

        loadingDialog = MaterialAlertDialogBuilder(this)
            .setView(R.layout.layout_loading)
            .setCancelable(false)
            .create()
            .apply {
                this.window?.let { window ->
                    window.setDimAmount(0.75F)
                    window.setBackgroundDrawableResource(android.R.color.transparent)
                    this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
                }
            }

        loadingDialog?.show()
    }

    override fun hideLoading() {
        super.hideLoading()
        loadingDialog?.hide()
        loadingDialog?.cancel()
    }
}