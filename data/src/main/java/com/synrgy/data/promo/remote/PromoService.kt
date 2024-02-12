package com.synrgy.data.promo.remote

import com.synrgy.common.data.ListWrapper
import com.synrgy.data.promo.model.response.VoucherResponse
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by wahid on 2/6/2024.
 * Github github.com/wahidabd.
 */


interface PromoService {

    @GET("/api/v1/voucher")
    suspend fun geVoucher(): Response<ListWrapper<VoucherResponse>>

}