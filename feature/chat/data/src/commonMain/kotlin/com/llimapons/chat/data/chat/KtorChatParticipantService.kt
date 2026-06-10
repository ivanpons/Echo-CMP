package com.llimapons.chat.data.chat

import com.llimapons.chat.data.dto.ChatParticipantDto
import com.llimapons.chat.data.mappers.toDomain
import com.llimapons.chat.domain.chat.ChatParticipantService
import com.llimapons.chat.domain.models.ChatParticipant
import com.llimapons.core.data.networking.get
import com.llimapons.core.domain.util.DataError
import com.llimapons.core.domain.util.Result
import com.llimapons.core.domain.util.map
import io.ktor.client.HttpClient

class KtorChatParticipantService(
    private val httpClient: HttpClient
): ChatParticipantService {

    override suspend fun searchParticipant(query: String): Result<ChatParticipant, DataError.Remote> {
       return httpClient.get<ChatParticipantDto>(
           route = "/participants",
           queryParams = mapOf(
               "query" to query
           )
       ).map { it.toDomain() }
    }

}