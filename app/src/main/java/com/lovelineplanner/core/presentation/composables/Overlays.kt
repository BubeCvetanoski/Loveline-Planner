package com.lovelineplanner.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.lovelineplanner.ui.theme.Black
import com.lovelineplanner.ui.theme.OverlayColor
import com.lovelineplanner.ui.theme.Transparent

@Composable
fun InvertedVerticalGradientOverlay(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Transparent,
                        OverlayColor
                    ),
                    startY = 350f,
                    endY = 0f
                )
            )
    )
}

@Composable
fun EquallyDistributedOverlay(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Black.copy(alpha = 0.5f))
    )
}
