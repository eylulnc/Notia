package com.github.eylulnc.notia.feature.onboarding.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.feature.onboarding.OnboardingPagerIndicator
import com.github.eylulnc.notia.ui.common.NotiaSecondaryButton
import com.github.eylulnc.notia.ui.common.NotiaTextButton
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.Spacing
import com.github.eylulnc.notia.ui.theme.ThemeMode

@Composable
fun OnboardingGetStarted(
    onFinish: () -> Unit,
    onBack: () -> Unit,
    pagerState: PagerState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Spacing.xl),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NotiaTextButton(onClick = onBack) {
                Text(stringResource(R.string.back))
            }

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(R.string.onboarding_start_title),
                textAlign = TextAlign.Center,
                fontSize = FontSizes.midTitle
            )

            Spacer(Modifier.height(Spacing.xxl))
            Text(
                text = stringResource(R.string.onboarding_start_body),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(Spacing.l))

            OnboardingPagerIndicator(
                pagerState = pagerState
            )

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = Spacing.xl)
        ) {
            NotiaSecondaryButton(
                onClick = onFinish
            ) {
                Text(stringResource(R.string.get_started))
            }

            NotiaTextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onFinish
            ) {
                Text(stringResource(R.string.skip_to_app))
            }
        }

    }
}


@Preview
@Composable
fun OnboardingGetStartedPreview() {
    NotiaTheme(ThemeMode.LIGHT) {
        OnboardingGetStarted(
            onFinish = {},
            onBack = {},
            pagerState = rememberPagerState(pageCount = { 3 })
        )
    }
}