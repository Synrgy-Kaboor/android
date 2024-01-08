package com.synrgy.common.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutAppBarBinding
import com.synrgy.common.utils.ext.goneIf
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/7/2024.
 * Github github.com/wahidabd.
 */


class KaboorAppbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutAppBarBinding

    private var title: String = emptyString()
    private var enableBackButton: Boolean = true
    private var onBackListener: () -> Unit = {}

    init {
        binding = LayoutAppBarBinding.inflate(LayoutInflater.from(context), this)
        setAttributes(attrs)
        setupView()
    }

    private fun setAttributes(attrs: AttributeSet?) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.KaboorAppbar, 0, 0)
        title = attributes.getString(R.styleable.KaboorAppbar_KaboorAppbar_title).orEmpty()
        enableBackButton =
            attributes.getBoolean(R.styleable.KaboorAppbar_KaboorAppbar_enable_back, true)
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
        imgBack.goneIf { !enableBackButton }
        tvTitle.text = title

        imgBack.onClick { onBackListener.invoke() }
    }

    fun setOnBackClickListener(listener: () -> Unit) {
        onBackListener = listener
    }

}