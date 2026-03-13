package com.llimapons.echo

import android.app.Application
import com.llimapons.echo.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class EchoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@EchoApplication)
            androidLogger()
        }
    }
}