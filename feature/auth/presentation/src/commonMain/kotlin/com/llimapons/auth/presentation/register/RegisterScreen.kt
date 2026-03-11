package com.llimapons.auth.presentation.register

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.llimapons.core.designsystem.components.brand.EchoBrandLogo
import com.llimapons.core.designsystem.components.buttons.EchoButton
import com.llimapons.core.designsystem.components.buttons.EchoButtonStyle
import com.llimapons.core.designsystem.components.layouts.EchoAdaptativeFormLayout
import com.llimapons.core.designsystem.components.layouts.EchoSnackbarScaffold
import com.llimapons.core.designsystem.components.textfields.EchoPasswordTextField
import com.llimapons.core.designsystem.components.textfields.EchoTextField
import com.llimapons.core.designsystem.theme.EchoTheme
import echo.feature.auth.presentation.generated.resources.Res
import echo.feature.auth.presentation.generated.resources.email
import echo.feature.auth.presentation.generated.resources.email_placeholder
import echo.feature.auth.presentation.generated.resources.login
import echo.feature.auth.presentation.generated.resources.password
import echo.feature.auth.presentation.generated.resources.password_hint
import echo.feature.auth.presentation.generated.resources.register
import echo.feature.auth.presentation.generated.resources.username
import echo.feature.auth.presentation.generated.resources.username_hint
import echo.feature.auth.presentation.generated.resources.username_placeholder
import echo.feature.auth.presentation.generated.resources.welcome_to_echo
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    RegisterScreen(
        state = state,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    EchoSnackbarScaffold(
        snackbarHostState = snackbarHostState
    ) {
        EchoAdaptativeFormLayout(
            headerText = stringResource(Res.string.welcome_to_echo),
            errorText = state.registrationError?.asString(),
            logo = { EchoBrandLogo() }
        ) {
            EchoTextField(
                state = state.usernameTextState,
                placeHolder = stringResource(Res.string.username_placeholder),
                title = stringResource(Res.string.username),
                supportingText = state.usernameError?.asString()
                    ?: stringResource(Res.string.username_hint),
                isError = state.usernameError != null,
                onFocusChanged = {isFocused ->
                    onAction(RegisterAction.OnInputTextFocusGain)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            EchoTextField(
                state = state.emailTextState,
                placeHolder = stringResource(Res.string.email_placeholder),
                title = stringResource(Res.string.email),
                supportingText = state.emailError?.asString(),
                isError = state.emailError != null,
                onFocusChanged = {isFocused ->
                    onAction(RegisterAction.OnInputTextFocusGain)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            EchoPasswordTextField(
                state = state.passwordTextState,
                placeHolder = stringResource(Res.string.password),
                title = stringResource(Res.string.password),
                supportingText = state.passwordError?.asString()
                    ?: stringResource(Res.string.password_hint),
                isError = state.passwordError != null,
                onFocusChanged = {isFocused ->
                    onAction(RegisterAction.OnInputTextFocusGain)
                },
                onToggleVisibilityClick = {
                    onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                },
                isPasswordVisible = state.isPasswordVisible
            )
            Spacer(modifier = Modifier.height(16.dp))

            EchoButton(
                text = stringResource(Res.string.register),
                onClick = {
                    onAction(RegisterAction.OnRegisterClick)
                },
                enabled = state.canRegister,
                isLoading = state.isRegistering,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            EchoButton(
                text = stringResource(Res.string.login),
                onClick = {
                    onAction(RegisterAction.OnLoginClick)
                },
                style = EchoButtonStyle.SECONDARY,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }

}

@Preview
@Composable
private fun Preview() {
    EchoTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}