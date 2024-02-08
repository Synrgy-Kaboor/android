package com.synrgy.kaboor.promo

import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import com.synrgy.common.presentation.KaboorBottomSheet
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.ext.lowerContains
import com.synrgy.domain.promo.model.response.Voucher
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.FragmentVoucherBottomSheetBinding
import com.synrgy.kaboor.promo.adapter.VoucherAdapter
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showEmptyState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.observerLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


/**
 * Created by wahid on 1/19/2024.
 * Github github.com/wahidabd.
 */


class VoucherBottomSheetFragment : KaboorBottomSheet<FragmentVoucherBottomSheetBinding>(),
    KoinComponent {

    companion object {
        fun newInstance(
            price: Long = 0L,
            paymentMethod: String = emptyString(),
            onSelectedPromo: (Voucher) -> Unit
        ): VoucherBottomSheetFragment = VoucherBottomSheetFragment().apply {
            this.paymentMethod = paymentMethod
            this.price = price
            this.onSelectedPromo = onSelectedPromo
        }
    }

    private val viewModel: PromoViewModel by inject()

    private var vouchers = mutableListOf<Voucher>()
    private var onSelectedPromo: (Voucher) -> Unit = {}

    private val promoAdapter by lazy {
        VoucherAdapter(requireContext(), Pair(paymentMethod, price)) {
            onSelectedPromo.invoke(it)
            dismiss()
        }
    }

    private var paymentMethod: String = emptyString()
    private var price: Long = 0L

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

            etSearch.doAfterTextChanged {
                filter(it.toString())
            }
        }
    }

    override fun initProcess() {
        super.initProcess()
        viewModel.getVouchers()
    }

    override fun initObservers() {
        super.initObservers()
        with(contentBinding) {
            viewModel.vouchers.observerLiveData(
                this@VoucherBottomSheetFragment,
                onLoading = { msv.showLoadingState() },
                onEmpty = { msv.showEmptyState() },
                onFailure = { _, message ->
                    msv.showEmptyState(message)
                },
                onSuccess = { vouchers ->
                    msv.showDefaultState()
                    promoAdapter.setData = vouchers
                    this@VoucherBottomSheetFragment.vouchers = vouchers.toMutableList()
                }
            )
        }
    }


    private fun filter(text: String) {
        val query = ArrayList<Voucher>()
        vouchers.forEach {
            if (it.code.lowerContains(text)
            ) {
                query.add(it)
            }
        }
        if (query.isEmpty()) contentBinding.msv.showEmptyState()
        else {
            contentBinding.msv.showDefaultState()
            promoAdapter.filter(query)
        }
    }

}