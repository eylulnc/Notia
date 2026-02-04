package com.github.eylulnc.notia.data.storage

import com.github.eylulnc.notia.storage.AndroidFocusStorage
import com.github.eylulnc.notia.storage.AndroidReminderStorage
import org.koin.dsl.module

actual val platformStorageModule = module {
    single<FocusStorage> {
        AndroidFocusStorage(get())
    }
    single<ReminderStorage> {
        AndroidReminderStorage(get())
    }
}
