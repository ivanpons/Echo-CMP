package com.llimapons.echo.di

import com.llimapons.auth.presentation.di.authPresentationModule
import com.llimapons.chat.data.di.chatDataModule
import com.llimapons.chat.presentation.di.chatPresentationModule
import com.llimapons.core.data.di.coreDataModule
import com.llimapons.core.presentation.di.corePresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            coreDataModule,
            authPresentationModule,
            corePresentationModule,
            chatPresentationModule,
            chatDataModule
        )
    }
}