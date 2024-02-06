package com.synrgy.kaboor.promo

import android.view.LayoutInflater
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.domain.promo.model.response.Voucher
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.FragmentVoucherBottomSheetBinding
import com.synrgy.kaboor.promo.adapter.VoucherAdapter
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.synrgy.common.utils.constant.ConstantTag


/**
 * Created by wahid on 1/19/2024.
 * Github github.com/wahidabd.
 */


class VoucherBottomSheetFragment : KaboorBottomSheet<FragmentVoucherBottomSheetBinding>() {

    companion object {
        fun newInstance(
            onSelectedPromo: (Voucher) -> Unit
        ): VoucherBottomSheetFragment = VoucherBottomSheetFragment().apply {
            this.onSelectedPromo = onSelectedPromo
        }
    }

    private var onSelectedPromo: (Voucher) -> Unit = {}

    private val promoAdapter by lazy {
        VoucherAdapter(requireContext())
    }

    override val tagName: String = ConstantTag.TAG_VOUCHER
    override fun getTitle(): String = getString(R.string.title_voucher)
    override fun setCancelButtonEnable(): Boolean = true
    override fun showButton(): Boolean = false

    override fun getContentBinding(inflater: LayoutInflater): FragmentVoucherBottomSheetBinding {
        return FragmentVoucherBottomSheetBinding.inflate(inflater)
    }

    override fun initUI() {
        super.initUI()

        with(contentBinding) {
            rvPromo.adapter = promoAdapter
        }
    }

    override fun initProcess() {
    }


}