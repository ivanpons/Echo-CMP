package com.llimapons.chat.presentation.create_chat

import com.llimapons.chat.domain.models.Chat

sealed interface CreateChatEvent {
    data class OnChatCreated(val chat: Chat): CreateChatEvent
}