package com.llimapons.core.data.mappers

import com.llimapons.core.data.auth.dto.AuthInfoSerializable
import com.llimapons.core.data.auth.dto.UserSerializable
import com.llimapons.core.domain.auth.AuthInfo
import com.llimapons.core.domain.auth.User

fun AuthInfoSerializable.toDomain(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        user = user.toDomain()
    )
}

fun UserSerializable.toDomain(): User {
    return User(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasVerifiedEmail,
        profilePictureUrl = profilePictureUrl
    )
}