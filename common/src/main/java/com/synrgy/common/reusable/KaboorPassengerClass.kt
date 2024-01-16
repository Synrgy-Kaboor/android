package com.synrgy.common.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutPassengerClassBinding
import com.synrgy.common.utils.enums.PassengerClassType
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.common.utils.ext.visibleIf
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
        label = attributes.getString(R.styleable.KaboorPassengerClass_kaboorPassengerClass_label)
            .orEmpty()
        description =
            attributes.getString(R.styleable.KaboorPassengerClass_kaboorPassengerClass_description)
                .orEmpty()
        type =
            attributes.getInt(R.styleable.KaboorPassengerClass_kaboorPassengerClass_type, 0).let {
                PassengerClassType.entries[it]
            }
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
        tvLabel.text = label
        tvDescription.goneIf { description.isEmpty() }
        tvDescription.text = description
        tvCount.text = passengerCount.toString()

        countContainer.visibleIf { type == PassengerClassType.PASSENGER }
        imgSelectable.visibleIf { type == PassengerClassType.PLANE_CLASS }

        btnMinus.onClick { setCounter(false) }
        btnPlus.onClick { setCounter(true) }
    }

    private fun setCounter(isIncrement: Boolean) = with(binding) {
        if (!isIncrement && passengerCount > 0) {
            passengerCount--
            tvCount.text = passengerCount.toString()
        } else if (isIncrement) {
            passengerCount++
            tvCount.text = passengerCount.toString()
        }
    }

    fun setPassenger(passenger: Int) = with(binding) {
        passengerCount = passenger
        tvCount.text = passengerCount.toString()
    }

    fun setSelectedItem(isSelected: Boolean) {
        binding.imgSelectable.isSelected = isSelected
    }

    fun setLabel(label: String) {
        binding.tvLabel.text = label
    }

    fun setDescription(description: String) {
        binding.tvDescription.visibleIf { description.isNotEmpty() }
        binding.tvDescription.text = description
    }
}