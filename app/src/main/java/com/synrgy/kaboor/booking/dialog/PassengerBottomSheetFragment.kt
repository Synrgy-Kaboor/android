package com.synrgy.kaboor.booking.dialog

import android.view.LayoutInflater
import com.synrgy.common.R
import com.synrgy.common.model.PassengerData
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.kaboor.databinding.FragmentPassengerBottomSheetBinding
import com.synrgy.kaboor.utils.constant.ConstantTag
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/1/2024.
 * Github github.com/wahidabd.
 */


class PassengerBottomSheetFragment : KaboorBottomSheet<FragmentPassengerBottomSheetBinding>() {


    private var passenger: PassengerData? = null
    private var onSave: (PassengerData?) -> Unit = {}

    override val tagName: String = ConstantTag.TAG_PASSENGER
    override fun getTitle(): String = getString(R.string.label_passenger)
    override fun setCancelButtonEnable(): Boolean = true
    override fun showButton(): Boolean = true
    override fun getContentBinding(inflater: LayoutInflater): FragmentPassengerBottomSheetBinding =
        FragmentPassengerBottomSheetBinding.inflate(layoutInflater)

    companion object {
        fun newInstance(
            passenger: PassengerData,
            onSave: (PassengerData?) -> Unit,
        ): PassengerBottomSheetFragment {
            return PassengerBottomSheetFragment().apply {
                this.passenger = passenger
                this.onSave = onSave
            }
        }
    }

    override fun initUI() {
        super.initUI()

        contentBinding.apply {
            kaboorPassengerClassBaby.setPassenger(passenger?.baby ?: 0)
            kaboorPassengerClassKid.setPassenger(passenger?.kid ?: 0)
            kaboorPassengerClassMature.setPassenger(passenger?.mature ?: 1)
        }
    }

    override fun initAction() {
        super.initAction()

        binding.btnSave.onClick {
            with(contentBinding) {
                passenger = PassengerData(
                    mature = kaboorPassengerClassMature.passengerCount,
                    kid = kaboorPassengerClassKid.passengerCount,
                    baby = kaboorPassengerClassBaby.passengerCount
                )
            }
            onSave.invoke(passenger)
            dismiss()
        }
    }
}