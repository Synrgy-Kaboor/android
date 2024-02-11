package com.synrgy.kaboor.utils.di

import com.synrgy.kaboor.account.AccountViewModel
import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.authentication.forgot.ForgotPasswordViewModel
import com.synrgy.kaboor.authentication.otp.OtpViewModel
import com.synrgy.kaboor.booking.viewmodel.FlightViewModel
import com.synrgy.kaboor.booking.viewmodel.PassengerViewModel
import com.synrgy.kaboor.home.SharedViewModel
import com.synrgy.kaboor.order.OrderViewModel
import com.synrgy.kaboor.payment.BookingViewModel
import com.synrgy.kaboor.promo.PromoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


val viewModelModule = module {
    viewModel { AuthViewModel(get(), get()) }
    viewModel { OtpViewModel(get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { SharedViewModel(get()) }
    viewModel { FlightViewModel(get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { PassengerViewModel(get()) }
    viewModel { PromoViewModel(get()) }
    viewModel { BookingViewModel(get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { OrderViewModel(get()) }
}