package com.synrgy.kaboor.booking.dialog

import android.view.LayoutInflater
import com.google.android.material.radiobutton.MaterialRadioButton
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.enums.DetailPassengerType
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.common.utils.ext.visibleIf
import com.synrgy.domain.booking.model.response.Passenger
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.FragementDetailPassengerBottomSheetBinding
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/26/2024.
 * Github github.com/wahidabd.
 */


class DetailPassengerBottomSheetFragment :
    KaboorBottomSheet<FragementDetailPassengerBottomSheetBinding>() {

    companion object {
        fun newInstance(
            type: DetailPassengerType,
            passenger: Passenger,
            onSave: ((Passenger) -> Unit)? = null,
        ): DetailPassengerBottomSheetFragment {
            return DetailPassengerBottomSheetFragment().apply {
                this.type = type
                this.passenger = passenger
                this.onSave = onSave
            }
        }
    }

    private var passenger: Passenger = Passenger()
    private var onSave: ((Passenger) -> Unit)? = null
    private var title: String = emptyString()

    private var type: DetailPassengerType = DetailPassengerType.PASSENGER

    override val tagName: String = ConstantTag.TAG_DETAIL_PASSENGER
    override fun getTitle(): String = getString(type.label)
    override fun setCancelButtonEnable(): Boolean = true
    override fun getContentBinding(inflater: LayoutInflater): FragementDetailPassengerBottomSheetBinding {
        return FragementDetailPassengerBottomSheetBinding.inflate(inflater)
    }

    override fun initUI() {
        super.initUI()

        with(contentBinding) {
            tvPassengerInfo.visibleIf { type != DetailPassengerType.BOOKER }
            bottomContainer.goneIf { type != DetailPassengerType.BOOKER }
            tvMessageTop.goneIf { type != DetailPassengerType.BOOKER }

            etFullName.setText(passenger.fullName.toString())
            etEmail.setText(passenger.email.toString())
            etContact.setText(passenger.phoneNumber.toString())
            setCheckDefaultValue(passenger.title.toString())
        }
    }

    override fun initAction() {
        super.initAction()

        contentBinding.btnSave.onClick {
            handleData()
        }

        contentBinding.rg.setOnCheckedChangeListener { group, id ->
            if (id != -1) {
                val radioButton = group.findViewById<MaterialRadioButton>(id)
                title = radioButton.text.toString()
            }
        }
    }

    private fun setCheckDefaultValue(defaultValue: String) = with(contentBinding) {
        val mr = rbMr.text.toString()
        val mrs = rbMrs.text.toString()
        val ms = rbMiss.text.toString()

        when (defaultValue) {
            mr -> {
                rbMr.isChecked = true
                title = mr
            }
            mrs -> {
                rbMrs.isChecked = true
                title = mrs
            }
            ms -> {
                rbMr.isChecked = true
                title = ms
            }
        }
    }

    private fun handleData() = with(contentBinding) {
        if (!validate()) {
            showToast(getString(R.string.message_not_empty))
            return
        }
        val passenger = Passenger(
            fullName = etFullName.getText(),
            email = etEmail.getText(),
            phoneNumber = etContact.getText(),
            title = title
        )
        onSave?.invoke(passenger)
        dismiss()
    }

    private fun validate(): Boolean {
        val fullName = contentBinding.etFullName.getText()
        val email = contentBinding.etEmail.getText()
        val contact = contentBinding.etContact.getText()

        when (type) {
            DetailPassengerType.PASSENGER -> if (title.isEmpty() || fullName.isEmpty()) return false
            DetailPassengerType.BOOKER -> if (title.isEmpty() || fullName.isEmpty() || email.isEmpty() || contact.isEmpty()) return false
            DetailPassengerType.PASSENGER_BOOKER -> if (title.isEmpty() || fullName.isEmpty()) return false
        }
        return true
    }

}