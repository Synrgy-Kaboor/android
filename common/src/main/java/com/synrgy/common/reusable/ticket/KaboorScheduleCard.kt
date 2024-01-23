package com.synrgy.common.reusable.ticket

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.synrgy.common.databinding.LayoutScheduleCardBinding
import com.synrgy.common.utils.ext.onGroupClick
import com.synrgy.common.utils.ext.oneWeekMillis
import com.synrgy.common.utils.ext.toFullDateFormat
import com.synrgy.common.utils.ext.tomorrowMillis
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.visible


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */


class KaboorScheduleCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var binding: LayoutScheduleCardBinding
    private var onClickDeparture: () -> Unit = {}
    private var onClickComingHome: () -> Unit = {}
    private var isRoundTrip = false

    var departure = tomorrowMillis
        private set

    var comingHome = oneWeekMillis
        private set

    init {
        binding = LayoutScheduleCardBinding.inflate(LayoutInflater.from(context), this)
        setupView()
    }

    private fun setupView() = with(binding) {
        tvDeparture.text = departure.toFullDateFormat()
        tvArrival.text = comingHome.toFullDateFormat()

        groupComingHome.onGroupClick { onClickComingHome.invoke() }
        groupDeparture.onGroupClick { onClickDeparture.invoke() }

        btnSwitch.setOnCheckedChangeListener { _, checked ->
            if (checked) groupComingHome.visible() else groupComingHome.gone()
            isRoundTrip = checked
        }
    }

    fun setDeparture(departure: Long) {
        this.departure = departure
        binding.tvDeparture.text = departure.toFullDateFormat()
    }

    fun setComingHome(comingHome: Long) {
        this.comingHome = comingHome
        binding.tvArrival.text = comingHome.toFullDateFormat()
    }

    fun setOnDepartureListener(onClick: () -> Unit) {
        this.onClickDeparture = onClick
    }

    fun setOnComingHomeListener(onClick: () -> Unit) {
        this.onClickComingHome = onClick
    }

    fun getRoundTrip(): Boolean = isRoundTrip

}