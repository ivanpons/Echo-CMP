package com.llimapons.core.domain.auth

import com.llimapons.core.domain.util.DataError
import com.llimapons.core.domain.util.EmptyResult

interface AuthService {
    suspend fun register(
        email: String,
        username: String,
        password: String
    ): EmptyResult<DataError.Remote>
}