package com.synrgy.kaboor.booking.dialog

import android.view.LayoutInflater
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.common.utils.enums.ProtectionType
import com.synrgy.kaboor.R
import com.synrgy.common.R as comR
import com.synrgy.kaboor.databinding.FragmentExtraProtectionBottomSheetBinding
import com.synrgy.kaboor.utils.constant.ConstantTag
import com.wahidabd.library.utils.exts.gone


class ExtraProtectionBottomSheetFragment :
    KaboorBottomSheet<FragmentExtraProtectionBottomSheetBinding>() {

    companion object {
        fun newInstance(type: ProtectionType): ExtraProtectionBottomSheetFragment =
            ExtraProtectionBottomSheetFragment().apply {
                this.protectionType = type
            }
    }

    private var protectionType: ProtectionType = ProtectionType.INSURANCE

    override val tagName: String = ConstantTag.TAG_INSURANCE
    override fun getTitle(): String = getString(R.string.title_extra_protection)
    override fun setCancelButtonEnable(): Boolean = true
    override fun getContentBinding(inflater: LayoutInflater): FragmentExtraProtectionBottomSheetBinding {
        return FragmentExtraProtectionBottomSheetBinding.inflate(inflater)
    }

    override fun initUI() {
        super.initUI()
        setupType()
    }

    private fun setupType() = with(contentBinding) {
        when(protectionType) {
            ProtectionType.INSURANCE -> {
                imgIcon.setImageResource(comR.drawable.ic_shield_cross)
                tvTitle.text = getString(R.string.title_travel_insurance)
                tvKey1.text = getString(R.string.key_insurance_1)
                tvKey2.text = getString(R.string.key_insurance_2)
                tvKey3.text = getString(R.string.key_insurance_3)
                tvKey4.text = getString(R.string.key_insurance_4)
                tvValue1.text = getString(R.string.value_insurance_1)
                tvValue2.text = getString(R.string.value_insurance_2)
                tvValue3.text = getString(R.string.value_insurance_3)
                tvValue4.text = getString(R.string.value_insurance_4)
            }
            ProtectionType.BAGGAGE -> {
                imgIcon.setImageResource(comR.drawable.ic_shopping_bag)
                tvTitle.text = getString(R.string.title_baggage_insurance)
                tab2.gone()
                tab3.gone()

                tvKey1.text = getString(R.string.key_baggage_1)
                tvKey4.text = getString(R.string.key_baggage_2)
                tvValue1.text = getString(R.string.value_baggage_1)
                tvValue4.text = getString(R.string.value_baggage_2)
            }
            ProtectionType.DELAY -> {
                imgIcon.setImageResource(comR.drawable.ic_clock)
                tvTitle.text = getString(R.string.title_delay_protection)
                tab2.gone()
                tab3.gone()

                tvKey1.text = getString(R.string.key_delay_1)
                tvKey4.text = getString(R.string.key_delay_2)
                tvValue1.text = getString(R.string.value_delay_1)
                tvValue4.text = getString(R.string.value_delay_2)
            }
        }
    }

}