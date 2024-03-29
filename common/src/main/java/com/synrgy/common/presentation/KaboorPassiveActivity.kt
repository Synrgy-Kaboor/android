package com.synrgy.common.presentation

import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.synrgy.common.R
import com.synrgy.common.presentation.dialog.GenericBottomSheetFragment
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.validation.PassiveFormActivity
import com.wahidabd.library.validation.PassiveValidator
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.ValidationListener


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


abstract class KaboorPassiveActivity<VB: ViewBinding> : KaboorActivity<VB>(), ValidationListener {

    private val validator = PassiveValidator(arrayListOf())

    override fun initProcess() {}
    override fun initObservers() {}
    override fun onValidationFailed() {}

    override fun initIntent() {
        this.validator.setListener(this)
        this.setupValidation()
    }

    fun addValidation(validation: Validation){
        this.validator.addValidation(validation)
    }

    fun validate(): Boolean = validator.validate()

    abstract fun setupValidation()

}