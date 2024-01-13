package com.synrgy.kaboor.utils.di

import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.authentication.otp.OtpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


val viewModelModule = module {
    viewModel { AuthViewModel(get(), get()) }
    viewModel { OtpViewModel(get()) }
}