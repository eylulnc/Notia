package com.github.eylulnc.notia

import android.app.Application
import com.github.eylulnc.notia.di.androidModule
import com.github.eylulnc.notia.di.initKoin
import org.koin.android.ext.koin.androidContext

class NotiaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@NotiaApplication)
            modules(androidModule)
        }
    }
}
