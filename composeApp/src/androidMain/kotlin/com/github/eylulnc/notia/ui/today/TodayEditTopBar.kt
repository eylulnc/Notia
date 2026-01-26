package com.github.eylulnc.notia.ui.today

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.eylulnc.notia.ui.common.NotiaTopBar
import com.github.eylulnc.notia.ui.theme.CharcoalSoft

@Composable
fun TodayEditTopBar(onCancel: () -> Unit) {
    NotiaTopBar(
        title = "Focus",
        leadingContent = {
            Text(
                text = "Cancel",
                color = CharcoalSoft,
                modifier = Modifier.clickable { onCancel() }
            )
        }
    )
}
