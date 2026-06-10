package com.llimapons.chat.domain.chat

import com.llimapons.chat.domain.models.ChatParticipant
import com.llimapons.core.domain.util.DataError
import com.llimapons.core.domain.util.Result

interface ChatParticipantService {

    suspend fun searchParticipant(
        query: String
    ): Result<ChatParticipant, DataError.Remote>


}