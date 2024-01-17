package com.synrgy.common.presentation

import androidx.viewbinding.ViewBinding
import com.wahidabd.library.validation.PassiveValidator
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.ValidationListener


/**
 * Created by wahid on 1/17/2024.
 * Github github.com/wahidabd.
 */


abstract class KaboorPassiveFragment<VB: ViewBinding> : KaboorFragment<VB>(), ValidationListener {

    private val validator = PassiveValidator(arrayListOf())

    override fun initProcess() {}
    override fun initObservers() {}
    override fun onValidationFailed() {}

    override fun initUI() {
        this.validator.setListener(this)
        this.setupValidation()
    }

    fun addValidation(validation: Validation){
        this.validator.addValidation(validation)
    }

    fun validate(): Boolean = validator.validate()

    abstract fun setupValidation()
}