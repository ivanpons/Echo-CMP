package com.llimapons.auth.presentation.email_verification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llimapons.core.domain.auth.AuthService
import com.llimapons.core.domain.util.onFailure
import com.llimapons.core.domain.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EmailVerificationViewModel(
    private val authService: AuthService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val token = savedStateHandle.get<String>("token")

    private val _state = MutableStateFlow(EmailVerificationState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                verifyEmail()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = EmailVerificationState()
        )

    //NO-OP
    fun onAction(action: EmailVerificationAction) = Unit

    private fun verifyEmail() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isVerifying = true
            )
            authService.verifyEmail(token ?: "Invalid Token")
                .onSuccess {
                    _state.value = _state.value.copy(
                        isVerifying = false,
                        isVerified = true
                    )
                }
                .onFailure {
                    _state.value = _state.value.copy(
                        isVerifying = false,
                        isVerified = false
                    )
                }
        }
    }

}