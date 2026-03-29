package com.llimapons.auth.presentation.login

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llimapons.auth.domain.EmailValidator
import com.llimapons.core.domain.auth.AuthService
import com.llimapons.core.domain.util.DataError
import com.llimapons.core.domain.util.onFailure
import com.llimapons.core.domain.util.onSuccess
import com.llimapons.core.presentation.util.UiText
import com.llimapons.core.presentation.util.toUiText
import echo.feature.auth.presentation.generated.resources.Res
import echo.feature.auth.presentation.generated.resources.error_email_not_verified
import echo.feature.auth.presentation.generated.resources.error_invalid_credentials
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: AuthService
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()


    private val _state = MutableStateFlow(LoginState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeValidationsStates()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LoginState()
        )

    private val isEmailValidFlow = snapshotFlow { state.value.emailTextFieldState.text.toString() }
        .map { email -> EmailValidator.validate(email) }
        .distinctUntilChanged()

    private val isPasswordNotBlankFlow = snapshotFlow { state.value.passwordTextFieldState.text.toString() }
        .map { password -> password.isNotBlank() }
        .distinctUntilChanged()

    private val isLoggingInFlow = state
        .map { it.isLoggingIn }
        .distinctUntilChanged()

    private fun observeValidationsStates(){
        combine(
            isPasswordNotBlankFlow,
            isEmailValidFlow,
            isLoggingInFlow)
        { isPasswordValid, isEmailValid , isLoggingIn->
            val allValid = isPasswordValid && isEmailValid
            _state.update {
                it.copy(
                   canLogin = !isLoggingIn && allValid
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.OnLoginClick -> login()
            LoginAction.OnTogglePasswordVisibility -> {
                _state.update { it.copy(
                    isPasswordVisible = !it.isPasswordVisible
                )
                }
            }
            else -> Unit
        }
    }

    private fun login() {
        if (!_state.value.canLogin) {
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoggingIn = true
                )
            }
            val email = state.value.emailTextFieldState.text.toString()
            val password = state.value.passwordTextFieldState.text.toString()
            authService
                .login(
                    email = email,
                    password = password
                )
                .onFailure { error ->
                    val errorMessage = when(error){
                        DataError.Remote.UNAUTHORIZED -> UiText.Resource(Res.string.error_invalid_credentials)
                        DataError.Remote.FORBIDDEN -> UiText.Resource(Res.string.error_email_not_verified)
                        else -> error.toUiText()
                    }
                    _state.update {
                        it.copy(
                            isLoggingIn = false,
                            error = errorMessage
                        )
                    }
                }
                .onSuccess { authInfo ->
                    _state.update {
                        it.copy(
                            isLoggingIn = false
                        )
                    }
                    eventChannel.send(LoginEvent.Success)
                }
        }
    }

}