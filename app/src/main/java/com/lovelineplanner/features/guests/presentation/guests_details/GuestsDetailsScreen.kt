package com.lovelineplanner.features.guests.presentation.guests_details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lovelineplanner.core.presentation.composables.EditSwitch
import com.lovelineplanner.features.guests.presentation.guests_details.components.EditableScreen
import com.lovelineplanner.features.guests.presentation.guests_details.components.GuestsDetailsScreenHeader
import com.lovelineplanner.features.guests.presentation.guests_details.components.NonEditableScreen
import com.lovelineplanner.core.presentation.theme.AppTheme
import com.lovelineplanner.features.guests.presentation.guests_overview.components.GuestItem
import com.lovelineplanner.features.guests.presentation.guests_overview.components.InitialsCircle

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GuestsDetailsScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    item: GuestItem
) {
    val context = LocalContext.current
    var isAllowedToEdit by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.height(248.dp)
            ) {
                GuestsDetailsScreenHeader(
                    text = "Your presence will make us happier",
                    context = context
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(97.dp)
                        .clip(AppTheme.shape.button)
                        .background(AppTheme.colorScheme.background)
                        .padding(4.dp)
                ) {
                    InitialsCircle(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(96.dp)
                            .clip(AppTheme.shape.button)
                            .background(
                                color = AppTheme.colorScheme.primary,
                                shape = AppTheme.shape.button
                            )
                            .sharedElement(
                                state = rememberSharedContentState(key = "${item.id}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            ),
                        initials = item.initials,
                        textStyle = AppTheme.typography.titleNormal
                    )
                }
            }
            AnimatedContent(
                targetState = isAllowedToEdit
            ) { targetState ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (targetState)
                        EditableScreen()
                    else
                        NonEditableScreen()
                }
            }
        }
        EditSwitch(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(AppTheme.size.normal),
            onSwitched = { getIsAllowedToEdit ->
                isAllowedToEdit = getIsAllowedToEdit
            }
        )
    }
}

//@PreviewLightDark
//@Composable
//private fun GuestsDetailsScreenPreview() {
//    AppTheme {
//        GuestsDetailsScreen(
//            modifier = Modifier.background(AppTheme.colorScheme.background)
//        )
//    }
//}