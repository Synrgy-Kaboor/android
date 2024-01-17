package com.synrgy.common.reusable

import android.content.Context
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutBaseEditTextBinding
import com.synrgy.common.utils.enums.EditTextType
import com.synrgy.common.utils.ext.endIconClick
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.common.utils.ext.setEndIcon
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.toStringTrim


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


class KaboorEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: LayoutBaseEditTextBinding

    val editText by lazy { binding.til.editText?.toStringTrim().toString() }
    val textInput by lazy { binding.til }

    private var hint: String? = emptyString()
    private var label: String? = emptyString()
    private var enable: Boolean = true
    private var type: EditTextType = EditTextType.TEXT

    private var isPasswordShow: Boolean = false

    init {
        binding = LayoutBaseEditTextBinding.inflate(LayoutInflater.from(context), this)
        setAttrs(attrs)
        setupView()
    }

    private fun setAttrs(attrs: AttributeSet?) {
        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.KaboorEditText, 0, 0)
        hint = attributes.getString(R.styleable.KaboorEditText_kaboorEditText_hint).orEmpty()
        label = attributes.getString(R.styleable.KaboorEditText_kaboorEditText_label).orEmpty()
        enable = attributes.getBoolean(R.styleable.KaboorEditText_kaboorEditText_enable, true)
        type = attributes.getInt(R.styleable.KaboorEditText_kaboorEditText_type, 0).let {
            EditTextType.entries[it]
        }
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
        tvLabel.goneIf { label.isNullOrEmpty() }
        tvLabel.text = label
        et.hint = hint
        et.inputType = type.type

        if (!enable) {
            et.isEnabled = false
            et.inputType = InputType.TYPE_NULL
        }

        if (type == EditTextType.PASSWORD) {
            setupViewPassword()
        }
    }

    private fun setupViewPassword() = with(binding) {
        til.setEndIcon(R.drawable.ic_eye)
        et.transformationMethod = PasswordTransformationMethod.getInstance()
        til.endIconClick { setIconPassword() }
    }

    private fun setIconPassword() = with(binding){
        if (!isPasswordShow){
            til.setEndIcon(R.drawable.ic_eye_slash)
            et.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }else{
            til.setEndIcon(R.drawable.ic_eye)
            et.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        isPasswordShow = !isPasswordShow
    }

}