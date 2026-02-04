package com.github.eylulnc.notia

import android.app.Application
import com.github.eylulnc.notia.data.storage.platformStorageModule
import com.github.eylulnc.notia.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotiaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NotiaApplication)
            modules(platformStorageModule, appModule)
        }
    }
}
