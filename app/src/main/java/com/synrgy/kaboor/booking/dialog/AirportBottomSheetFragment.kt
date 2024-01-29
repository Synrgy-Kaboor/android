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


class AirportBottomSheetFragment : KaboorBottomSheet<FragmentAirportBottomSheetBinding>() {

    private val airportAdapter by lazy {
        AirportAdapter(requireContext(), ::setAirportSelected)
    }

    private var onSelectedAirport: (AirportData) -> Unit = {}

    companion object {
        fun newInstance(
            onSelectedAirport: (AirportData) -> Unit
        ): AirportBottomSheetFragment = AirportBottomSheetFragment().apply {
            this.onSelectedAirport = onSelectedAirport
        }
    }

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

    override fun initProcess() {
        super.initProcess()
        airportAdapter.setData = ConstantDummy.airports().map { it.toData() }
    }

    private fun setAirportSelected(data: AirportData) {
        onSelectedAirport.invoke(data)
        dismiss()
    }

    private fun filter(text: String) {
        val query = ArrayList<AirportData>()
        ConstantDummy.airports().map { it.toData() }.forEach {
            if (it.city.lowerContains(text) ||
                it.airport.lowerContains(text)
            ) {
                query.add(it)
            }
        }
        airportAdapter.filter(query)
    }

}