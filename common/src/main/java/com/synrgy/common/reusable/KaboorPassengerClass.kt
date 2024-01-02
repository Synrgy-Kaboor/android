package com.synrgy.common.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutPassengerClassBinding
import com.synrgy.common.utils.enums.views.PassengerClassType
import com.synrgy.common.utils.goneIf
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/1/2024.
 * Github github.com/wahidabd.
 */


class KaboorPassengerClass @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutPassengerClassBinding

    private var label = emptyString()
    private var description = emptyString()
    private var showDescription = true
    private var type: PassengerClassType = PassengerClassType.PASSENGER

    var passengerCount = 0
        private set


    init {
        binding = LayoutPassengerClassBinding.inflate(LayoutInflater.from(context), this)
        setupAttributes(attrs)
        setupView()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.KaboorPassengerClass, 0, 0)
        label = attributes.getString(R.styleable.KaboorPassengerClass_kaboorPassengerClass_label).orEmpty()
        description = attributes.getString(R.styleable.KaboorPassengerClass_kaboorPassengerClass_description).orEmpty()
        showDescription = attributes.getBoolean(R.styleable.KaboorPassengerClass_kaboorPassengerClass_showDescription, true)
        type = attributes.getInt(R.styleable.KaboorPassengerClass_kaboorPassengerClass_type, 0).let {
            PassengerClassType.entries[it]
        }
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
        tvLabel.text = label
        tvDescription.goneIf { !showDescription }
        tvDescription.text = description
        tvCount.text = passengerCount.toString()

        btnMinus.onClick { setCounter(false) }
        btnPlus.onClick { setCounter(true) }
    }

    private fun setCounter(isIncrement: Boolean) = with(binding){
        if (!isIncrement && passengerCount > 0) {
            passengerCount--
            tvCount.text = passengerCount.toString()
        } else if (isIncrement) {
            passengerCount++
            tvCount.text = passengerCount.toString()
        }
    }
}