package com.synrgy.common.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutRoundedIconBinding
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.common.utils.ext.visibleIf
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
    private var size = 50
    private var padding = 12

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
        size = attributes.getDimensionPixelSize(
            R.styleable.KaboorRoundedIcon_kaboorRoundedIconSize,
            50
        )
        padding = attributes.getDimensionPixelSize(
            R.styleable.KaboorRoundedIcon_kaboorRoundedIcon_padding,
            12
        )
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
        tvLabel.goneIf { label.isEmpty() }
        tvLabel.text = label
        imgIcon.setWidth(size)
        imgIcon.setHeight(size)
        imgIcon.setPadding(padding, padding, padding, padding)

        imgIcon.setImageResource(icon)
        roundedIcon.onClick { onClickListener.invoke() }
    }

    fun setOnIconClick(onClickListener: () -> Unit) {
        this.onClickListener = onClickListener
    }

    fun setLabel(label: String) {
        this.label = label
        binding.tvLabel.apply {
            text = label
            visibleIf { label.isNotEmpty() }
        }
    }

    fun setIcon(@DrawableRes icon: Int) {
        this.icon = icon
        binding.imgIcon.setImageResource(icon)
    }
}