package com.lovelineplanner.core.common.util

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreenViewProvider

object ExtensionFunctions {

    fun SplashScreenViewProvider.animate() {
        val zoomX = ObjectAnimator.ofFloat(
            this.iconView,
            View.SCALE_X,
            0.8f,
            0.0f
        ).apply {
            interpolator = OvershootInterpolator()
            duration = 500L
            doOnEnd { this@animate.remove() }
        }
        val zoomY = ObjectAnimator.ofFloat(
            this.iconView,
            View.SCALE_Y,
            0.8f,
            0.0f
        ).apply {
            interpolator = OvershootInterpolator()
            duration = 500L
            doOnEnd { this@animate.remove() }
        }
        zoomX.start()
        zoomY.start()
    }
}