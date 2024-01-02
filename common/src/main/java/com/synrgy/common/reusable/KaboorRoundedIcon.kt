package com.synrgy.common.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutRoundedIconBinding
import com.synrgy.common.utils.goneIf
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setHeight
import com.wahidabd.library.utils.exts.setWidth


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

    private var label = emptyString()
    private var icon = 0
    private var showLabel = false
    private var size = 50

    private var onClickListener: () -> Unit = {}

    init {
        binding = LayoutRoundedIconBinding.inflate(LayoutInflater.from(context), this)
        setupAttributes(attrs)
        setupView()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.KaboorRoundedIcon, 0, 0)
        label = attributes.getString(R.styleable.KaboorRoundedIcon_kaboorRoundedIconLabel).orEmpty()
        icon = attributes.getResourceId(R.styleable.KaboorRoundedIcon_kaboorRoundedIcon, 0)
        showLabel =
            attributes.getBoolean(R.styleable.KaboorRoundedIcon_kaboorRoundedIconShowLabel, true)
        size = attributes.getDimensionPixelSize(
            R.styleable.KaboorRoundedIcon_kaboorRoundedIconSize,
            50
        )
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
        tvLabel.goneIf { !showLabel }
        tvLabel.text = label
        imgIcon.setWidth(size)
        imgIcon.setHeight(size)

        imgIcon.setImageResource(icon)
        roundedIcon.onClick { onClickListener.invoke() }
    }

    fun setOnIconClick(onClickListener: () -> Unit) {
        this.onClickListener = onClickListener
    }

    fun setLabel(label: String) {
        this.label = label
        binding.tvLabel.text = label
    }

    fun setIcon(@DrawableRes icon: Int) {
        this.icon = icon
        binding.imgIcon.setImageResource(icon)
    }
}