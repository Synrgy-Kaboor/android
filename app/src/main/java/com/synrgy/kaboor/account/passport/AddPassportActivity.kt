package com.synrgy.kaboor.account.passport

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.snackbarDanger
import com.synrgy.common.utils.ext.toDateFormatMonth
import com.synrgy.common.utils.ext.toStringTrim
import com.synrgy.domain.user.model.request.PassportParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityAddPassportBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.notEmptyRule
import org.koin.android.ext.android.inject

class AddPassportActivity : KaboorPassiveActivity<ActivityAddPassportBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AddPassportActivity::class.java))
        }
    }

    private val viewModel: PassportViewModel by inject()

    override fun getViewBinding(): ActivityAddPassportBinding {
        return ActivityAddPassportBinding.inflate(layoutInflater)
    }

    override fun initUI() = with(binding) {

    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        llExpiredDate.onClick { showDatePicker() }
        btnSave.onClick { validate() }
    }

    private fun showDatePicker() = with(binding) {
        showDatePicker { date ->
            tvExpiredDate.text = date.toDateFormatMonth()
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.generic.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                onBackPress()
            }
        )
    }

    override fun onValidationSuccess() = with(binding) {
        if (tvExpiredDate.toStringTrim().isEmpty()) {
            snackbarDanger(getString(R.string.error_required_date))
            return
        }
        val param = PassportParam(
            fullName = etFullName.getText(),
            passportNumber = etPassportNumber.getText(),
            expiredDate = tvExpiredDate.toStringTrim(),
            nation = etCountry.getText()
        )
        viewModel.addPassport(param)
    }

    override fun setupValidation() = with(binding) {
        addValidation(
            Validation(
                etFullName.textInput,
                listOf(notEmptyRule(getString(R.string.error_required_full_name)))
            )
        )

        addValidation(
            Validation(
                etPassportNumber.textInput,
                listOf(notEmptyRule(getString(R.string.error_required_passport)))
            )
        )

        addValidation(
            Validation(
                etCountry.textInput,
                listOf(notEmptyRule(getString(R.string.error_required_country)))
            )
        )
    }
}