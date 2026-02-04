package com.github.eylulnc.notia.di

import com.github.eylulnc.notia.data.FocusRepositoryImpl
import com.github.eylulnc.notia.data.ReminderRepositoryImpl
import com.github.eylulnc.notia.data.storage.platformStorageModule
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.domain.repository.ReminderRepository
import com.github.eylulnc.notia.util.DateProvider
import com.github.eylulnc.notia.util.HistoryDateFormatter
import com.github.eylulnc.notia.util.SystemDateProvider
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {
    single<DateProvider> { SystemDateProvider() }
    single { HistoryDateFormatter() }
    
    single<FocusRepository> {
        FocusRepositoryImpl(get(), get())
    }
    
    single<ReminderRepository> {
        ReminderRepositoryImpl(get())
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        platformStorageModule,
        sharedModule
    )
}
