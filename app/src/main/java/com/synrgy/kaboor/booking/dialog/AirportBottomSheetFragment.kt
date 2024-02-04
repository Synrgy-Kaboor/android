package com.synrgy.kaboor.booking.dialog

import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import com.synrgy.common.R
import com.synrgy.common.model.AirportData
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.common.utils.ext.lowerContains
import com.synrgy.domain.flight.mapper.toData
import com.synrgy.kaboor.booking.adapter.AirportAdapter
import com.synrgy.kaboor.databinding.FragmentAirportBottomSheetBinding
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.kaboor.booking.viewmodel.FlightViewModel
import com.wahidabd.library.utils.exts.observerLiveData
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent


class AirportBottomSheetFragment : KaboorBottomSheet<FragmentAirportBottomSheetBinding>() {

    companion object {
        fun newInstance(
            airportData: List<AirportData>,
            onSelectedAirport: (AirportData) -> Unit
        ): AirportBottomSheetFragment = AirportBottomSheetFragment().apply {
            this.onSelectedAirport = onSelectedAirport
            this.airportData = airportData
        }
    }

    private var airportData = listOf<AirportData>()

    private val airportAdapter by lazy {
        AirportAdapter(requireContext(), ::setAirportSelected)
    }

    private var onSelectedAirport: (AirportData) -> Unit = {}

    override val tagName: String = ConstantTag.TAG_PASSENGER
    override fun getTitle(): String = getString(R.string.label_airport)
    override fun setCancelButtonEnable(): Boolean = true
    override fun showButton(): Boolean = false
    override fun getContentBinding(inflater: LayoutInflater): FragmentAirportBottomSheetBinding =
        FragmentAirportBottomSheetBinding.inflate(layoutInflater)

    override fun initUI() {
        super.initUI()

        contentBinding.rvAirport.adapter = airportAdapter

        contentBinding.etSearch.doAfterTextChanged {
            filter(it.toString())
        }
    }

    override fun initObservers() {
        super.initObservers()
        airportAdapter.setData = airportData
    }

    private fun setAirportSelected(data: AirportData) {
        onSelectedAirport.invoke(data)
        dismiss()
    }

    private fun filter(text: String) {
        val query = ArrayList<AirportData>()
        airportData.forEach {
            if (it.code.lowerContains(text) ||
                it.name.lowerContains(text)
            ) {
                query.add(it)
            }
        }
        airportAdapter.filter(query)
    }

}