package com.synrgy.kaboor.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityRegisterBinding
import com.synrgy.kaboor.databinding.DialogErrorBinding

class RegisterActivity : KaboorActivity<ActivityRegisterBinding>() {

    companion object {
        fun start(context: AppCompatActivity){
            context.startActivity(Intent(context, RegisterActivity::class.java))

        }
    }

    override fun getViewBinding(): ActivityRegisterBinding =
        ActivityRegisterBinding.inflate(layoutInflater)


    // TODO: For handle intent (Data, etc)
    override fun initIntent() {}

    // TODO: For UI
    override fun initUI() {
        binding.btDaftar.setOnClickListener {
            val email = binding.etEmail.text.toString()

            if (isValidEmail(email)) {
                // Format email valid, lanjutkan dengan mengirim data
                kirimData(email)
            } else {
                // Format email tidak valid, tampilkan pesan kesalahan dalam bentuk custom dialog
                tampilkanDialogError("Format email tidak valid")
            }
        }

    }

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {}

    // TODO: For Observer (LiveData, etc)
    override fun initObservers() {}

    private fun tampilkanDialogError(pesan: String) {
        val dialogBinding = DialogErrorBinding.inflate(layoutInflater)
        dialogBinding.tvErrorMessage.text = pesan

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.btnOK.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun kirimData(email: String) {
//        val intent = Intent(this, DetailRegisterActivity::class.java)
        intent.putExtra("email", email)
        startActivity(intent)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }


}