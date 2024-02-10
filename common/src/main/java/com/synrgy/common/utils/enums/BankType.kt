package com.synrgy.common.utils.enums

import com.synrgy.common.R
import com.synrgy.common.model.BankData


/**
 * Created by wahid on 2/10/2024.
 * Github github.com/wahidabd.
 */


enum class BankType(val bank: BankData) {
    BCA(
        BankData(
            code = "BCA",
            methodName = "Bank BCA",
            imageUrl = "https://www.bca.co.id/-/media/Feature/Card/List-Card/Tentang-BCA/Brand-Assets/Logo-BCA/Logo-BCA_Biru.png",
            atm = R.array.atm_bca,
            internet = R.array.internet_bca,
            mobile = R.array.mobile_bca
        )
    ),
    BNI(
        BankData(
            code = "BNI",
            methodName = "Bank BNI",
            imageUrl = "https://logowik.com/content/uploads/images/bni-bank-negara-indonesia8078.logowik.com.webp",
            atm = R.array.atm_bni,
            internet = R.array.internet_bni,
            mobile = R.array.mobile_bni
        )
    ),
    BRI(
        BankData(
            code = "BRI",
            methodName = "Bank BRI",
            imageUrl = "https://logowik.com/content/uploads/images/bri-20209664.logowik.com.webp",
            atm = R.array.atm_bri,
            internet = R.array.internet_bri,
            mobile = R.array.mobile_bri
        )
    ),
    MANDIRI(
        BankData(
            code = "Mandiri",
            methodName = "Bank Mandiri",
            imageUrl = "https://logowik.com/content/uploads/images/bank-mandiri.jpg",
            atm = R.array.atm_mandiri,
            internet = R.array.internet_mandiri,
            mobile = R.array.mobile_mandiri
        )
    );

    companion object {
        fun getBankType(code: String): BankType {
            return when (code) {
                BCA.bank.code -> BCA
                BNI.bank.code -> BNI
                BRI.bank.code -> BRI
                MANDIRI.bank.code -> MANDIRI
                else -> throw IllegalArgumentException("Bank type not found")
            }
        }
    }
}