package com.llimapons.core.data.auth

import com.llimapons.core.data.auth.dto.requests.EmailRequest
import com.llimapons.core.data.auth.dto.requests.RegisterRequest
import com.llimapons.core.data.networking.get
import com.llimapons.core.data.networking.post
import com.llimapons.core.domain.auth.AuthService
import com.llimapons.core.domain.util.DataError
import com.llimapons.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class KtorAuthService(
    private val httpClient: HttpClient
): AuthService {

    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/register",
            body = RegisterRequest(
                email = email,
                username = username,
                password = password
            )
        )
    }

    override suspend fun resendVerificationEmail(email: String): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/resend-verification",
            body = EmailRequest(email)
        )
    }

    override suspend fun verifyEmail(token: String): EmptyResult<DataError.Remote> {
        return httpClient.get(
            route = "/auth/verify",
            queryParams = mapOf(
                "token" to token
            )
        )
    }
}