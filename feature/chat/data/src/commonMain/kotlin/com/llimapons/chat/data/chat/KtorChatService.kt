package com.llimapons.chat.data.chat

import com.llimapons.chat.data.dto.ChatDto
import com.llimapons.chat.data.dto.request.CreateChatRequest
import com.llimapons.chat.data.mappers.toDomain
import com.llimapons.chat.domain.chat.ChatService
import com.llimapons.chat.domain.models.Chat
import com.llimapons.core.data.networking.post
import com.llimapons.core.domain.util.DataError
import com.llimapons.core.domain.util.Result
import com.llimapons.core.domain.util.map
import io.ktor.client.HttpClient

class KtorChatService(
    private val httpClient: HttpClient
) : ChatService {

    override suspend fun createChat(otherUserIds: List<String>): Result<Chat, DataError.Remote> {
        return httpClient.post<CreateChatRequest, ChatDto>(
            route = "/chat",
            body = CreateChatRequest(
                otherUserIds = otherUserIds
            )
        ).map { it.toDomain() }
    }
}

