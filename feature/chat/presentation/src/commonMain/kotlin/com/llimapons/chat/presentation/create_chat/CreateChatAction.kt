package com.llimapons.chat.presentation.create_chat

import com.llimapons.chat.presentation.chat_list_detail.ChatListDetailAction

sealed interface CreateChatAction {
    data object OnAddClick: CreateChatAction
    data object OnDismissDialog: CreateChatAction
    data object OnCreateChatClick: CreateChatAction
}