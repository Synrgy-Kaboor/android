package com.synrgy.common.presentation

import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.synrgy.common.R
import com.synrgy.common.presentation.dialog.GenericBottomSheetFragment
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.validation.PassiveFormActivity


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


abstract class KaboorPassiveActivity<VB: ViewBinding> : PassiveFormActivity<VB>() {

    private var loadingDialog: AlertDialog? = null

    override fun initProcess() {}
    override fun initObservers() {}
    override fun onValidationFailed() {}

    override fun showLoading() {
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
        loadingDialog?.hide()
        loadingDialog?.cancel()
    }

    fun showErrorDialog(description: String){
        loadingDialog?.hide()
        loadingDialog?.cancel()

        GenericBottomSheetFragment.newInstance(
            title = getString(R.string.message_error_title),
            description = description,
            isCancelable = true
        ).show(supportFragmentManager, GenericBottomSheetFragment::class.java.name)
    }

    fun showLoginDialog(
        title: String = emptyString(),
        description: String = emptyString(),
        primaryTextButton: String? = emptyString(),
        secondaryTextButton: String? = emptyString(),
        primaryAction: (() -> Unit)? = null,
        secondaryAction: (() -> Unit)? = null
    ) {
        GenericBottomSheetFragment.newInstance(
            title = title,
            description = description,
            primaryTextButton = primaryTextButton,
            secondaryTextButton = secondaryTextButton,
            onPrimaryButtonClicked = primaryAction,
            onSecondaryButtonClicked = secondaryAction,
        ).show(supportFragmentManager, GenericBottomSheetFragment::class.java.name)
    }

}