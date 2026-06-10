package com.llimapons.chat.domain.models

data class ChatInfo(
    val chat: Chat,
    val messages: List<MessageWithSender>,
)
