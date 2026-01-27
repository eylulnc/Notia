package com.github.eylulnc.notia.feature.settings.darkmode

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.github.eylulnc.notia.ui.theme.Charcoal
import com.github.eylulnc.notia.ui.theme.CharcoalSoft
import com.github.eylulnc.notia.ui.theme.Cream
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.Spacing

@Composable
fun <T> SegmentedControl(
    options: List<T>,
    selected: T,
    onSelect: (T) -> Unit,
    label: @Composable (T) -> String = { it.toString() }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = CharcoalSoft.copy(alpha = 0.15f),
                shape = RoundedCornerShape(Spacing.m)
            )
            .padding(Spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        options.forEach { option ->
            val isSelected = option == selected

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(Spacing.s))
                    .background(
                        if (isSelected) Cream else Color.Transparent
                    )
                    .clickable { onSelect(option) }
                    .padding(vertical = Spacing.s),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label(option),
                    fontSize = FontSizes.label,
                    fontWeight = FontWeight.Medium,
                    color = if (isSelected) Charcoal else CharcoalSoft
                )
            }
        }
    }
}