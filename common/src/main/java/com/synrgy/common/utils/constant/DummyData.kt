package com.synrgy.common.utils.constant

import com.synrgy.common.model.AirportData


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */


object DummyData {

    fun departure() = AirportData("Juanda Intl", "Surabaya", "Indonesia", "SUB")
    fun arrival() = AirportData("Soekarno Hatta Intl", "Jakarta", "Indonesia", "CGK")

    fun airports(): List<AirportData> =
        listOf(
            AirportData(
                "Juanda Intl",
                "Surabaya",
                "Indonesia",
                "SUB"
            ),
            AirportData(
                "Soekarno Hatta Intl",
                "Jakarta",
                "Indonesia",
                "CGK"
            ),
            AirportData(
                "Adi Sumarmo Wiryokusumo",
                "Solo",
                "Indonesia",
                "SOC"
            ),
            AirportData(
                "Husein Sastranegara Intl",
                "Bandung",
                "Indonesia",
                "BDO"
            ),
            AirportData(
                "Sultan Hasanuddin Intl",
                "Makassar",
                "Indonesia",
                "UPG"
            ),
            AirportData(
                "Ngurah Rai Intl",
                "Denpasar",
                "Indonesia",
                "DPS"
            ),
            AirportData(
                "Kualanamu Intl",
                "Medan",
                "Indonesia",
                "KNO"
            ),
            AirportData(
                "Hang Nadim",
                "Batam",
                "Indonesia",
                "BTH"
            ),
            AirportData(
                "Halim Perdana Kusuma",
                "Jakarta",
                "Indonesia",
                "HLP"
            ),
            AirportData(
                "Sultan Syarif Kasim Ii",
                "Pekanbaru",
                "Indonesia",
                "PKU"
            ),
            AirportData(
                "Sultan Mahmud Badaruddin Ii",
                "Palembang",
                "Indonesia",
                "PLM"
            ),
            AirportData(
                "Minangkabau",
                "Padang",
                "Indonesia",
                "PDG"
            ),
            AirportData(
                "Sultan Aji Muhamad Sulaiman",
                "Balikpapan",
                "Indonesia",
                "BPN"
            ),
            AirportData(
                "Sultan Iskandarmuda",
                "Banda Aceh",
                "Indonesia",
                "BTJ"
            ),
            AirportData(
                "Sultan Thaha",
                "Jambi",
                "Indonesia",
                "DJB"
            ),
            AirportData(
                "Hasanuddin Intl",
                "Ujung Pandang",
                "Indonesia",
                "UPG"
            ),
            AirportData(
                "El Tari",
                "Kupang",
                "Indonesia",
                "KOE"
            ),
            AirportData(
                "Supadio",
                "Pontianak",
                "Indonesia",
                "PNK"
            ),
            AirportData(
                "Sam Ratulangi",
                "Manado",
                "Indonesia",
                "MDC"
            ),
            AirportData(
                "Sepinggan",
                "Balikpapan",
                "Indonesia",
                "BPN"
            )
        )

}