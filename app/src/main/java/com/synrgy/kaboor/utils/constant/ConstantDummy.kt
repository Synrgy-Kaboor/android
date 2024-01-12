package com.synrgy.kaboor.utils.constant

import com.synrgy.common.R
import com.synrgy.domain.home.model.LastSeen
import com.synrgy.domain.home.model.Product
import com.synrgy.domain.ticket.plane.Ticket


/**
 * Created by wahid on 12/31/2023.
 * Github github.com/wahidabd.
 */


object ConstantDummy {
    fun promos(): List<Int> =
        listOf(
            R.drawable.sample_img_promo,
            R.drawable.sample_img_promo,
            R.drawable.sample_img_promo,
            R.drawable.sample_img_promo,
        )

    fun lastSees(): List<LastSeen> =
        listOf(
            LastSeen(
                1,
                "Stasiun Manggarai",
                R.drawable.sample_img_manggarai
            ),
            LastSeen(
                2,
                "Bandara Juanda",
                R.drawable.sample_img_juanda
            ),
            LastSeen(
                3,
                "Stasiun Manggarai",
                R.drawable.sample_img_manggarai
            )
        )

    fun favoriteDestinations(): List<Product> =
        listOf(
            Product(
                1,
                "Bali",
                "Ekonomi",
                4.5f,
                900000,
                image = R.drawable.sample_img_bali
            ),
            Product(
                2,
                "Jogja",
                "Ekonomi",
                5.0f,
                950000,
                image = R.drawable.sample_img_jogja
            ),
            Product(
                3,
                "Jakarta",
                "Ekonomi",
                4.5f,
                900000,
                image = R.drawable.sample_img_jakarta
            ),
            Product(
                4,
                "Bali",
                "Hotel",
                4.5f,
                1000000,
                image = R.drawable.sample_img_bali
            ),
        )

    fun rentalCars(): List<Product> =
        listOf(
            Product(
                1,
                "Toyota Avanza",
                "Mobil",
                4.5f,
                900000,
                image = R.drawable.sample_img_car
            ),
            Product(
                2,
                "Toyota Avanza",
                "Mobil",
                4.5f,
                900000,
                image = R.drawable.sample_img_car
            ),
            Product(
                3,
                "Toyota Avanza",
                "Mobil",
                4.5f,
                900000,
                image = R.drawable.sample_img_car
            ),
            Product(
                4,
                "Toyota Avanza",
                "Mobil",
                4.5f,
                900000,
                image = R.drawable.sample_img_car
            ),
        )

    fun planeTicket(): List<Ticket> =
        listOf(
            Ticket(
                1,
                "Garuda Indonesia",
                "Ekonomi",
                "Surabaya",
                "05.00 WIB",
                "Jakarta",
                "09.00 WIB",
                "Durasi 4 Jam",
                1274000,
                image = R.drawable.sample_img_garuda
            ),
            Ticket(
                2,
                "Lion Air",
                "Ekonomi",
                "Surabaya",
                "05.00 WIB",
                "Jakarta",
                "09.00 WIB",
                "Durasi 4 Jam",
                1274000,
                image = R.drawable.sample_img_lion_air
            ),
            Ticket(
                3,
                "Batik Air",
                "Ekonomi",
                "Surabaya",
                "07.00 WIB",
                "Jakarta",
                "10.05 WIB",
                "Durasi 4 Jam 5 Menit",
                1274000,
                image = R.drawable.sample_img_batik_air
            ),
            Ticket(
                4,
                "Garuda Indonesia",
                "Ekonomi",
                "Surabaya",
                "05.00 WIB",
                "Jakarta",
                "09.00 WIB",
                "Durasi 4 Jam",
                1274000,
                image = R.drawable.sample_img_garuda
            ),
            Ticket(
                5,
                "Lion Air",
                "Ekonomi",
                "Surabaya",
                "05.00 WIB",
                "Jakarta",
                "09.00 WIB",
                "Durasi 4 Jam",
                1274000,
                image = R.drawable.sample_img_lion_air
            ),
            Ticket(
                6,
                "Batik Air",
                "Ekonomi",
                "Surabaya",
                "07.00 WIB",
                "Jakarta",
                "10.05 WIB",
                "Durasi 4 Jam 5 Menit",
                1274000,
                image = R.drawable.sample_img_batik_air
            )
        )
}