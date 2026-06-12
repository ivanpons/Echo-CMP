package com.llimapons.chat.presentation.util

import echo.feature.chat.presentation.generated.resources.Res
import echo.feature.chat.presentation.generated.resources.network_error
import echo.feature.chat.presentation.generated.resources.offline
import echo.feature.chat.presentation.generated.resources.online
import echo.feature.chat.presentation.generated.resources.reconnecting
import echo.feature.chat.presentation.generated.resources.unknown_error
import com.llimapons.chat.domain.models.ConnectionState
import com.llimapons.core.presentation.util.UiText

fun ConnectionState.toUiText(): UiText {
    val resource = when(this) {
        ConnectionState.DISCONNECTED -> Res.string.offline
        ConnectionState.CONNECTING -> Res.string.reconnecting
        ConnectionState.CONNECTED -> Res.string.online
        ConnectionState.ERROR_NETWORK -> Res.string.network_error
        ConnectionState.ERROR_UNKNOWN -> Res.string.unknown_error
    }
    return UiText.Resource(resource)
}