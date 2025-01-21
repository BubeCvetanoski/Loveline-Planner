package com.lovelineplanner.features.guests.presentation.guests_overview

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.lovelineplanner.core.presentation.theme.AppTheme
import com.lovelineplanner.features.guests.presentation.guests_overview.components.AddGuestsDialog
import com.lovelineplanner.features.guests.presentation.guests_overview.components.GuestItem
import com.lovelineplanner.features.guests.presentation.guests_overview.components.GuestsList
import com.lovelineplanner.features.guests.presentation.guests_overview.components.GuestsScreenHeader
import com.lovelineplanner.features.guests.presentation.guests_overview.components.GuestsSearchTextField

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GuestsScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemCardClick: (GuestItem) -> Unit
) {
    val context = LocalContext.current
    var showAddGuestsDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
    ) {
        Box(
            modifier = Modifier.height(224.dp)
        ) {
            GuestsScreenHeader(context = context)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(49.dp)
                    .clip(AppTheme.shape.button)
                    .background(AppTheme.colorScheme.background)
                    .padding(3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clickable { showAddGuestsDialog = true }
                        .align(Alignment.Center)
                        .size(48.dp)
                        .clip(AppTheme.shape.button)
                        .background(
                            color = AppTheme.colorScheme.primary,
                            shape = AppTheme.shape.button
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "guests_add_icon",
                        tint = AppTheme.colorScheme.onPrimary
                    )
                }
            }
        }
        GuestsSearchTextField(
            text = "",
            onValueChange = {},
            onSearch = {}
        )
        HorizontalDivider(
            color = AppTheme.colorScheme.onBackground.copy(alpha = 0.1f)
        )
        GuestsList(
            onItemCardClick = onItemCardClick,
            animatedVisibilityScope = animatedVisibilityScope
        )

        if (showAddGuestsDialog) {
            AddGuestsDialog(
                onDismissRequest = { showAddGuestsDialog = false },
                onConfirm = { nameSurname, guests, invitation, status, note ->
                    showAddGuestsDialog = false
                }
            )
        }
    }
}


//@PreviewLightDark
//@Composable
//private fun GuestsScreenPreview() {
//    AppTheme {
//        GuestsScreen(
//            modifier = Modifier.background(AppTheme.colorScheme.background),
//            onItemCardClick = {}
//        )
//    }
//}