package com.synrgy.common.presentation.dialog

import android.view.LayoutInflater
import com.synrgy.common.databinding.LayoutNoInternetBottomSheetBinding
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.common.utils.constant.ConstantTag
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 1/28/2024.
 * Github github.com/wahidabd.
 */


class LottieBottomSheet : KaboorBottomSheet<LayoutNoInternetBottomSheetBinding>() {

    companion object {
        fun newInstance(
            message: String = emptyString(),
            isCancelable: Boolean = false,
        ): LottieBottomSheet {
            return LottieBottomSheet().apply {
                this.message = message
                this.isCancelable = isCancelable
            }
        }
    }

    private var message = emptyString()
    private var isCancelable = false

    override val tagName: String = ConstantTag.TAG_LOTTIE
    override fun getTitle(): String = emptyString()
    override fun setCancelButtonEnable(): Boolean = false
    override fun getContentBinding(inflater: LayoutInflater): LayoutNoInternetBottomSheetBinding {
        return LayoutNoInternetBottomSheetBinding.inflate(inflater)
    }

    override fun initUI() {
        super.initUI()
        setCancelable(isCancelable)

        with(contentBinding) {
            tvMessage.text = message
        }
    }
}