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
            onSave: ((Passenger) -> Unit)? = null,
        ): DetailPassengerBottomSheetFragment {
            return DetailPassengerBottomSheetFragment().apply {
                this.type = type
                this.onSave = onSave
            }
        }
    }

    private var onSave: ((Passenger) -> Unit)? = null
    private var title: String = ""

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

    private fun handleData() = with(contentBinding) {
        val fullName = etFullName.getText()
        val email = etEmail.getText()
        val phone = etContact.getText()

        if ((type == DetailPassengerType.PASSENGER || type == DetailPassengerType.PASSENGER_BOOKER) && title.isEmpty() && fullName.isEmpty()) {
            showToast(getString(R.string.message_not_empty))
            return@with
        }

        if (type == DetailPassengerType.BOOKER && title.isEmpty() && fullName.isEmpty() && email.isEmpty() && phone.isEmpty()) {
            showToast(getString(R.string.message_not_empty))
            return@with
        }

        if (type == DetailPassengerType.PASSENGER) {

        }

        val passenger = Passenger(
            fullName = fullName,
            email = email,
            phoneNumber = phone,
            title = title
        )
        onSave?.invoke(passenger)
        dismiss()
    }

}