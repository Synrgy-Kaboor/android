package com.synrgy.common.utils.constant

import com.synrgy.common.model.AirportData


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */


object DummyData {


    fun departure() = AirportData("Juanda Intl", "Surabaya")
    fun arrival() = AirportData("Soekarno Hatta Intl", "Jakarta")

    fun airportData(): List<AirportData> =
        listOf(
            AirportData(
                "Juanda Intl",
                "Surabaya"
            ),
            AirportData(
                "Soekarno Hatta Intl",
                "Jakarta"
            ),
            AirportData(
                "Adi Sumarmo Wiryokusumo",
                "Solo"
            ),
            AirportData(
                "Husein Sastranegara Intl",
                "Bandung"
            ),
            AirportData(
                "Sultan Hasanuddin Intl",
                "Makassar"
            ),
            AirportData(
                "Ngurah Rai Intl",
                "Denpasar"
            ),
            AirportData(
                "Kualanamu Intl",
                "Medan"
            ),
            AirportData(
                "Hang Nadim",
                "Batam"
            ),
            AirportData(
                "Halim Perdana Kusuma",
                "Jakarta"
            ),
            AirportData(
                "Sultan Syarif Kasim Ii",
                "Pekanbaru"
            ),
            AirportData(
                "Sultan Mahmud Badaruddin Ii",
                "Palembang"
            ),
            AirportData(
                "Minangkabau",
                "Padang"
            ),
            AirportData(
                "Sultan Aji Muhamad Sulaiman",
                "Balikpapan"
            ),
            AirportData(
                "Sultan Iskandarmuda",
                "Banda Aceh"
            ),
            AirportData(
                "Sultan Thaha",
                "Jambi"
            ),
            AirportData(
                "Hasanuddin Intl",
                "Ujung Pandang"
            ),
            AirportData(
                "El Tari",
                "Kupang"
            ),
            AirportData(
                "Supadio",
                "Pontianak"
            ),
            AirportData(
                "Sam Ratulangi",
                "Manado"
            ),
            AirportData(
                "Sepinggan",
                "Balikpapan"
            )
        )
}