package com.github.eylulnc.notia.ui.today

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.common.NotiaTopBar
import com.github.eylulnc.notia.ui.theme.CharcoalSoft

@Composable
fun TodayEditTopBar(onCancel: () -> Unit) {
    NotiaTopBar(
        title = stringResource(R.string.focus_title),
        leadingContent = {
            Text(
                text = stringResource(R.string.cancel),
                color = CharcoalSoft,
                modifier = Modifier.clickable { onCancel() }
            )
        }
    )
}
