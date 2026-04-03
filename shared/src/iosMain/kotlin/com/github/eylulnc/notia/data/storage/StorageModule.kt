package com.github.eylulnc.notia.data.storage

import com.github.eylulnc.notia.storage.IOSFocusStorage
import com.github.eylulnc.notia.storage.IOSReminderStorage
import org.koin.dsl.module

actual val platformStorageModule = module {
    single<FocusStorage> {
        IOSFocusStorage()
    }
    single<ReminderStorage> {
        IOSReminderStorage()
    }
}
