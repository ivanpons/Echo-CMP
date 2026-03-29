package com.llimapons.auth.presentation.register_success

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.llimapons.core.designsystem.components.brand.EchoSuccessIcon
import com.llimapons.core.designsystem.components.buttons.EchoButton
import com.llimapons.core.designsystem.components.buttons.EchoButtonStyle
import com.llimapons.core.designsystem.components.layouts.EchoAdaptativeResultLayout
import com.llimapons.core.designsystem.components.layouts.EchoSimpleResultLayout
import com.llimapons.core.designsystem.components.layouts.EchoSnackbarScaffold
import com.llimapons.core.designsystem.theme.EchoTheme
import com.llimapons.core.presentation.util.ObserveAsEvents
import echo.feature.auth.presentation.generated.resources.Res
import echo.feature.auth.presentation.generated.resources.account_successfully_created
import echo.feature.auth.presentation.generated.resources.login
import echo.feature.auth.presentation.generated.resources.resend_verification_email
import echo.feature.auth.presentation.generated.resources.verification_email_sent_to_x
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterSuccessRoot(
    viewModel: RegisterSuccessViewModel = koinViewModel(),
    onLoginClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegisterSuccessEvent.ResendVerificationEmailSuccess -> {
                snackbarHostState.showSnackbar(
                    message = getString(
                        Res.string.resend_verification_email
                    )
                )
            }
        }
    }

    RegisterSuccessScreen(
        state = state,
        onAction = { action ->
            when(action) {
                RegisterSuccessAction.OnLoginClick -> onLoginClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun RegisterSuccessScreen(
    state: RegisterSuccessState,
    onAction: (RegisterSuccessAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    EchoSnackbarScaffold(
        snackbarHostState = snackbarHostState
    ) {
        EchoAdaptativeResultLayout {
            EchoSimpleResultLayout(
                title = stringResource(Res.string.account_successfully_created),
                description = stringResource(
                    Res.string.verification_email_sent_to_x,
                    state.registeredEmail
                ),
                icon = {
                    EchoSuccessIcon()
                },
                primaryButton = {
                    EchoButton(
                        text = stringResource(Res.string.login),
                        onClick = { onAction(RegisterSuccessAction.OnLoginClick) },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                },
                secondaryButton = {
                    EchoButton(
                        text = stringResource(Res.string.resend_verification_email),
                        onClick = { onAction(RegisterSuccessAction.OnResendEVerificationClick) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = !state.isResendingVerificationEmail,
                        isLoading = state.isResendingVerificationEmail,
                        style = EchoButtonStyle.SECONDARY,
                    )
                },
                secondaryError = state.resendVerificationError?.asString()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    EchoTheme {
        RegisterSuccessScreen(
            state = RegisterSuccessState(
                registeredEmail = "mio@llimapons.com"
            ),
            onAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}