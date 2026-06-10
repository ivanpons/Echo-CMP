@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.llimapons.chat.presentation.chat_list_detail

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.llimapons.chat.presentation.create_chat.CreateChatRoot
import com.llimapons.core.designsystem.theme.extended
import com.llimapons.core.presentation.util.DialogSheetScopedViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatListDetailAdaptiveLayout(
    initialChatId: String?,
    onLogout: () -> Unit,
    chatListDetailViewModel: ChatListDetailViewModel = koinViewModel()
) {
    val sharedState by chatListDetailViewModel.state.collectAsStateWithLifecycle()
    val scaffoldDirective = createNoSpacingPaneScaffoldDirective()
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator(
        scaffoldDirective = scaffoldDirective
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(initialChatId) {
        if(initialChatId != null) {
            chatListDetailViewModel.onAction(ChatListDetailAction.OnSelectChat(initialChatId))
            scaffoldNavigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
        }
    }

    BackHandler(enabled = scaffoldNavigator.canNavigateBack()) {
        scope.launch {
            scaffoldNavigator.navigateBack()
            chatListDetailViewModel.onAction(ChatListDetailAction.OnSelectChat(null))
        }
    }

    val detailPane = scaffoldNavigator.scaffoldValue[ListDetailPaneScaffoldRole.Detail]
    LaunchedEffect(detailPane, sharedState.selectedChatId) {
        if(detailPane == PaneAdaptedValue.Hidden && sharedState.selectedChatId != null) {
            chatListDetailViewModel.onAction(ChatListDetailAction.OnSelectChat(null))
        }
    }

    ListDetailPaneScaffold(
        directive = scaffoldDirective,
        value = scaffoldNavigator.scaffoldValue,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.extended.surfaceLower),
        listPane = {
            AnimatedPane {

            }
        },
        detailPane = {
            AnimatedPane {

            }
        }
    )

    DialogSheetScopedViewModel(
        visible = sharedState.dialogState is DialogState.CreateChat
    ) {
        CreateChatRoot(
            onDismiss = {
                chatListDetailViewModel.onAction(ChatListDetailAction.OnDismissCurrentDialog)
            },
            onChatCreated = { chat ->
                chatListDetailViewModel.onAction(ChatListDetailAction.OnDismissCurrentDialog)
                chatListDetailViewModel.onAction(ChatListDetailAction.OnSelectChat(chat.id))
                scope.launch {
                    scaffoldNavigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                }
            }
        )
    }

}