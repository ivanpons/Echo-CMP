package com.llimapons.chat.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.llimapons.core.designsystem.components.buttons.EchoButton
import com.llimapons.core.designsystem.components.buttons.EchoButtonStyle
import com.llimapons.core.designsystem.components.textfields.EchoTextField
import com.llimapons.core.presentation.util.UiText
import echo.feature.chat.presentation.generated.resources.Res
import echo.feature.chat.presentation.generated.resources.add
import echo.feature.chat.presentation.generated.resources.email_or_username
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChatParticipantSearchTextSection(
    queryState: TextFieldState,
    onAddClick: () -> Unit,
    isSearchEnabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    error: UiText? = null,
    onFocusChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        EchoTextField(
            state = queryState,
            modifier = Modifier
                .weight(1f),
            placeHolder = stringResource(Res.string.email_or_username),
            isError = error != null,
            supportingText = error?.asString(),
            singleLine = true,
            keyboardType = KeyboardType.Email,
            onFocusChanged = onFocusChange
        )
        EchoButton(
            text = stringResource(Res.string.add),
            onClick = onAddClick,
            isLoading = isLoading,
            enabled = isSearchEnabled,
            style = EchoButtonStyle.SECONDARY
        )
    }

}