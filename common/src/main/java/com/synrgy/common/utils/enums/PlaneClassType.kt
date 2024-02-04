package com.synrgy.common.utils.enums


/**
 * Created by wahid on 1/6/2024.
 * Github github.com/wahidabd.
 */


enum class PlaneClassType(val label: String, val description: String, val code: String) {
    EKONOMI("Ekonomi", "Tiket pesawat murah, sesuai kebutuhan Anda", "E"),
    EKONOMI_PREMIUM("Ekonomi Premium", "Terbang hemat, tapi tetap nyaman", "EP"),
    BISNIS("Bisnis", "Terbang berkesan dengan check-in tanpa antrian dan kursi istimewa", "B"),
    FIRST_CLASS("First Class", "Terbang berkelas, dengan layanan yang tak terlupakan.", "F");

    companion object {
        fun getByCode(code: String?): PlaneClassType {
            return entries.first {
                it.code.contentEquals(code, true)
            }
        }
    }
}