package com.github.eylulnc.notia.feature.onboarding

import com.github.eylulnc.notia.feature.history.ui.HistoryItem
import kotlinx.datetime.LocalDate

object OnboardingPreviewData {

    fun historyItems(): List<HistoryItem> =
        listOf(
            HistoryItem(
                date = LocalDate(2024, 1, 1),
                dateLabel = "Mon, Jan 1",
                focusText = "Finish onboarding polish"
            )
        )
}
