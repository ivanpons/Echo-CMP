package com.llimapons.auth.presentation.reset_password

sealed interface ResetPasswordAction {
    data object OnSubmitClick : ResetPasswordAction
    data object OnTogglePasswordVisibility : ResetPasswordAction
}
