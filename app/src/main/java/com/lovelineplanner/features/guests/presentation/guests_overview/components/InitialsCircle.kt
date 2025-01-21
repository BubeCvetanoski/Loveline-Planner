package com.lovelineplanner.features.guests.presentation.guests_overview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.lovelineplanner.core.presentation.theme.AppTheme


@Composable
fun InitialsCircle(
    modifier: Modifier = Modifier,
    initials: String,
    textStyle: TextStyle
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            style = textStyle,
            color = AppTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
    }
}