package com.github.eylulnc.notia.di

import com.github.eylulnc.notia.BuildConfig
import com.github.eylulnc.notia.feature.history.viewmodel.HistoryViewModel
import com.github.eylulnc.notia.feature.settings.repository.SettingsRepository
import com.github.eylulnc.notia.feature.settings.repository.SettingsRepositoryImpl
import com.github.eylulnc.notia.feature.settings.viewmodel.SettingsViewModel
import com.github.eylulnc.notia.feature.today.TodayViewModel
import com.github.eylulnc.notia.notifications.ReminderManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single<SettingsRepository> { SettingsRepositoryImpl(androidContext()) }
    single { ReminderManager(androidContext()) }

    viewModel {
        TodayViewModel(
            focusRepository = get(),
            dateProvider = get()
        )
    }

    viewModel {
        HistoryViewModel(
            focusRepository = get(),
            dateFormatter = get()
        )
    }

    viewModel {
        SettingsViewModel(
            settingsRepository = get(),
            focusRepository = get(),
            reminderManager = get(),
            appVersion = BuildConfig.VERSION_NAME
        )
    }
}
