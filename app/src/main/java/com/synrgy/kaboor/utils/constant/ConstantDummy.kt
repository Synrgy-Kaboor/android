package com.synrgy.kaboor.utils.constant

import com.synrgy.common.R
import com.synrgy.common.utils.Selectable
import com.synrgy.domain.flight.model.response.OrderFlight
import com.synrgy.domain.home.model.LastSeen
import com.synrgy.domain.home.model.Product
import com.synrgy.domain.notification.model.response.AirportCity
import com.synrgy.domain.notification.model.response.Notification
import com.synrgy.domain.notification.model.response.PriceNotification
import com.synrgy.domain.promo.model.response.Bank
import com.synrgy.domain.user.model.response.HelpCenter
import com.synrgy.domain.user.model.response.Passport


/**
 * Created by wahid on 12/31/2023.
 * Github github.com/wahidabd.
 */


object ConstantDummy {

    fun priceNotifications(): List<PriceNotification> =
        listOf(
            PriceNotification(
                AirportCity("Surabaya", "SUB"),
                AirportCity("Jakarta", "JKT"),
                "20 Des 2023",
                "21 Des 2023",
                2,
                1,
                0,
                "E",
                1000000,
                2000000
            ),
            PriceNotification(
                AirportCity("Surabaya", "SUB"),
                AirportCity("Jakarta", "JKT"),
                "20 Des 2023",
                "21 Des 2023",
                2,
                1,
                0,
                "F",
                1000000,
                2000000
            ),
            PriceNotification(
                AirportCity("Surabaya", "SUB"),
                AirportCity("Jakarta", "JKT"),
                "20 Des 2023",
                "21 Des 2023",
                2,
                1,
                0,
                "B",
                1000000,
                2000000
            ),
            PriceNotification(
                AirportCity("Surabaya", "SUB"),
                AirportCity("Jakarta", "JKT"),
                "20 Des 2023",
                "21 Des 2023",
                2,
                1,
                0,
                "EP",
                1000000,
                2000000
            )
        )

    fun allNotifications(): List<Notification> =
        listOf(
            Notification(
                1,
                "Pembayaran Berhasil",
                "Tiket pesawat Lion Air Anda sudah dikonfirmasi untuk penerbangan ke Jakarta (CGK) pada tanggal 20 Des2024. Siapkan perjalanan Anda dengan semangat! \uD83C\uDF1F",
                "20 Des",
                R.drawable.ic_plane
            ),
            Notification(
                2,
                "Pesawat Delay",
                "Tiket pesawat Lion Air Anda sudah dikonfirmasi untuk penerbangan ke Jakarta (CGK) pada tanggal 20 Des2024. Siapkan perjalanan Anda dengan semangat! \uD83C\uDF1F",
                "20 Des",
                R.drawable.ic_plane
            )
        )

    fun passport(): List<Passport> =
        listOf(
            Passport(
                1,
                "Andre Hutson",
                "123456789",
                "12/12/2024",
                "Indonesia"
            ),
        )

    fun helpCenters(): List<HelpCenter> =
        listOf(
            HelpCenter(
                "Apakah Pembayaran Sudah Diterima",
                "Lorem ipsum"
            ),
            HelpCenter(
                "Cara Membayar Pesanan Saya",
                "Lorem ipsum"
            ),
            HelpCenter(
                "Cara Mengubah Nomor Handphone?",
                "Lorem ipsum"
            ),
            HelpCenter(
                "Cara Mengubah Email",
                "Lorem ipsum"
            ),
            HelpCenter(
                "Transaksi Saya Tidak Berhasil",
                "Lorem ipsum"
            ),
            HelpCenter(
                "Pendaftaran Akun Gagal?",
                "Lorem ipsum"
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

    fun bankPaymentMethod(): List<Selectable<Bank>> =
        listOf(
            Selectable(
                Bank(
                    code = "BRI",
                    methodName = "Bank BRI",
                    imageUrl = "https://logowik.com/content/uploads/images/bri-20209664.logowik.com.webp",
                    atm = R.array.atm_bri,
                    internet = R.array.internet_bri,
                    mobile = R.array.mobile_bri
                )
            ),
            Selectable(
                Bank(
                    code = "BNI",
                    methodName = "Bank BNI",
                    imageUrl = "https://logowik.com/content/uploads/images/bni-bank-negara-indonesia8078.logowik.com.webp",
                    atm = R.array.atm_bni,
                    internet = R.array.internet_bni,
                    mobile = R.array.mobile_bni
                )
            ),
            Selectable(
                Bank(
                    code = "BCA",
                    methodName = "Bank BCA",
                    imageUrl = "https://www.bca.co.id/-/media/Feature/Card/List-Card/Tentang-BCA/Brand-Assets/Logo-BCA/Logo-BCA_Biru.png",
                    atm = R.array.atm_bca,
                    internet = R.array.internet_bca,
                    mobile = R.array.mobile_bca
                )
            ),
            Selectable(
                Bank(
                    code = "Mandiri",
                    methodName = "Bank Mandiri",
                    imageUrl = "https://logowik.com/content/uploads/images/bank-mandiri.jpg",
                    atm = R.array.atm_mandiri,
                    internet = R.array.internet_mandiri,
                    mobile = R.array.mobile_mandiri
                )
            )
        )

    fun activeOrderFlight(): List<OrderFlight> =
        listOf(
            OrderFlight(
                "12122023994",
                "Jakarta",
                "Surabaya",
                "Sen, 24 Des 2023",
                "08.00 WIB",
                "Sedang di Proses",
                image = R.drawable.sample_img_garuda
            ),
            OrderFlight(
                "12122023994",
                "Surabaya",
                "Jakarta",
                "Sel, 25 Des 2023",
                "08.00 WIB",
                "E-Tiket Terbit",
                image = R.drawable.sample_img_garuda
            )
        )

    fun orderHistoryFlight(): List<OrderFlight> =
        listOf(
            OrderFlight(
                "12122023994",
                "Jakarta",
                "Surabaya",
                "Sen, 24 Des 2023",
                "08.00 WIB",
                "Selesai",
                image = R.drawable.sample_img_garuda
            ),
            OrderFlight(
                "12122023994",
                "Surabaya",
                "Jakarta",
                "Sel, 25 Des 2023",
                "08.00 WIB",
                "Selesai",
                image = R.drawable.sample_img_garuda
            )
        )
}