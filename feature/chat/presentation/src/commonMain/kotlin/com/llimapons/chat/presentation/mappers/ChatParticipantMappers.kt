package com.llimapons.chat.presentation.mappers

import com.llimapons.chat.domain.models.ChatParticipant
import com.llimapons.core.designsystem.components.avatar.ChatParticipantUi

fun ChatParticipant.toUi(): ChatParticipantUi =
    ChatParticipantUi(
        id = usrId,
        username = userName,
        imageUrl = profilePictureUrl,
        initials = this.initials,
    )