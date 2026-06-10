package com.llimapons.chat.data.mappers

import com.llimapons.chat.data.dto.ChatDto
import com.llimapons.chat.domain.models.Chat
import kotlin.time.Instant

fun ChatDto.toDomain(): Chat =
    Chat(
        id = id,
        participants = participants.map { it.toDomain() },
        lastActivityAt = Instant.parse(lastActivityAt),
        lastMessage = lastMessage?.toDomain(),

    )