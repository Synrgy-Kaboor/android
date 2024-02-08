package com.synrgy.domain.promo

import com.synrgy.common.data.ListWrapper
import com.synrgy.data.promo.PromoRepository
import com.synrgy.data.promo.model.response.VoucherResponse
import com.synrgy.domain.promo.mapper.toDomain
import com.synrgy.domain.promo.model.response.Voucher
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/22/2024.
 * Github github.com/wahidabd.
 */


class PromoInteractor(
    private val repository: PromoRepository
) : PromoUseCase {

    override suspend fun getVoucher(): Flow<Resource<List<Voucher>>> {
        return object : InternetBoundResource<List<Voucher>, ListWrapper<VoucherResponse>>() {
            override suspend fun createCall(): Flow<Resource<ListWrapper<VoucherResponse>>> {
                return repository.getVoucher()
            }

            override suspend fun saveCallRequest(data: ListWrapper<VoucherResponse>): List<Voucher> {
                return data.data.map { it.toDomain() }
            }
        }.asFlow()
    }

}