package com.synrgy.common.presentation.dialog

import android.view.LayoutInflater
import com.synrgy.common.databinding.FragmentGenericBottomSheetBinding
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.common.utils.ext.goneIf
import com.wahidabd.library.utils.common.emptyString

class GenericBottomSheetFragment : KaboorBottomSheet<FragmentGenericBottomSheetBinding>() {

    companion object {
        fun newInstance(
            title: String = emptyString(),
            description: String = emptyString(),
            isCancelable: Boolean = false,
            primaryTextButton: String? = emptyString(),
            secondaryTextButton: String? = emptyString(),
            onPrimaryButtonClicked: (() -> Unit)? = null,
            onSecondaryButtonClicked: (() -> Unit)? = null,
        ): GenericBottomSheetFragment {
            return GenericBottomSheetFragment().apply {
                this.title = title
                this.description = description
                this.isCancelable = isCancelable
                this.primaryTextButton = primaryTextButton
                this.secondaryTextButton = secondaryTextButton
                this.onPrimaryButtonClicked = onPrimaryButtonClicked
                this.onSecondaryButtonClicked = onSecondaryButtonClicked
            }
        }
    }

    private var title: String = emptyString()
    private var description: String = emptyString()
    private var isCancelable: Boolean = false
    private var primaryTextButton: String? = emptyString()
    private var secondaryTextButton: String? = emptyString()
    private var onPrimaryButtonClicked: (() -> Unit)? = {}
    private var onSecondaryButtonClicked: (() -> Unit)? = {}

    override val tagName: String = GenericBottomSheetFragment::class.java.name
    override fun getTitle(): String = emptyString()
    override fun setCancelButtonEnable(): Boolean = false
    override fun showButton(): Boolean = false
    override fun getContentBinding(inflater: LayoutInflater): FragmentGenericBottomSheetBinding {
        return FragmentGenericBottomSheetBinding.inflate(inflater)
    }

    override fun initUI() {
        super.initUI()
        with(contentBinding) {
            tvDescription.goneIf { description.isEmpty() }
            btnSecondary.goneIf { secondaryTextButton?.isEmpty() == true }
            btnPrimary.goneIf { primaryTextButton?.isEmpty() == true }
            tvTitle.text = title
            tvDescription.text = description
            btnPrimary.text = primaryTextButton
            btnSecondary.text = secondaryTextButton
        }

        setCancelable(isCancelable)
    }

    override fun initAction() {
        super.initAction()

        with(contentBinding) {
            btnPrimary.setOnClickListener {
                onPrimaryButtonClicked?.invoke()
                dismiss()
            }
            btnSecondary.setOnClickListener {
                onSecondaryButtonClicked?.invoke()
                dismiss()
            }
        }
    }
}