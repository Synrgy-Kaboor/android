package com.synrgy.common.reusable

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.net.toFile
import com.google.android.material.card.MaterialCardView
import com.synrgy.common.databinding.LayoutPaymentReceiptBinding
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import java.io.File


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


class KaboorPaymentReceipt @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding = LayoutPaymentReceiptBinding.inflate(LayoutInflater.from(context), this)
    private var setOnSelectImage: () -> Unit = {}
    private var setOnRemoveImage: () -> Unit = {}
    private var isUploaded = false

    init {
        setupView()
    }

    private fun setupView() = with(binding) {
        selectReceiptContainer.onClick { setOnSelectImage.invoke() }
        imgSelectFile.onClick { setOnSelectImage.invoke() }
        tvSelectFile.onClick { setOnSelectImage.invoke() }
        imgRemove.onClick { setOnRemoveImage.invoke() }
    }

    private fun showContainer() = with(binding) {
        if (!isUploaded) {
            selectReceiptContainer.visible()
            receiptContainer.gone()
        } else {
            selectReceiptContainer.gone()
            receiptContainer.visible()
        }
    }


    fun setOnSelectImage(listener: () -> Unit) {
        this.setOnSelectImage = listener
    }

    fun removeImageFile() {
        isUploaded = false
        showContainer()
    }
    fun setUploaded(isUploaded: Boolean) {
        this.isUploaded = isUploaded
        showContainer()
    }

    fun setOnRemoveImage(listener: () -> Unit) {
        this.setOnRemoveImage = listener
    }

    fun setImage(uri: Uri) {
        binding.imgSelectFile.setImageURI(uri)
    }
}