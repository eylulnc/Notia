package com.github.eylulnc.notia.di

import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.domain.repository.ReminderRepository
import com.github.eylulnc.notia.util.DateProvider
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    
    val focusRepository: FocusRepository by inject()
    val reminderRepository: ReminderRepository by inject()
    val dateProvider: DateProvider by inject()

    fun iOSInit() {
        KoinStarter.startIOS()
    }
}
