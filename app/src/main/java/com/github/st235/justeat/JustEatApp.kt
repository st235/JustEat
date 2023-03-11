package com.github.st235.justeat

import android.app.Application
import com.github.st235.justeat.di.appModules
import org.koin.core.context.startKoin

class JustEatApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModules)
        }
    }
}
