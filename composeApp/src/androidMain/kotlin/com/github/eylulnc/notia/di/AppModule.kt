package com.github.eylulnc.notia.di

import com.github.eylulnc.notia.util.DateProvider
import com.github.eylulnc.notia.util.SystemDateProvider
import org.koin.dsl.module

val appModule = module {

    single<DateProvider> {
        SystemDateProvider()
    }
}
