package com.github.eylulnc.notia.di

import com.github.eylulnc.notia.data.FocusRepositoryImpl
import com.github.eylulnc.notia.data.storage.FocusStorage
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.feature.history.viewmodel.HistoryViewModel
import com.github.eylulnc.notia.storage.AndroidFocusStorage
import com.github.eylulnc.notia.feature.today.TodayViewModel
import com.github.eylulnc.notia.util.DateProvider
import com.github.eylulnc.notia.util.SystemDateProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<DateProvider> {
        SystemDateProvider()
    }

    single<FocusStorage> {
        AndroidFocusStorage(androidContext())
    }

    single<FocusRepository> {
        FocusRepositoryImpl(
            storage = get(),
            dateProvider = get()
        )
    }

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

}

