package com.synrgy.common.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutAppBarBinding
import com.synrgy.common.utils.enums.AppbarType
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.common.utils.ext.invisibleIf
import com.synrgy.common.utils.ext.visibleIf
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
    private var type: AppbarType = AppbarType.APPBAR_NORMAL
    private var onBackListener: () -> Unit = {}
    private var onNotificationListener: () -> Unit = {}

    init {
        binding = LayoutAppBarBinding.inflate(LayoutInflater.from(context), this)
        setAttrs(attrs)
        setupView()
    }

    private fun setAttrs(attrs: AttributeSet?) {
        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.KaboorAppbar, 0, 0)
        title = attributes.getString(R.styleable.KaboorAppbar_kaboorAppbar_title).orEmpty()
        enableBackButton =
            attributes.getBoolean(R.styleable.KaboorAppbar_kaboorAppbar_enable_back, true)
        type = attributes.getInt(R.styleable.KaboorAppbar_kaboorAppbar_type, 0).let {
            AppbarType.entries[it]
        }
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
        imgBack.goneIf { !enableBackButton }
        ticketContainer.visibleIf { type == AppbarType.APPBAR_TICKET }
        imgNotification.invisibleIf { type != AppbarType.APPBAR_TICKET }
        tvTitle.goneIf { type == AppbarType.APPBAR_TICKET }
        tvTitle.text = title

        imgBack.onClick { onBackListener.invoke() }
    }

    fun setDescription(date: String, passenger: Int, clazz: String) = with(binding) {
        tvDesc.text = context.getString(R.string.format_appbar_description, date, passenger, clazz)
    }

    fun setOnBackClickListener(listener: () -> Unit) {
        onBackListener = listener
    }

    fun setOnNotificationClickListener(listener: () -> Unit) {
        onNotificationListener = listener
    }

}