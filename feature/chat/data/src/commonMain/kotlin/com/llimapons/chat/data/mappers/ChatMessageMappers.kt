package com.llimapons.chat.data.mappers

import com.llimapons.chat.data.dto.ChatMessageDto
import com.llimapons.chat.domain.models.ChatMessage
import kotlin.time.Instant

fun ChatMessageDto.toDomain(): ChatMessage =
    ChatMessage(
        id = id,
        chatId = chatId,
        content = content,
        createdAt = Instant.parse(createdAt),
        senderId = senderId
    )