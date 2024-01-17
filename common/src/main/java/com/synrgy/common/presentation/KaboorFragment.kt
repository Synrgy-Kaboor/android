package com.synrgy.common.presentation

import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.synrgy.common.R
import com.synrgy.common.presentation.dialog.GenericBottomSheetFragment
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.emptyString
import org.koin.core.component.KoinComponent


/**
 * Created by wahid on 12/27/2023.
 * Github github.com/wahidabd.
 */


abstract class KaboorFragment<VB : ViewBinding> : BaseFragment<VB>() {

    private var loadingDialog: AlertDialog? = null

    override fun initProcess() {}
    override fun initObservers() {}

    fun showLoading() {
        loadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
            loadingDialog = null
        }

        loadingDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.layout_loading)
            .setCancelable(false)
            .create()
            .apply {
                window?.setBackgroundDrawableResource(android.R.color.transparent)
                window?.setDimAmount(0.75F)
                supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
            }

        loadingDialog?.show()
    }

    fun hideLoading() {
        loadingDialog?.hide()
        loadingDialog?.cancel()
        loadingDialog?.dismiss()
    }

    fun showAlertDialog(
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
        ).show(childFragmentManager, GenericBottomSheetFragment::class.java.name)
    }

    fun showAlertLoginDialog(
        primaryAction: (() -> Unit)?,
        secondaryAction: (() -> Unit)? = null
    ) {
        GenericBottomSheetFragment.newInstance(
            title = getString(R.string.message_login_required),
            description = getString(R.string.message_login_description),
            secondaryTextButton = getString(R.string.label_later),
            primaryTextButton = getString(R.string.label_login),
            isCancelable = false,
            onPrimaryButtonClicked =  primaryAction ,
            onSecondaryButtonClicked = secondaryAction,
        ).show(childFragmentManager, GenericBottomSheetFragment::class.java.name)
    }
}