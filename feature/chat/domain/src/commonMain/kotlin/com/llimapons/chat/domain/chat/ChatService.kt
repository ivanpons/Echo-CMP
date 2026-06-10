package com.llimapons.chat.domain.chat

import com.llimapons.chat.domain.models.Chat
import com.llimapons.core.domain.util.DataError
import com.llimapons.core.domain.util.Result

interface ChatService {

    suspend fun createChat(
        otherUserIds: List<String>
    ): Result<Chat, DataError.Remote>
}