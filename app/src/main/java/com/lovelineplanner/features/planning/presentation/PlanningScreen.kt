package com.lovelineplanner.features.planning.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.lovelineplanner.core.presentation.theme.AppTheme

@Composable
fun PlanningScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Hello from PlanningScreen")
    }
}


@PreviewLightDark
@Composable
private fun PlanningScreenPreview() {
    AppTheme {
        PlanningScreen(
            modifier = Modifier.background(AppTheme.colorScheme.background)
        )
    }
}