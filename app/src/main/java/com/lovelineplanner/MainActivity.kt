package com.lovelineplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lovelineplanner.core.common.util.ExtensionFunctions.animate
import com.lovelineplanner.core.navigation.BottomNavigationBar
import com.lovelineplanner.core.navigation.guests.guestsGraph
import com.lovelineplanner.core.navigation.home.HomeGraph
import com.lovelineplanner.core.navigation.home.homeGraph
import com.lovelineplanner.core.navigation.planning.planningGraph
import com.lovelineplanner.core.navigation.profile.profileGraph
import com.lovelineplanner.features.login.presentation.LoginScreen
import com.lovelineplanner.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                screen.animate()
            }
        }
        enableEdgeToEdge()
        setContent {
//            val view = LocalView.current
//            val windowController = WindowCompat.getInsetsController(window, view)
//            windowController.hide(WindowInsetsCompat.Type.statusBars())

            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colorScheme.background
                ) {
                    var isUserLoggedIn by remember {
                        mutableStateOf(true)
                    }

                    if (isUserLoggedIn) {
                        val navController = rememberNavController()

                        Scaffold(
                            containerColor = AppTheme.colorScheme.background,
                            bottomBar = {
                                BottomNavigationBar(navController = navController)
                            }
                        ) { paddingValues ->
                            NavHost(
                                navController = navController,
                                startDestination = HomeGraph,
                                modifier = Modifier.padding(paddingValues)
                            ) {
                                homeGraph(navController)
                                planningGraph(navController)
                                guestsGraph(navController)
                                profileGraph(navController)
                            }
                        }
                    } else {
                        LoginScreen(
                            onLogInClicked = { isUserLoggedIn = true }
                        )
                    }
                }
            }
        }
    }
}