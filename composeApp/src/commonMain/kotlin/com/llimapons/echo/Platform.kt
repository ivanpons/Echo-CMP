package com.llimapons.echo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform