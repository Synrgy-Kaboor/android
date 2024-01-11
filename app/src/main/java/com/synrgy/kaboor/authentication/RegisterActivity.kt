package com.synrgy.kaboor.authentication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityRegisterBinding
import com.synrgy.kaboor.databinding.BottomSheetLayoutBinding
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
        val spannableString = SpannableString("Dengan membuat akun kamu menyetujui Syarat, Ketentuan dan Kebijakan Privasi Kaboor")
        val blueSpan = ForegroundColorSpan(Color.parseColor("#3A42FF"))
        val start = spannableString.indexOf("Syarat, Ketentuan dan Kebijakan")
        val end = start + "Syarat, Ketentuan dan Kebijakan".length
        spannableString.setSpan(blueSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.syarat.text = spannableString

        val spannableStringLogin = SpannableString("Udah punya akun? Log in aja!")
        val blueSpanLogin = ForegroundColorSpan(Color.parseColor("#3A42FF"))
        val startLogin = spannableStringLogin.indexOf("Log in aja!")
        val endLogin = startLogin + "Log in aja!".length
        spannableStringLogin.setSpan(blueSpanLogin, startLogin, endLogin, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.punya.text = spannableStringLogin


        binding.btDaftar.setOnClickListener {
            val email = binding.etEmail.text.toString()

            if (email == "muhkelvin@gmail.com") {
                // Menampilkan dialog pesan jika email adalah "muhkelvin@gmail.com"
                tampilkanDialogPesan("Email ini terdaftar, silahkan gunakan email lain.")
            } else if (isValidEmail(email)) {
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

    fun tampilkanDialogError(pesan: String) {
        val dialogBinding = DialogErrorBinding.inflate(layoutInflater)
        val tvErrorMessage: TextView = dialogBinding.tvErrorMessage
        val btnOK: Button = dialogBinding.btnOK

        tvErrorMessage.text = pesan

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        btnOK.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun tampilkanDialogPesan(pesan: String) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = BottomSheetLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        bottomSheetBinding.tvText.text = pesan

        // Setel listener untuk button "Nanti Saja"
        bottomSheetBinding.btNanti.setOnClickListener {
            // Menutup BottomSheetDialog
            bottomSheetDialog.dismiss()
        }

        // Setel listener untuk button "Login"
        bottomSheetBinding.btLogin.setOnClickListener {
            // Menutup BottomSheetDialog
            bottomSheetDialog.dismiss()

            // Memulai aktivitas login
//            val intent = Intent(this, DetailRegisterActivity::class.java)
            startActivity(intent)
        }

        bottomSheetDialog.show()
    }

    private fun kirimData(email: String) {
        val intent = Intent(this, RegisterDetailActivity::class.java)
        intent.putExtra("email", email)
        startActivity(intent)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }


}