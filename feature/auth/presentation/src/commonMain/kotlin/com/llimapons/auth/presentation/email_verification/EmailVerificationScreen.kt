package com.llimapons.auth.presentation.email_verification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.llimapons.core.designsystem.components.brand.EchoFailureIcon
import com.llimapons.core.designsystem.components.brand.EchoSuccessIcon
import com.llimapons.core.designsystem.components.buttons.EchoButton
import com.llimapons.core.designsystem.components.buttons.EchoButtonStyle
import com.llimapons.core.designsystem.components.layouts.EchoAdaptativeResultLayout
import com.llimapons.core.designsystem.components.layouts.EchoSimpleResultLayout
import com.llimapons.core.designsystem.theme.EchoTheme
import com.llimapons.core.designsystem.theme.extended
import echo.feature.auth.presentation.generated.resources.Res
import echo.feature.auth.presentation.generated.resources.close
import echo.feature.auth.presentation.generated.resources.email_verified_failed
import echo.feature.auth.presentation.generated.resources.email_verified_failed_desc
import echo.feature.auth.presentation.generated.resources.email_verified_successfully
import echo.feature.auth.presentation.generated.resources.email_verified_successfully_desc
import echo.feature.auth.presentation.generated.resources.login
import echo.feature.auth.presentation.generated.resources.verifying_account
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EmailVerificationRoot(
    viewModel: EmailVerificationViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EmailVerificationScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun EmailVerificationScreen(
    state: EmailVerificationState,
    onAction: (EmailVerificationAction) -> Unit,
) {

    EchoAdaptativeResultLayout {
        when {
            state.isVerifying -> {
                VerifyingContent(
                    Modifier.fillMaxWidth()
                )
            }
            state.isVerified -> {
                EchoSimpleResultLayout(
                    title = stringResource(Res.string.email_verified_successfully),
                    description = stringResource(Res.string.email_verified_successfully_desc),
                    icon = {
                        EchoSuccessIcon()
                    },
                    primaryButton = {
                        EchoButton(
                            text = stringResource(Res.string.login),
                            onClick = {
                                onAction(EmailVerificationAction.OnLoginClick)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }
            else -> {
                EchoSimpleResultLayout(
                    title = stringResource(Res.string.email_verified_failed),
                    description = stringResource(Res.string.email_verified_failed_desc),
                    icon = {
                        Spacer(modifier = Modifier.height(32.dp))
                        EchoFailureIcon(
                            modifier = Modifier
                                .size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    },
                    primaryButton = {
                        EchoButton(
                            text = stringResource(Res.string.close),
                            onClick = {
                                onAction(EmailVerificationAction.OnCloseClick)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            style = EchoButtonStyle.SECONDARY
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun VerifyingContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = 200.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(Res.string.verifying_account),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.extended.textSecondary
        )
    }
}

@Preview
@Composable
private fun EmailVerificationErrorPreview() {
    EchoTheme {
        EmailVerificationScreen(
            state = EmailVerificationState(),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun EmailVerificationSuccessPreview() {
    EchoTheme {
        EmailVerificationScreen(
            state = EmailVerificationState(
                isVerified =true
            ),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun EmailVerificationVerifyingPreview() {
    EchoTheme {
        EmailVerificationScreen(
            state = EmailVerificationState(
                isVerifying = true
            ),
            onAction = {}
        )
    }
}