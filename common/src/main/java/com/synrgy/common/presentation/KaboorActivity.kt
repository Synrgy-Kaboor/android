package com.synrgy.common.presentation

import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.synrgy.common.R
import com.synrgy.common.presentation.dialog.GenericBottomSheetFragment
import com.synrgy.common.presentation.dialog.LottieBottomSheet
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.ext.observeNetwork
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.getCompatDrawable


/**
 * Created by wahid on 12/27/2023.
 * Github github.com/wahidabd.
 */


abstract class KaboorActivity<VB : ViewBinding> : BaseActivity<VB>() {

    private var loadingDialog: AlertDialog? = null

    override fun initAction() {}
    override fun initIntent() {
        checkConnection()
    }

    override fun initProcess() {}
    override fun initObservers() {}

    private fun checkConnection() {
        val lottieDialog = LottieBottomSheet.newInstance(
            message = getString(R.string.message_no_internet_connection),
        )

        observeNetwork { state ->
            if (!state) lottieDialog.show(supportFragmentManager, ConstantTag.TAG_LOTTIE)
            else { if (lottieDialog.isVisible) lottieDialog.dismiss() }
        }
    }

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
            .setBackground(getCompatDrawable(R.drawable.bg_rectangle_stroke_white))
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

    private fun showLottieDialog(
        message: String,
    ) {
        LottieBottomSheet.newInstance(message)
            .show(supportFragmentManager, ConstantTag.TAG_LOTTIE)
    }

    fun showErrorDialog(description: String) {
        loadingDialog?.hide()
        loadingDialog?.cancel()

        GenericBottomSheetFragment.newInstance(
            title = getString(R.string.message_error_title),
            description = description,
            isCancelable = true
        ).show(supportFragmentManager, GenericBottomSheetFragment::class.java.name)
    }

    fun showAlertDialog(
        title: String = emptyString(),
        description: String = emptyString(),
        primaryTextButton: String? = emptyString(),
        secondaryTextButton: String? = emptyString(),
        primaryAction: (() -> Unit)? = null,
        secondaryAction: (() -> Unit)? = null,
        isCancelable: Boolean = true
    ) {
        GenericBottomSheetFragment.newInstance(
            title = title,
            description = description,
            primaryTextButton = primaryTextButton,
            secondaryTextButton = secondaryTextButton,
            onPrimaryButtonClicked = primaryAction,
            onSecondaryButtonClicked = secondaryAction,
            isCancelable = isCancelable
        ).show(supportFragmentManager, GenericBottomSheetFragment::class.java.name)
    }

    fun showAlertLoginDialog(
        primaryAction: (() -> Unit)?,
        secondaryAction: (() -> Unit)? = null
    ) {
        showAlertDialog(
            title = getString(R.string.message_login_required),
            description = getString(R.string.message_login_description),
            secondaryTextButton = getString(R.string.label_later),
            primaryTextButton = getString(R.string.label_login),
            isCancelable = false,
            primaryAction = primaryAction,
            secondaryAction = secondaryAction,
        )
    }
}