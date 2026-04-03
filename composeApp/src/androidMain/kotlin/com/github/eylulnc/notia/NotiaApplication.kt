package com.github.eylulnc.notia

import android.app.Application
import com.github.eylulnc.notia.di.KoinStarter
import com.github.eylulnc.notia.di.androidModule
import org.koin.android.ext.koin.androidContext

class NotiaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinStarter.startAndroid {
            androidContext(this@NotiaApplication)
            modules(androidModule)
        }
    }
}
