package com.llimapons.chat.presentation.chat_detail

import androidx.compose.foundation.text.input.TextFieldState
import com.llimapons.chat.domain.models.ConnectionState
import com.llimapons.chat.presentation.model.ChatUi
import com.llimapons.chat.presentation.model.MessageUi
import com.llimapons.core.presentation.util.UiText

data class ChatDetailState(
    val chatUi: ChatUi? = null,
    val isLoading: Boolean = false,
    val messages: List<MessageUi> = emptyList(),
    val error: UiText? = null,
    val messageTextFieldState: TextFieldState = TextFieldState(),
    val canSendMessage: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val paginationError: UiText? = null,
    val endReached: Boolean = false,
    val messageWithOpenMenu: MessageUi.LocalUserMessage? = null,
    val bannerState: BannerState = BannerState(),
    val isChatOptionsOpen: Boolean = false,
    val isNearBottom: Boolean = false,
    val connectionState: ConnectionState = ConnectionState.DISCONNECTED
)

data class BannerState(
    val formattedDate: UiText? = null,
    val isVisible: Boolean = false
)