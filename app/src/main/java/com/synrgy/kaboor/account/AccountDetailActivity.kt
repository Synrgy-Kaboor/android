package com.synrgy.kaboor.account

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.result.contract.ActivityResultContracts
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.ext.PermissionExt
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.requestMultiplePermission
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.snackbarDanger
import com.synrgy.common.utils.ext.toDateFormat
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityAccountDetailBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.notEmptyRule
import org.koin.android.ext.android.inject
import java.util.Locale


class AccountDetailActivity : KaboorPassiveActivity<ActivityAccountDetailBinding>() {

    private val viewModel: AccountViewModel by inject()
    private var titleList: MutableList<String>? = null
    private var genderList: MutableList<String>? = null
    private var isWni: Boolean = false
    private var selectedTitle: String = ""
    private var selectedGender: String = ""

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AccountDetailActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityAccountDetailBinding =
        ActivityAccountDetailBinding.inflate(layoutInflater)

    override fun initUI() = with(binding) {
        initDataUser()
        imgProfile.setImageResource(R.drawable.ic_launcher_foreground)

        titleList = ArrayList(
            listOf("Mr.", "Mrs.", "Miss.")
        )
        genderList = ArrayList(
            listOf("Laki-Laki", "Perempuan")
        )
        spinnerTitle.item = titleList as List<String>
        spinnerGender.item = genderList as List<String>

        setupSpinnerListener(spinnerTitle, titleList as ArrayList<String>) { selectedValue ->
            selectedTitle = selectedValue
        }

        setupSpinnerListener(spinnerGender, genderList as ArrayList<String>) { selectedValue ->
            selectedGender = selectedValue
        }
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        llDateOfBirth.setOnClickListener { showDatePicker() }
        btnSave.onClick { validate() }
    }

    override fun initProcess() {}

    override fun initObservers() {
        viewModel.updatePersonalInfo.observerLiveData(
            this,
            onLoading = { showLoading() },
            onFailure = { _, message ->
                hideLoading()
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                Toast.makeText(
                    this,
                    getString(R.string.message_update_data_success),
                    LENGTH_SHORT
                ).show()
                onBackPress()
            }
        )
    }

    override fun onValidationSuccess() = with(binding) {
        val citizenship = etCitizenship.getText().lowercase(Locale.ROOT)

        if (citizenship == "indo" || citizenship == "indonesia") {
            isWni = true
        }

        val body = UpdatePersonalInfoParam(
            title = selectedTitle,
            fullName = etFullname.getText(),
            gender = selectedGender,
            birthday = tvDateOfBirth.text.toString(),
            nation = etCountry.getText(),
            city = etCity.getText(),
            address = etFullAddress.text.toString(),
            isWni = isWni
        )
        viewModel.updatePersonalInfo(body)
    }

    override fun setupValidation() = with(binding) {
        addValidation(
            Validation(
                etFullname.textInput, listOf(
                    notEmptyRule(getString(R.string.error_required_full_name))
                )
            )
        )
        addValidation(
            Validation(
                tvDateOfBirth, listOf(
                    notEmptyRule(getString(R.string.error_empty_birthday))
                )
            )
        )
        addValidation(
            Validation(
                etCountry.textInput, listOf(
                    notEmptyRule(getString(R.string.error_empty_country))
                )
            )
        )
        addValidation(
            Validation(
                etCity.textInput, listOf(
                    notEmptyRule(getString(R.string.error_empty_city))
                )
            )
        )
        addValidation(
            Validation(
                etFullAddress, listOf(
                    notEmptyRule(getString(R.string.error_empty_address))
                )
            )
        )
        addValidation(
            Validation(
                etCitizenship, listOf(
                    notEmptyRule(getString(R.string.error_empty_citizenship))
                )
            )
        )
    }

    private fun initDataUser() = with(binding) {
        viewModel.getPersonalInfo()
        viewModel.getPersonalInfo.observerLiveData(
            this@AccountDetailActivity,
            onLoading = { showLoading() },
            onFailure = { _, message ->
                hideLoading()
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()

                val selectedItemTitle = titleList?.indexOf(it.title.toString())
                val selectedItemGender = genderList?.indexOf(it.gender.toString())
                if (selectedItemTitle != -1) {
                    if (selectedItemTitle != null) {
                        spinnerTitle.setSelection(selectedItemTitle)
                    }
                }
                if (selectedItemGender != -1) {
                    if (selectedItemGender != null) {
                        spinnerGender.setSelection(selectedItemGender)
                    }
                }
                if (it.isWni == true) {
                    etCitizenship.setText(getString(R.string.message_wni))
                } else {
                    etCitizenship.setText(getString(R.string.message_wna))
                }
                etFullname.setText(it.fullName.toString())
                tvDateOfBirth.text = it.birthday.toString()
                etCountry.setText(it.nation.toString())
                etCity.setText(it.city.toString())
                etFullAddress.setText(it.address.toString())
            }
        )
    }

    fun setupSpinnerListener(
        spinner: Spinner,
        itemList: List<String>,
        onItemSelected: (String) -> Unit,
    ) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long,
            ) {
                val selectedValue = itemList[position]
                onItemSelected(selectedValue)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    private fun showDatePicker() = with(binding) {
        showDatePicker { date ->
            tvDateOfBirth.text = date.toDateFormat()
        }
    }

    private fun handleTakeAndSelectImage() {

    }

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) // TODO
            else snackbarDanger(getString(R.string.message_failed_select_image))
        }

    private val launchCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) // TODO
        else snackbarDanger(getString(R.string.message_failed_select_image))
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            requestMultiplePermission(
                permissions = PermissionExt.takeCapturePermission14,
                requestCode = PermissionExt.IMAGE_REQUEST_CODE,
                doIfGranted = {}
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestMultiplePermission(
                permissions = PermissionExt.takeCapturePermission13,
                requestCode = PermissionExt.IMAGE_REQUEST_CODE,
                doIfGranted = {}
            )
        } else {
            requestMultiplePermission(
                permissions = PermissionExt.takeCapturePermission12L,
                requestCode = PermissionExt.IMAGE_REQUEST_CODE,
                doIfGranted = { }
            )
        }
    }

}