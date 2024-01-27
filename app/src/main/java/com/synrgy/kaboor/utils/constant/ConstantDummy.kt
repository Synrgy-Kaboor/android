package com.synrgy.kaboor.utils.constant

import com.synrgy.common.R
import com.synrgy.common.utils.Selectable
import com.synrgy.data.payment.model.response.Promo
import com.synrgy.domain.home.model.LastSeen
import com.synrgy.domain.home.model.Product
import com.synrgy.domain.payment.Bank
import com.synrgy.domain.booking.Ticket
import com.synrgy.domain.booking.model.response.Airport


/**
 * Created by wahid on 12/31/2023.
 * Github github.com/wahidabd.
 */


object ConstantDummy {

    fun departure(): Airport = Airport("Surabaya", "SUB")
    fun arrival(): Airport = Airport("Jakarta", "JKT")

    fun airports(): List<Airport> =
        listOf(
            Airport("Surabaya", "SUB"),
            Airport("Jakarta", "JKT"),
            Airport("Bandung", "BDO"),
            Airport("Solo", "SOC"),
            Airport("Makassar", "UPG"),
            Airport("Denpasar", "DPS"),
            Airport("Medan", "KNO"),
            Airport("Yogyakarta", "JOG"),
            Airport("Semarang", "SRG"),
            Airport("Palembang", "PLM"),
            Airport("Balikpapan", "BPN"),
            Airport("Pekanbaru", "PKU"),
            Airport("Banjarmasin", "BDJ"),
            Airport("Batam", "BTH"),
            Airport("Padang", "PDG"),
            Airport("Lombok", "LOP"),
            Airport("Banda Aceh", "BTJ"),
            Airport("Pekanbaru", "PKU"),
            Airport("Bengkulu", "BKS"),
            Airport("Tanjung Pinang", "TNJ"),
            Airport("Tanjung Pandan", "TJQ"),
            Airport("Tanjung Selor", "TJS"),
            Airport("Tanjung Redeb", "BEJ"),
            Airport("Tanjung Warukin", "TJG"),
            Airport("Tanjung Karang", "TKG"),
            Airport("Gorontalo", "GTO"),
        )

    fun vouchers(): List<Promo> =
        listOf(
            Promo(
                "Voucher Promo",
                "Voucher Promo",
                "Discount Hingga Rp 100.000 Buat Keliling Indonesia",
                "TEMANKABOOR",
                100000,
                26000,
            ),
            Promo(
                "Voucher Promo",
                "Voucher Promo",
                "Discount Hingga Rp 100.000 Buat Keliling Indonesia",
                "TEMANKABOOR",
                100000,
                26000,
            ),
            Promo(
                "Voucher Promo",
                "Voucher Promo",
                "Discount Hingga Rp 100.000 Buat Keliling Indonesia",
                "TEMANKABOOR",
                100000,
                26000,
            ),
            Promo(
                "Voucher Promo",
                "Voucher Promo",
                "Discount Hingga Rp 100.000 Buat Keliling Indonesia",
                "TEMANKABOOR",
                100000,
                26000,
            ),
        )

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
                "",
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
                "",
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
                "",
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
                "",
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
                "",
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
                "",
                1274000,
                image = R.drawable.sample_img_batik_air
            )
        )

    fun roundTripPlaneTicket(): List<Ticket> =
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
                "Sen, 24 Des 2023",
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
                "Sel, 25 Des 2023",
                1274000,
                image = R.drawable.sample_img_lion_air
            )
        )

    fun bankPaymentMethod(): List<Selectable<Bank>> =
        listOf(
            Selectable(
                Bank(
                    id = 1,
                    methodName = "Bank BRI",
                    imageUrl = "https://asset-2.tstatic.net/madura/foto/bank/images/logo-bri-dibuka-lowongan-kerja-di-bank-bri-untuk-lulusan-s1-dan-s2-untuk-september.jpg"
                )
            ),
            Selectable(
                Bank(
                    2,
                    "Bank BNI",
                    imageUrl ="https://logowik.com/content/uploads/images/bni-bank-negara-indonesia8078.logowik.com.webp"
                )
            ),
            Selectable(
                Bank(
                    3,
                    "Bank BCA",
                    imageUrl = "https://www.bca.co.id/-/media/Feature/Card/List-Card/Tentang-BCA/Brand-Assets/Logo-BCA/Logo-BCA_Biru.png"
                )
            ),
        )
}