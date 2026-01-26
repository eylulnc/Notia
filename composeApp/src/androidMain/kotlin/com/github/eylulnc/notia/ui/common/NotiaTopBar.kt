package com.github.eylulnc.notia.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.eylulnc.notia.ui.theme.*

@Composable
fun NotiaTopBar(
    title: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundLight)
            .padding(top = Spacing.l)
            .padding(horizontal = Spacing.l)
            .height(Spacing.topBarHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Leading
        Box(
            modifier = Modifier.size(width = Spacing.iconBoxWidth, height = Spacing.iconBoxHeight),
            contentAlignment = Alignment.CenterStart
        ) {
            if (leadingContent != null) {
                leadingContent()
            } else if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = Primary
                )
            }
        }

        // Title
        Text(
            text = title.uppercase(),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Medium,
                letterSpacing = Spacing.letterWide
            ),
            color = CharcoalSoft,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        // Trailing
        Box(
            modifier = Modifier.size(width = Spacing.iconBoxWidth, height = Spacing.iconBoxHeight),
            contentAlignment = Alignment.CenterEnd
        ) {
            trailingContent?.invoke()
        }
    }
}
