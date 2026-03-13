package com.llimapons.core.domain.validation

data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasDigit: Boolean = false,
    val hasUppercase: Boolean = false,
){
    fun isValidPassword(): Boolean {
        return hasMinLength && hasDigit && hasUppercase
    }
}
