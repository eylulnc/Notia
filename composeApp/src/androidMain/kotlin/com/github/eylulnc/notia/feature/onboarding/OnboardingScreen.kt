package com.github.eylulnc.notia.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.github.eylulnc.notia.feature.onboarding.pages.OnboardingFreshStart
import com.github.eylulnc.notia.feature.onboarding.pages.OnboardingGetStarted
import com.github.eylulnc.notia.feature.onboarding.pages.OnboardingIntro
import com.github.eylulnc.notia.feature.onboarding.pages.OnboardingPage
import com.github.eylulnc.notia.feature.onboarding.pages.onboardingPages
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { onboardingPages.size }
    )

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            userScrollEnabled = true
        ) { pageIndex ->
            when (onboardingPages[pageIndex]) {

                OnboardingPage.INTRO ->
                    OnboardingIntro(
                        onNext = {
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        },
                        onSkip = onFinish,
                        pagerState = pagerState
                    )

                OnboardingPage.FRESH_START ->
                    OnboardingFreshStart(
                        onNext = {
                            scope.launch {
                                pagerState.animateScrollToPage(2)
                            }
                        },
                        onBack = {
                            scope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        },
                        onSkip = onFinish,
                        pagerState = pagerState
                    )

                OnboardingPage.GET_STARTED ->
                    OnboardingGetStarted(
                        onFinish = onFinish,
                        onBack = {
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        },
                        pagerState = pagerState
                    )
            }
        }


    }
}
