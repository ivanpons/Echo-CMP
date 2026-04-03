package com.llimapons.auth.presentation.di

import com.llimapons.auth.presentation.email_verification.EmailVerificationViewModel
import com.llimapons.auth.presentation.forgot_password.ForgotPasswordViewModel
import com.llimapons.auth.presentation.login.LoginViewModel
import com.llimapons.auth.presentation.register.RegisterViewModel
import com.llimapons.auth.presentation.register_success.RegisterSuccessViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RegisterSuccessViewModel)
    viewModelOf(::EmailVerificationViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}
