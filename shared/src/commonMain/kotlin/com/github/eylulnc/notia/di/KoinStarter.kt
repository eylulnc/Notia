package com.github.eylulnc.notia.di

import com.github.eylulnc.notia.data.storage.platformStorageModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

object KoinStarter {

    fun startIOS() {
        startKoin {
            modules(
                platformStorageModule,
                sharedModule
            )
        }
    }

    fun startAndroid(appDeclaration: KoinAppDeclaration) {
        startKoin {
            appDeclaration()
            modules(
                platformStorageModule,
                sharedModule
            )
        }
    }
}

