package com.synrgy.common.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutRoundedIconBinding
import com.synrgy.common.utils.goneIf
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.onlyGoneIf


/**
 * Created by wahid on 12/27/2023.
 * Github github.com/wahidabd.
 */


class KaboorRoundedIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutRoundedIconBinding
    var label = emptyString()
        private set
    var icon = 0
        private set
    var showLabel = false
        private set

    private var onClickListener: () -> Unit = {}

    init {
        binding = LayoutRoundedIconBinding.inflate(LayoutInflater.from(context), this)
        setupAttributes(attrs)
        setupView()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.KaboorRoundedIcon, 0, 0)
        label = attributes.getString(R.styleable.KaboorRoundedIcon_label).orEmpty()
        icon = attributes.getResourceId(R.styleable.KaboorRoundedIcon_icon, 0)
        showLabel = attributes.getBoolean(R.styleable.KaboorRoundedIcon_showLabel, true)
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
        tvLabel.goneIf { !showLabel }
        tvLabel.text = label

        imgIcon.setImageResource(icon)
        roundedIcon.onClick { onClickListener.invoke() }
    }

    fun setOnIconClick(onClickListener: () -> Unit) {
        this.onClickListener = onClickListener
    }
}