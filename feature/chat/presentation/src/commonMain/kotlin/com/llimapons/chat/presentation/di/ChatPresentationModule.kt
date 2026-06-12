package com.llimapons.chat.presentation.di

import com.llimapons.chat.presentation.chat_detail.ChatDetailViewModel
import com.llimapons.chat.presentation.chat_list.ChatListViewModel
import com.llimapons.chat.presentation.chat_list_detail.ChatListDetailViewModel
import com.llimapons.chat.presentation.create_chat.CreateChatViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chatPresentationModule = module {
    viewModelOf(::ChatListViewModel)
    viewModelOf(::ChatDetailViewModel)
    viewModelOf(::ChatListDetailViewModel)
    viewModelOf(::CreateChatViewModel)
}