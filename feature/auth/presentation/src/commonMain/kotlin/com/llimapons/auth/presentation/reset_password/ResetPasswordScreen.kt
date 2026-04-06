package com.llimapons.auth.presentation.reset_password

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.llimapons.core.designsystem.components.brand.EchoBrandLogo
import com.llimapons.core.designsystem.components.buttons.EchoButton
import com.llimapons.core.designsystem.components.layouts.EchoAdaptativeFormLayout
import com.llimapons.core.designsystem.components.textfields.EchoPasswordTextField
import com.llimapons.core.designsystem.components.textfields.EchoTextField
import com.llimapons.core.designsystem.theme.EchoTheme
import com.llimapons.core.designsystem.theme.extended
import echo.feature.auth.presentation.generated.resources.Res
import echo.feature.auth.presentation.generated.resources.new_password
import echo.feature.auth.presentation.generated.resources.password
import echo.feature.auth.presentation.generated.resources.password_hint
import echo.feature.auth.presentation.generated.resources.reset_password_successfully
import echo.feature.auth.presentation.generated.resources.set_new_password
import echo.feature.auth.presentation.generated.resources.submit
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ResetPasswordRoot(
    viewModel: ResetPasswordViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ResetPasswordScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ResetPasswordScreen(
    state: ResetPasswordState,
    onAction: (ResetPasswordAction) -> Unit,
) {
    EchoAdaptativeFormLayout(
        headerText = stringResource(Res.string.set_new_password),
        logo = { EchoBrandLogo() },
        errorText = state.errorText?.asString()
    ) {
        EchoPasswordTextField(
            state = state.passwordTextState,
            modifier = Modifier.fillMaxWidth(),
            placeHolder = stringResource(Res.string.password),
            title = stringResource(Res.string.new_password),
            supportingText = stringResource(Res.string.password_hint),
            isPasswordVisible = state.isPasswordVisible,
            onToggleVisibilityClick = {
                onAction(ResetPasswordAction.OnTogglePasswordVisibility)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        EchoButton(
            text = stringResource(Res.string.submit),
            onClick = {
                onAction(ResetPasswordAction.OnSubmitClick)
            },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = !state.isLoading && state.canSubmit,
            isLoading = state.isLoading
        )
        if (state.isResetSuccessful) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.reset_password_successfully),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.extended.success,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    EchoTheme {
        ResetPasswordScreen(
            state = ResetPasswordState(),
            onAction = {}
        )
    }
}