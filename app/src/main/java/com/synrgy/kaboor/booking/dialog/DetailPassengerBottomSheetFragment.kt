package com.synrgy.kaboor.booking.dialog

import android.view.LayoutInflater
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.common.utils.enums.DetailPassengerType
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.common.utils.ext.visibleIf
import com.synrgy.kaboor.databinding.FragementDetailPassengerBottomSheetBinding
import com.synrgy.common.utils.constant.ConstantTag


/**
 * Created by wahid on 1/26/2024.
 * Github github.com/wahidabd.
 */


class DetailPassengerBottomSheetFragment :
    KaboorBottomSheet<FragementDetailPassengerBottomSheetBinding>() {

    companion object {
        fun newInstance(type: DetailPassengerType): DetailPassengerBottomSheetFragment {
            return DetailPassengerBottomSheetFragment().apply {
                this.type = type
            }
        }
    }

    private var type: DetailPassengerType = DetailPassengerType.PASSENGER

    override val tagName: String = ConstantTag.TAG_DETAIL_PASSENGER
    override fun getTitle(): String = getString(type.label)
    override fun setCancelButtonEnable(): Boolean = true
    override fun showButton(): Boolean = true
    override fun getContentBinding(inflater: LayoutInflater): FragementDetailPassengerBottomSheetBinding {
        return FragementDetailPassengerBottomSheetBinding.inflate(inflater)
    }

    override fun initUI() {
        super.initUI()

        with(contentBinding) {
            tvPassengerInfo.visibleIf { type == DetailPassengerType.PASSENGER }
            bottomContainer.goneIf { type == DetailPassengerType.PASSENGER }
            tvMessageTop.goneIf { type == DetailPassengerType.PASSENGER }
        }
    }


}