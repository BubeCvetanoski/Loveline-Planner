package com.lovelineplanner

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import com.lovelineplanner.presentation.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                animateSplashScreen(screen)
            }
        }
        enableEdgeToEdge()
        setContent {
            AppTheme {
//                LoginScreen()
            }
        }
    }
}

fun animateSplashScreen(
    screen: SplashScreenViewProvider
) {
    val zoomX = ObjectAnimator.ofFloat(
        screen.iconView,
        View.SCALE_X,
        0.8f,
        0.0f
    ).apply {
        interpolator = OvershootInterpolator()
        duration = 500L
        doOnEnd { screen.remove() }
    }
    val zoomY = ObjectAnimator.ofFloat(
        screen.iconView,
        View.SCALE_Y,
        0.8f,
        0.0f
    ).apply {
        interpolator = OvershootInterpolator()
        duration = 500L
        doOnEnd { screen.remove() }
    }
    zoomX.start()
    zoomY.start()
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
    }
}