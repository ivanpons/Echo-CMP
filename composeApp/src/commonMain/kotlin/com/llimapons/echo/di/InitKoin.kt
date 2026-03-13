package com.llimapons.echo.di

import com.llimapons.auth.presentation.di.authPresentationModule
import com.llimapons.core.data.di.coreDataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(
            coreDataModule,
            authPresentationModule
        )
    }
}