package com.llimapons.core.data.di

import com.llimapons.core.data.auth.DataStoreSessionStorage
import com.llimapons.core.data.auth.KtorAuthService
import com.llimapons.core.data.logging.KermitLogger
import com.llimapons.core.data.networking.HttpClientFactory
import com.llimapons.core.domain.auth.AuthService
import com.llimapons.core.domain.auth.SessionStorage
import com.llimapons.core.domain.logging.EchoLogger
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformCoreDataModule: Module

val coreDataModule = module {
    includes(platformCoreDataModule)
    single<EchoLogger> { KermitLogger }
    single {
        HttpClientFactory(get(), get()).create(get())
    }
    singleOf(::KtorAuthService) bind AuthService::class
    singleOf(::DataStoreSessionStorage) bind SessionStorage::class
}