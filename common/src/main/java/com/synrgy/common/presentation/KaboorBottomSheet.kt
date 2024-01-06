package com.synrgy.common.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutBaseBottomSheetBinding
import com.synrgy.common.utils.ext.visibleIf
import com.wahidabd.library.presentation.fragment.BaseBottomSheetDialogFragment
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/1/2024.
 * Github github.com/wahidabd.
 */


abstract class KaboorBottomSheet<VB : ViewBinding> :
    BaseBottomSheetDialogFragment<LayoutBaseBottomSheetBinding>() {

    override val bottomSheetTheme: Int = R.style.KaboorBottomDialog
    abstract override val tagName: String
    abstract fun getTitle(): String
    abstract fun setCancelButtonEnable(): Boolean
    open fun showButton(): Boolean = false
    protected lateinit var contentBinding: VB
    abstract fun getContentBinding(inflater: LayoutInflater): VB

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): LayoutBaseBottomSheetBinding =
        LayoutBaseBottomSheetBinding.inflate(layoutInflater, container, attachRoot)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (dialog is BottomSheetDialog) {
            dialog.behavior.apply {
                skipCollapsed = true
                state = BottomSheetBehavior.STATE_EXPANDED
                maxHeight = resources.displayMetrics.heightPixels * 6 / 7
            }
        }

        return dialog
    }

    @CallSuper
    override fun initUI() = with(binding) {
        btnClose.visibleIf { setCancelButtonEnable() }
        tvLabel.text = getTitle()
        btnSave.visibleIf { showButton() }

        // set content
        contentBinding = getContentBinding(layoutInflater)
        container.addView(contentBinding.root)
    }

    @CallSuper
    override fun initAction() {
        binding.btnClose.onClick { dismiss() }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}