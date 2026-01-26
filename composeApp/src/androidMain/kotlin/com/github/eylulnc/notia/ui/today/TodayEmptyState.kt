package com.github.eylulnc.notia.ui.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.BackgroundLight
import com.github.eylulnc.notia.ui.theme.Charcoal
import com.github.eylulnc.notia.ui.theme.Cream
import com.github.eylulnc.notia.ui.theme.Primary

@Composable
fun TodayEmptyState(
    onSetFocus: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.today_question),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Charcoal,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = onSetFocus,
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = stringResource(R.string.set_focus),
                color = Cream,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun TodayEmptyStatePreview() {
    TodayEmptyState(onSetFocus = {})
}
