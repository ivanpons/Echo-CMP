package com.llimapons.chat.domain.models

data class ChatParticipant(
    val usrId: String,
    val userName: String,
    val profilePictureUrl: String?
){
    val initials: String
        get() = userName.take(2).uppercase()
}

