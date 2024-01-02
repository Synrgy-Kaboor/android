package com.synrgy.kaboor.ticket

import android.view.LayoutInflater
import com.synrgy.common.R
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.kaboor.databinding.FragmentPassengerBottomSheetBinding


/**
 * Created by wahid on 1/1/2024.
 * Github github.com/wahidabd.
 */


class PassengerBottomSheetFragment : KaboorBottomSheet<FragmentPassengerBottomSheetBinding>() {


    override val tagName: String = PassengerBottomSheetFragment::class.java.name
    override fun getTitle(): String = getString(R.string.label_passenger)
    override fun setCancelButtonEnable(): Boolean = true
    override fun showButton(): Boolean = true
    override fun getContentBinding(inflater: LayoutInflater): FragmentPassengerBottomSheetBinding =
        FragmentPassengerBottomSheetBinding.inflate(layoutInflater)

    companion object {
        fun newInstance(): PassengerBottomSheetFragment {
            return PassengerBottomSheetFragment()
        }
    }

    override fun initUI() {
        super.initUI()
    }

    override fun initAction() {
        super.initAction()

    }
}