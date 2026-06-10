package com.llimapons.core.presentation.di

import com.llimapons.core.presentation.util.ScopeStoreRegistryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val corePresentationModule = module {
    viewModelOf(::ScopeStoreRegistryViewModel)
}