package com.github.eylulnc.notia.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.Spacing
import com.github.eylulnc.notia.ui.theme.ThemeMode

@Composable
fun OnboardingPagerIndicator(
    pagerState: PagerState
) {
    Row(
        modifier = Modifier
            .padding(vertical = Spacing.l)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { index ->
            val isSelected = pagerState.currentPage == index

            Box(
                modifier = Modifier
                    .padding(horizontal = Spacing.xs)
                    .height(Spacing.xs)
                    .width(if (isSelected) 24.dp else 8.dp)
                    .background(
                        color = if (isSelected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun PagerIndicatorLightPreview() {
    NotiaTheme(ThemeMode.LIGHT) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val pagerState = rememberPagerState(pageCount = { 3 })
            OnboardingPagerIndicator(pagerState = pagerState)
        }
    }
}
