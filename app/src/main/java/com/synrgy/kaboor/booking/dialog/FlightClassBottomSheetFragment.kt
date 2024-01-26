package com.synrgy.kaboor.booking.dialog

import android.view.LayoutInflater
import com.synrgy.common.R
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.kaboor.databinding.FragmentFlightClassBottomSheetBinding
import com.synrgy.kaboor.booking.adapter.PlaneClassAdapter
import com.synrgy.kaboor.utils.constant.ConstantTag
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/3/2024.
 * Github github.com/wahidabd.
 */


class FlightClassBottomSheetFragment : KaboorBottomSheet<FragmentFlightClassBottomSheetBinding>() {

    private val planeClassAdapter by lazy {
        PlaneClassAdapter(
            requireContext(),
            defaultItem
        ) { type ->
            onSave.invoke(type)
        }
    }

    private var defaultItem: PlaneClassType = PlaneClassType.EKONOMI
    private var onSave: (PlaneClassType) -> Unit = {}

    companion object {
        fun newInstance(
            defaultItem: PlaneClassType,
            onSave: (PlaneClassType) -> Unit,
        ): FlightClassBottomSheetFragment {
            return FlightClassBottomSheetFragment().apply {
                this.onSave = onSave
                this.defaultItem = defaultItem
            }
        }
    }

    override val tagName: String = ConstantTag.TAG_PLANE_CLASS
    override fun getTitle(): String = getString(R.string.label_class)
    override fun setCancelButtonEnable(): Boolean = true
    override fun showButton(): Boolean = true

    override fun getContentBinding(inflater: LayoutInflater): FragmentFlightClassBottomSheetBinding =
        FragmentFlightClassBottomSheetBinding.inflate(layoutInflater)

    override fun initUI() {
        super.initUI()
        contentBinding.rvPlaneClass.adapter = planeClassAdapter
        binding.btnSave.onClick { dismiss() }
    }

    override fun initProcess() {
        super.initProcess()
        planeClassAdapter.addAll(PlaneClassType.entries)
    }
}