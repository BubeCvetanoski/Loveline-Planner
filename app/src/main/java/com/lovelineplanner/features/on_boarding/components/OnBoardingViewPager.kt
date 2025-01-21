package com.lovelineplanner.features.on_boarding.components

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.lovelineplanner.features.on_boarding.components.model.OnBoardingPageItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnBoardingViewPager(
    modifier: Modifier = Modifier,
    totalPages: Int = 3,
    slideInterval: Long = 3200L,
    pages: List<OnBoardingPageItem>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    onButtonClick: (currentPageIndex: Int) -> Unit,
) {
    LaunchedEffect(pagerState.currentPage) {
        while (true) {
            if (pagerState.currentPage == pages.lastIndex) {
                break
            }

            delay(slideInterval)
            coroutineScope.launch {
                pagerState.animateScrollToPage(
                    (pagerState.currentPage + 1) % totalPages
                )
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { currentPageIndex ->
        OnBoardingPage(
            totalPages = totalPages,
            currentPageIndex = currentPageIndex,
            page = pages[currentPageIndex],
            onButtonClick = {
                onButtonClick(currentPageIndex)
            }
        )
    }
}