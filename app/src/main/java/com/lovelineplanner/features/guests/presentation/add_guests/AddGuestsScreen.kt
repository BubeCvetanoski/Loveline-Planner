package com.lovelineplanner.features.guests.presentation.add_guests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.lovelineplanner.ui.theme.AppTheme

@Composable
fun AddGuestsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
    ) {

    }
}

@PreviewLightDark
@Composable
private fun AddGuestsScreenPreview() {
    AppTheme {
        AddGuestsScreen(
            modifier = Modifier.background(AppTheme.colorScheme.background)
        )
    }
}