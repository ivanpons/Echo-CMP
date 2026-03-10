package com.llimapons.core.designsystem.components.textfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.llimapons.core.designsystem.theme.EchoTheme
import com.llimapons.core.designsystem.theme.extended
import echo.core.designsystem.generated.resources.Res
import echo.core.designsystem.generated.resources.eye_icon
import echo.core.designsystem.generated.resources.eye_off_icon
import echo.core.designsystem.generated.resources.hide_password
import echo.core.designsystem.generated.resources.show_password
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EchoPasswordTextField(
    state: TextFieldState,
    isPasswordVisible: Boolean,
    onToggleVisibilityClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeHolder: String? = null,
    title: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    onFocusChanged: (Boolean) -> Unit = {},
) {

    EchoTextFieldLayout(
        title = title,
        supportingText = supportingText,
        isError = isError,
        enabled = enabled,
        onFocusChanged = onFocusChanged,
        modifier = modifier
    ) { styleModifier, interactionSource ->
        BasicSecureTextField(
            state = state,
            modifier = styleModifier,
            enabled = enabled,
            interactionSource = interactionSource,
            textObfuscationMode = if (isPasswordVisible) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.Hidden
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = if (enabled) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.extended.textPlaceholder
                }
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            decorator = { innerBox ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (state.text.isEmpty() && placeHolder != null) {
                            Text(
                                text = placeHolder,
                                color = MaterialTheme.colorScheme.extended.textPlaceholder,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        innerBox()
                    }
                    Icon(
                        imageVector = if (isPasswordVisible) {
                            vectorResource(
                                Res.drawable.eye_off_icon
                            )
                        } else {
                            vectorResource(
                                Res.drawable.eye_icon
                            )
                        },
                        contentDescription = if (isPasswordVisible) {
                            stringResource(Res.string.hide_password)
                        } else {
                            stringResource(Res.string.show_password)
                        },
                        tint = MaterialTheme.colorScheme.extended.textDisabled,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(
                                    bounded = false,
                                    radius = 24.dp
                                ),
                                onClick = onToggleVisibilityClick
                            )
                    )
                }
            }
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
fun EchoPasswordTextFieldEmptyPreview() {
    EchoTheme {
        EchoPasswordTextField(
            state = rememberTextFieldState(),
            isPasswordVisible = true,
            onToggleVisibilityClick = {},
            modifier = Modifier
                .width(300.dp),
            placeHolder = "Password",
            title = "Password",
            supportingText = "Use 9+ characters, at least one digit and one uppercase letter",
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
fun EchoPasswordTextFieldFilledPreview() {
    EchoTheme {
        EchoPasswordTextField(
            state = rememberTextFieldState("password1234"),
            isPasswordVisible = false,
            onToggleVisibilityClick = {},
            modifier = Modifier
                .width(300.dp),
            placeHolder = "Password",
            title = "Password",
            supportingText = "Use 9+ characters, at least one digit and one uppercase letter",
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
fun EchoPasswordTextFieldErrorPreview() {
    EchoTheme {
        EchoPasswordTextField(
            state = rememberTextFieldState("password1234"),
            isPasswordVisible = true,
            onToggleVisibilityClick = {},
            modifier = Modifier
                .width(300.dp),
            placeHolder = "Password",
            title = "Password",
            supportingText = "Doesn't contain at least one number",
            isError = true
        )
    }
}