package com.llimapons.echo

sealed interface MainEvent {
    data object OnSessionExpired: MainEvent
}