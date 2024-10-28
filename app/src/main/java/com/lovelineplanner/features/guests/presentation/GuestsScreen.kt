package com.lovelineplanner.features.guests.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lovelineplanner.features.guests.presentation.components.GuestsList
import com.lovelineplanner.features.guests.presentation.components.GuestsScreenHeader
import com.lovelineplanner.ui.theme.AppTheme
import com.lovelineplanner.ui.theme.White

@Composable
fun GuestsScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.height(224.dp)
        ) {
            GuestsScreenHeader(context = context)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(50.dp)
                    .clip(AppTheme.shape.button)
                    .background(AppTheme.colorScheme.background)
            ) {
                FloatingActionButton(
                    onClick = { },
                    shape = AppTheme.shape.button,
                    containerColor = AppTheme.colorScheme.primary,
                    contentColor = AppTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(48.dp)
                        .clip(CircleShape)
                        .padding(2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "guests_add_icon",
                        tint = White
                    )
                }
            }
        }
        GuestsList()
    }
}


@PreviewLightDark
@Composable
private fun GuestsScreenPreview() {
    AppTheme {
        GuestsScreen(
            modifier = Modifier.background(AppTheme.colorScheme.background)
        )
    }
}