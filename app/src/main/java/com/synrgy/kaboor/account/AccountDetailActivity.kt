package com.synrgy.kaboor.account

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.ext.PermissionExt
import com.synrgy.common.utils.ext.getImageFile
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.replaceSpace
import com.synrgy.common.utils.ext.requestMultiplePermission
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.snackbarDanger
import com.synrgy.common.utils.ext.toDateFormat
import com.synrgy.common.utils.ext.toDateFormatMonth
import com.synrgy.common.utils.ext.toStringTrim
import com.synrgy.domain.user.model.request.ImageProfileParam
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityAccountDetailBinding
import com.synrgy.kaboor.databinding.DialogSelectImageBinding
import com.wahidabd.library.utils.exts.getCompatDrawable
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.notEmptyRule
import org.koin.android.ext.android.inject
import java.io.File
import java.util.Locale
import com.synrgy.common.R as comR


class AccountDetailActivity : KaboorPassiveActivity<ActivityAccountDetailBinding>() {

    private val viewModel: AccountViewModel by inject()
    private var titleList: MutableList<String>? = null
    private var genderList: MutableList<String>? = null
    private var isWni: Boolean = false
    private var selectedTitle: String = ""
    private var selectedGender: String = ""
    private var imageUri: Uri? = null
    private var imageName: String = ""

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AccountDetailActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityAccountDetailBinding =
        ActivityAccountDetailBinding.inflate(layoutInflater)

    override fun initUI() = with(binding) {
        initDataUser()

        titleList = ArrayList(
            listOf("Mr.", "Mrs.", "Miss.")
        )
        genderList = ArrayList(
            listOf("Laki-laki", "Perempuan")
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
        imgCamera.onClick { requestPermissions() }
    }

    override fun initProcess() {
        viewModel.getProfile()
    }

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

                viewModel.getPersonalInfo()
            }
        )

        viewModel.imageProfile.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                Toast.makeText(
                    this,
                    getString(R.string.message_update_data_success),
                    LENGTH_SHORT
                ).show()
                viewModel.setProfile(it.imageUrl.replaceSpace())
                viewModel.getProfile()
                initUpdateDataUser(it.imageName)
            }
        )

        viewModel.profile.observe(this) { profile ->
            if (profile.isNotEmpty()) {
                binding.imgProfile.setImageUrl(this, profile)
            }
        }
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
                etNik.textInput, listOf(
                    notEmptyRule(getString(R.string.error_empty_nik))
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
                tilFullAddress, listOf(
                    notEmptyRule(getString(R.string.error_empty_city))
                )
            )
        )
        addValidation(
            Validation(
                etCitizenship.textInput, listOf(
                    notEmptyRule(getString(R.string.error_empty_citizenship))
                )
            )
        )
    }

    override fun onValidationSuccess() {
        initUpdateDataUser(imageName)
    }

    private fun initUpdateDataUser(updateImageName: String) = with(binding) {
        val citizenship = etCitizenship.getText().lowercase(Locale.ROOT)

        isWni = citizenship == "indo" || citizenship == "indonesia" || citizenship == "wni"

        val body = UpdatePersonalInfoParam(
            title = selectedTitle,
            fullName = etFullname.getText(),
            gender = selectedGender,
            birthday = tvDateOfBirth.toStringTrim(),
            nik = etNik.getText(),
            nation = etCountry.getText(),
            city = etCity.getText(),
            address = etFullAddress.text.toString(),
            isWni = isWni,
            imageName = updateImageName
        )
        viewModel.updatePersonalInfo(body)
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

                val selectedItemTitle = titleList?.indexOf(it.title.orEmpty())
                val selectedItemGender = genderList?.indexOf(it.gender.orEmpty())
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
                etFullname.setText(it.fullName.orEmpty())
                tvDateOfBirth.text = it.birthday.orEmpty()
                etNik.setText(it.nik.orEmpty())
                etCountry.setText(it.nation.orEmpty())
                etCity.setText(it.city.orEmpty())
                etFullAddress.setText(it.address.orEmpty())
                imageName = it.imageName.orEmpty()

                viewModel.saveUserInfo(it)
            }
        )
    }

    private fun setupSpinnerListener(
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
            tvDateOfBirth.text = date.toDateFormatMonth()
        }
    }

    private fun initRequest(uri: Uri) {
        val body = ImageProfileParam(getImageFile(uri))
        viewModel.uploadImage(body)
    }

    private fun handleTakeAndSelectImage() {
        val dialogBinding = DialogSelectImageBinding.inflate(LayoutInflater.from(this))
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogBinding.root)
            .setBackground(getCompatDrawable(comR.drawable.bg_rectangle_stroke_white))
            .create()

        dialogBinding.imgCamera.onClick {
            val imageFile = File.createTempFile(
                "IMG_",
                ".jpg",
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            )

            imageUri = FileProvider.getUriForFile(
                this,
                "${this.packageName}.provider",
                imageFile
            )

            launchCamera.launch(imageUri)
            dialog.dismiss()
        }
        dialogBinding.imgGalley.onClick {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            dialog.dismiss()
        }

        dialog.show()
    }

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) initRequest(uri)
            else snackbarDanger(getString(R.string.message_failed_select_image))
        }

    private val launchCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess && imageUri != null) initRequest(imageUri!!)
        else snackbarDanger(getString(R.string.message_failed_select_image))
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            requestMultiplePermission(
                permissions = PermissionExt.takeCapturePermission14,
                requestCode = PermissionExt.CAPTURE_REQUEST_CODE,
                doIfGranted = ::handleTakeAndSelectImage
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestMultiplePermission(
                permissions = PermissionExt.takeCapturePermission13,
                requestCode = PermissionExt.CAPTURE_REQUEST_CODE,
                doIfGranted = ::handleTakeAndSelectImage
            )
        } else {
            requestMultiplePermission(
                permissions = PermissionExt.takeCapturePermission12L,
                requestCode = PermissionExt.CAPTURE_REQUEST_CODE,
                doIfGranted = ::handleTakeAndSelectImage
            )
        }
    }

}