package com.llimapons.chat.data.mappers

import com.llimapons.chat.data.dto.ChatParticipantDto
import com.llimapons.chat.domain.models.ChatParticipant

fun ChatParticipantDto.toDomain(): ChatParticipant =
    ChatParticipant(
        usrId = userId,
        userName = username,
        profilePictureUrl = profilePictureUrl
    )