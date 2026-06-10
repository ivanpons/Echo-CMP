package com.llimapons.chat.presentation.create_chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.llimapons.chat.domain.models.Chat
import com.llimapons.chat.presentation.components.ChatParticipantSearchTextSection
import com.llimapons.chat.presentation.components.ChatParticipantsSelectionSection
import com.llimapons.chat.presentation.components.ManageChatButtonSection
import com.llimapons.chat.presentation.components.ManageChatHeaderRow
import com.llimapons.core.designsystem.components.brand.EchoHorizontalDivider
import com.llimapons.core.designsystem.components.buttons.EchoButton
import com.llimapons.core.designsystem.components.buttons.EchoButtonStyle
import com.llimapons.core.designsystem.components.dialog.EchoAdaptativeDialogSheetLayout
import com.llimapons.core.designsystem.theme.EchoTheme
import com.llimapons.core.presentation.util.DeviceConfiguration
import com.llimapons.core.presentation.util.ObserveAsEvents
import com.llimapons.core.presentation.util.clearFocusOnTap
import com.llimapons.core.presentation.util.currentDeviceConfiguration
import echo.feature.chat.presentation.generated.resources.Res
import echo.feature.chat.presentation.generated.resources.cancel
import echo.feature.chat.presentation.generated.resources.create_chat
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateChatRoot(
    onDismiss: () -> Unit,
    onChatCreated: (Chat) -> Unit,
    viewModel: CreateChatViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events){ event ->
        when(event){
            is CreateChatEvent.OnChatCreated -> onChatCreated(event.chat)
        }
    }

    EchoAdaptativeDialogSheetLayout(
        onDismiss = onDismiss
    ) {
        CreateChatScreen(
            state = state,
            onAction = { action ->
                when(action){
                    CreateChatAction.OnDismissDialog -> {
                        onDismiss()
                    }
                    else -> Unit
                }
                viewModel.onAction(action)
            }
        )
    }
}

@Composable
fun CreateChatScreen(
    state: CreateChatState,
    onAction: (CreateChatAction) -> Unit,
) {
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val imeHeight = WindowInsets.ime.getBottom(LocalDensity.current)
    val isKeyboardOpen = imeHeight > 0
    val configuration = currentDeviceConfiguration()

    val shouldHideHeader = configuration == DeviceConfiguration.MOBILE_LANDSCAPE
            || (isKeyboardOpen && configuration != DeviceConfiguration.DESKTOP)
            || isTextFieldFocused

    Column(
        modifier = Modifier
            .clearFocusOnTap()
            .fillMaxWidth()
            .wrapContentHeight()
            .imePadding()
            .background(MaterialTheme.colorScheme.surface)
            .navigationBarsPadding()
    ) {
        AnimatedVisibility(
            visible = !shouldHideHeader
        ) {
            Column {
                ManageChatHeaderRow(
                    title = stringResource(Res.string.create_chat),
                    onCloseClick = {
                        onAction(CreateChatAction.OnDismissDialog)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                EchoHorizontalDivider()
            }
        }

        ChatParticipantSearchTextSection(
            queryState = state.queryTextState,
            onAddClick = {
                onAction(CreateChatAction.OnAddClick)
            },
            isSearchEnabled = state.canAddParticipant,
            isLoading = state.isSearching,
            modifier = Modifier.fillMaxWidth(),
            error = state.searchError,
            onFocusChange = {
                isTextFieldFocused = it
            }
        )
        EchoHorizontalDivider()
        ChatParticipantsSelectionSection(
            selectedParticipants = state.selectedChatParticipants,
            searchResult = state.currentSearchResult,
            modifier = Modifier.fillMaxWidth()
        )
        EchoHorizontalDivider()
        ManageChatButtonSection(
            primaryButton = {
                EchoButton(
                    text = stringResource(Res.string.create_chat),
                    onClick = {
                        onAction(CreateChatAction.OnCreateChatClick)
                    },
                    enabled = state.selectedChatParticipants.isNotEmpty(),
                    isLoading = state.isCreatingChat
                )
            },
            secondaryButton = {
                EchoButton(
                    text = stringResource(Res.string.cancel),
                    onClick = {
                        onAction(CreateChatAction.OnDismissDialog)
                    },
                    style = EchoButtonStyle.SECONDARY
                )
            },
            modifier = Modifier.fillMaxWidth(),
            error = state.createChatError?.asString()
        )
    }

}

@Preview
@Composable
private fun Preview() {
    EchoTheme {
        CreateChatScreen(
            state = CreateChatState(),
            onAction = {}
        )
    }
}