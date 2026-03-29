package com.llimapons.auth.presentation.login

sealed interface LoginAction {
    data object OnLoginClick : LoginAction
    data object OnForgotPasswordClick : LoginAction
    data object OnSignUpClick : LoginAction
    data object OnTogglePasswordVisibility : LoginAction
}