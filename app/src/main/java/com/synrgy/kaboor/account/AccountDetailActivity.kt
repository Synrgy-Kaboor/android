package com.synrgy.kaboor.account

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.PermissionExt
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.requestMultiplePermission
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.snackbarDanger
import com.synrgy.common.utils.ext.toDateFormat
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityAccountDetailBinding
import com.wahidabd.library.utils.exts.onClick


class AccountDetailActivity : KaboorActivity<ActivityAccountDetailBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AccountDetailActivity::class.java))
        }
    }

    private var titleList: MutableList<String>? = null
    private var genderList: MutableList<String>? = null

    override fun getViewBinding(): ActivityAccountDetailBinding =
        ActivityAccountDetailBinding.inflate(layoutInflater)

    override fun initUI() = with(binding) {
        imgProfile.setImageResource(R.drawable.ic_launcher_foreground)

        titleList = ArrayList(
            listOf("Mr.", "Mrs.", "Miss.")
        )
        genderList = ArrayList(
            listOf("Laki-laki", "Perempuan")
        )
        spinnerTitle.item = titleList as List<String>
        spinnerGender.item = genderList as List<String>
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        llDateOfBirth.setOnClickListener { showDatePicker() }
        btnSave.onClick { }
    }

    override fun initProcess() {}

    override fun initObservers() {}

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