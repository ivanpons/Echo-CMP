package com.llimapons.chat.data.di

import com.llimapons.chat.data.chat.KtorChatParticipantService
import com.llimapons.chat.data.chat.KtorChatService
import com.llimapons.chat.domain.chat.ChatParticipantService
import com.llimapons.chat.domain.chat.ChatService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val chatDataModule = module {
    singleOf(::KtorChatParticipantService) bind ChatParticipantService::class
    singleOf(::KtorChatService) bind ChatService::class

}