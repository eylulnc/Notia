package com.github.eylulnc.notia.di

import android.content.Context
import com.github.eylulnc.notia.data.FocusRepositoryImpl
import com.github.eylulnc.notia.data.GoalRepositoryImpl
import com.github.eylulnc.notia.data.ReminderRepositoryImpl
import com.github.eylulnc.notia.storage.AndroidFocusStorage
import com.github.eylulnc.notia.storage.AndroidGoalStorage
import com.github.eylulnc.notia.storage.AndroidReminderStorage
import com.github.eylulnc.notia.util.SystemDateProvider

class AppContainer(context: Context) {

    private val dateProvider = SystemDateProvider()

    private val focusStorage = AndroidFocusStorage(context)
    private val goalStorage = AndroidGoalStorage(context)
    private val reminderStorage = AndroidReminderStorage(context)

    val focusRepository =
        FocusRepositoryImpl(focusStorage, dateProvider)

    val goalRepository =
        GoalRepositoryImpl(goalStorage)

    val reminderRepository =
        ReminderRepositoryImpl(reminderStorage)
}
