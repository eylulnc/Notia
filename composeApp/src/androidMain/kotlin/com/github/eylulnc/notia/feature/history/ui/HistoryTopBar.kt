package com.github.eylulnc.notia.feature.history.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.common.NotiaTopBar

@Composable
fun HistoryTopBar() {
    NotiaTopBar(
        title = stringResource(R.string.history_title)
    )
}
