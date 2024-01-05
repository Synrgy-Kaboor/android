package com.synrgy.kaboor.ticket.plane.dialog

import android.view.LayoutInflater
import com.synrgy.common.R
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.kaboor.databinding.FragmentFlightClassBottomSheetBinding


/**
 * Created by wahid on 1/3/2024.
 * Github github.com/wahidabd.
 */


class FlightClassBottomSheetFragment : KaboorBottomSheet<FragmentFlightClassBottomSheetBinding>() {

    override val tagName: String = FlightClassBottomSheetFragment::class.java.name
    override fun getTitle(): String = getString(R.string.label_class)
    override fun setCancelButtonEnable(): Boolean = true
    override fun showButton(): Boolean = true

    override fun getContentBinding(inflater: LayoutInflater): FragmentFlightClassBottomSheetBinding =
        FragmentFlightClassBottomSheetBinding.inflate(layoutInflater)
}