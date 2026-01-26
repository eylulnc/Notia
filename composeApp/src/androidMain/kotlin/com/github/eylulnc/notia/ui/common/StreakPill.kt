package com.github.eylulnc.notia.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.github.eylulnc.notia.ui.theme.*

@Composable
fun StreakPill(
    count: Int,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .background(
                color = Charcoal.copy(alpha = 0.06f),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = Spacing.s, vertical = Spacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = count.toString(),
            color = Charcoal,
            fontSize = FontSizes.caption
        )
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Charcoal,
            modifier = Modifier
                .padding(start = 4.dp)
                .size(14.dp)
        )
    }
}
