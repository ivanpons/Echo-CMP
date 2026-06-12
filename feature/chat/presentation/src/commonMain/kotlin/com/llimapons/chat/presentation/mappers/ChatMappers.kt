package com.llimapons.chat.presentation.mappers

import com.llimapons.chat.domain.models.Chat
import com.llimapons.chat.presentation.model.ChatUi

fun Chat.toUi(localParticipantId: String): ChatUi {
    val (local, other) = participants.partition { it.usrId == localParticipantId }
    return ChatUi(
        id = id,
        localParticipant = local.first().toUi(),
        otherParticipants = other.map { it.toUi() },
        lastMessage = lastMessage,
        lastMessageSenderUsername = lastMessageSenderUsername
    )
}