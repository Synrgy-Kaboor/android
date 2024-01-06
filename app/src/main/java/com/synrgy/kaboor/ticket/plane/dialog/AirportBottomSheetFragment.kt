package com.synrgy.kaboor.ticket.plane.dialog

import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import com.synrgy.common.R
import com.synrgy.common.model.AirportData
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.common.utils.constant.DummyData
import com.synrgy.common.utils.ext.lowerContains
import com.synrgy.kaboor.databinding.FragmentAirportBottomSheetBinding
import com.synrgy.kaboor.ticket.plane.adapter.AirportAdapter


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

    override val tagName: String = AirportBottomSheetFragment::class.java.name
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
        airportAdapter.setData = DummyData.airports()
    }

    private fun setAirportSelected(data: AirportData){
        onSelectedAirport.invoke(data)
        dismiss()
    }

    private fun filter(text: String){
        val query = ArrayList<AirportData>()
        DummyData.airports().forEach {
            if (it.city.lowerContains(text) ||
                it.country.lowerContains(text) ||
                it.iata.lowerContains(text) ||
                it.name.lowerContains(text)){
                query.add(it)
            }
        }
        airportAdapter.filter(query)
    }

}