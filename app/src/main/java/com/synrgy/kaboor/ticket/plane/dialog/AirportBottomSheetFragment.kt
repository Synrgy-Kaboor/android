package com.synrgy.kaboor.ticket.plane.dialog

import android.view.LayoutInflater
import com.synrgy.common.R
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.kaboor.databinding.FragmentAirportBottomSheetBinding


class AirportBottomSheetFragment : KaboorBottomSheet<FragmentAirportBottomSheetBinding>() {

    override val tagName: String = AirportBottomSheetFragment::class.java.name
    override fun getTitle(): String = getString(R.string.label_airport)
    override fun setCancelButtonEnable(): Boolean = true
    override fun getContentBinding(inflater: LayoutInflater): FragmentAirportBottomSheetBinding =
        FragmentAirportBottomSheetBinding.inflate(layoutInflater)

}