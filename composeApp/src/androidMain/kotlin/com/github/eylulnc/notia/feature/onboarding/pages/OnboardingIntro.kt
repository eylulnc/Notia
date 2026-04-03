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
import com.github.eylulnc.notia.ui.common.NotiaPrimaryButton
import com.github.eylulnc.notia.ui.common.NotiaTextButton
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.Spacing
import com.github.eylulnc.notia.ui.theme.ThemeMode

@Composable
fun OnboardingIntro(
    onNext: () -> Unit,
    onSkip: () -> Unit,
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
            horizontalArrangement = Arrangement.End
        ) {
            NotiaTextButton(onClick = onSkip) {
                Text(stringResource(R.string.skip))
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            Text(
                text = stringResource(R.string.onboarding_intro_title),
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(Modifier.height(Spacing.m))

            Text(
                text = stringResource(R.string.onboarding_intro_body),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(Spacing.xxl))

            OnboardingPagerIndicator(
                pagerState = pagerState
            )

        }

        NotiaPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.xl),
            onClick = onNext
        ) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview
@Composable
fun OnboardingIntroPreview() {
    NotiaTheme(ThemeMode.SYSTEM) {
        val pagerState = rememberPagerState(pageCount = { 3 })
        OnboardingIntro(
            onNext = {},
            onSkip = {},
            pagerState = pagerState
        )
    }
}