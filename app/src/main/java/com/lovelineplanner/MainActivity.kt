package com.lovelineplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lovelineplanner.core.presentation.util.ExtensionFunctions.animate
import com.lovelineplanner.core.presentation.navigation.BottomNavigationBar
import com.lovelineplanner.core.presentation.navigation.guests.guestsGraph
import com.lovelineplanner.core.presentation.navigation.home.HomeGraph
import com.lovelineplanner.core.presentation.navigation.home.homeGraph
import com.lovelineplanner.core.presentation.navigation.planning.planningGraph
import com.lovelineplanner.core.presentation.navigation.profile.profileGraph
import com.lovelineplanner.features.on_boarding.components.OnBoardingViewPager
import com.lovelineplanner.features.on_boarding.components.model.OnBoardingPageItem
import com.lovelineplanner.core.presentation.theme.AppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                screen.animate()
            }
        }
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colorScheme.background
                ) {
                    var isOnBoardingDone by remember {
                        mutableStateOf(false)
                    }

                    if (isOnBoardingDone) {
                        val navController = rememberNavController()

                        Scaffold(
                            containerColor = AppTheme.colorScheme.background,
                            bottomBar = {
                                BottomNavigationBar(navController = navController)
                            }
                        ) { paddingValues ->
                            SharedTransitionLayout {
                                NavHost(
                                    navController = navController,
                                    startDestination = HomeGraph,
                                    modifier = Modifier.padding(paddingValues)
                                ) {
                                    homeGraph(navController)
                                    planningGraph(navController)
                                    guestsGraph(navController, this@SharedTransitionLayout)
                                    profileGraph(navController)
                                }
                            }
                        }
                    } else {
                        val onBoardingPages = OnBoardingPageItem.pages
                        val totalPages = onBoardingPages.count()
                        val pagerState = rememberPagerState { totalPages }
                        val coroutineScope = rememberCoroutineScope()

                        OnBoardingViewPager(
                            totalPages = totalPages,
                            pages = onBoardingPages,
                            pagerState = pagerState,
                            coroutineScope = coroutineScope,
                            onButtonClick = { currentPageIndex ->
                                if (currentPageIndex == onBoardingPages.lastIndex) {
                                    isOnBoardingDone = true
                                } else {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(currentPageIndex + 1)
                                    }
                                }
                            }
                        )
//                        LoginScreen(
//                            onLogInClicked = { isUserLoggedIn = true }
//                        )
                    }
                }
            }
        }
    }
}