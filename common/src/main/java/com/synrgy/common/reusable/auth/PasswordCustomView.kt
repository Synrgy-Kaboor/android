package com.synrgy.common.reusable.auth

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.synrgy.common.R

class PasswordCustomView : AppCompatEditText {
    private lateinit var warning: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun showWarning() {
        setButtonDrawables(bottomOfTheText = warning)
    }

    private fun hideWarning() {
        setButtonDrawables(bottomOfTheText = null)
    }

    private fun hideClearButton() {
        setButtonDrawables()
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null,
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    private fun init() {
        warning = ContextCompat.getDrawable(context, R.drawable.warning_error) as Drawable
        var passValid = false

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    passValid = if (s.toString().length < 8) {
                        showWarning()
                        false
                    } else {
                        hideWarning()
                        true
                    }
                } else hideClearButton()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }
}