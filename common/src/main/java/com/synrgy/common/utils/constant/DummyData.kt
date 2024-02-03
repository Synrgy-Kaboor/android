package com.synrgy.common.utils.constant

import com.synrgy.common.model.AirportData


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */


object DummyData {


    fun departure() = AirportData(1, "SUB", "Juanda Intl", 7)
    fun arrival() = AirportData(2, "CKG", "Soekarno Hatta Intl", 7)

    fun airportData(): List<AirportData> =
        listOf(
            AirportData(
                1,
                "Juanda Intl",
                "SUB",
                7
            ),
            AirportData(
                2,
                "Soekarno Hatta Intl",
                "CKG",
                7
            ),
            AirportData(
                3,
                "Adi Soemarmo Intl",
                "SOC",
                7
            ),
            AirportData(
                4,
                "Adi Sumarmo Intl",
                "SOC",
                7
            ),
            AirportData(
                5,
                "Adi Sumarmo Intl",
                "SOC",
                7
            ),
            AirportData(
                6,
                "Adi Sumarmo Intl",
                "SOC",
                7
            ),
            AirportData(
                7,
                "Adi Sumarmo Intl",
                "SOC",
                7
            ),
        )
}