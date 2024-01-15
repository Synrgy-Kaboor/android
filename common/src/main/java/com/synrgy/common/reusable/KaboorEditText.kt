package com.synrgy.common.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutBaseEditTextBinding
import com.synrgy.common.utils.enums.EditTextType
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.common.utils.ext.textTrim
import com.wahidabd.library.presentation.view.ErrorableView
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.visible


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

    val editText by lazy { binding.et.textTrim() }
    val textInput by lazy { binding.til }

    private var hint: String? = emptyString()
    private var label: String? = emptyString()
    private var type: EditTextType = EditTextType.TEXT

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
    }

}