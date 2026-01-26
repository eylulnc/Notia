package com.github.eylulnc.notia.ui.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.eylulnc.notia.ui.theme.BackgroundLight

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { HistoryTopBar() },
        containerColor = BackgroundLight
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text("History Screen")
        }
    }
}
