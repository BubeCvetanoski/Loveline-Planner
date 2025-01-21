package com.lovelineplanner.features.guests.presentation.guests_overview.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.lovelineplanner.core.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GuestsList(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemCardClick: (GuestItem) -> Unit
) {
    val listOfItems = remember {
        mutableStateListOf(
            GuestItem(
                id = 0,
                name = "Bube Cvetanoski",
                initials = "BC",
                invitation = "Sent",
                status = "Confirmed",
                guests = 2,
                isFavorite = true
            ),
            GuestItem(
                id = 1,
                name = "Valeria Ilievska",
                initials = "VI",
                invitation = "Sent",
                status = "Confirmed",
                guests = 2,
                isFavorite = true
            ),
            GuestItem(
                id = 2,
                name = "Darko Cvetanoski",
                initials = "DC",
                invitation = "Sent",
                status = "Confirmed",
                guests = 4
            ), GuestItem(
                id = 3,
                name = "Katarina Cvetanoska",
                initials = "KC",
                invitation = "Sent",
                status = "Confirmed",
                guests = 2
            ),
            GuestItem(
                id = 4,
                name = "Ace Cvetanoski",
                initials = "AC",
                invitation = "Sent",
                status = "Confirmed",
                guests = 2
            )
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            start = AppTheme.size.normal,
            end = AppTheme.size.normal,
            bottom = AppTheme.size.normal
        ),
        verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
    ) {
        itemsIndexed(
            items = listOfItems,
            key = { _, item -> item.name }
        ) { index, item ->
            var isVisible by remember { mutableStateOf(true) }

            AnimatedVisibility(
                visible = isVisible,
                exit = fadeOut(animationSpec = tween(durationMillis = 500))
                        + shrinkHorizontally(animationSpec = tween(durationMillis = 500)),
            ) {
                GuestsItemCard(
                    animatedVisibilityScope = animatedVisibilityScope,
                    onItemCardClick = onItemCardClick,
                    onFavoriteIconClick = { isFavorite ->
                        listOfItems[index] = item.copy(isFavorite = isFavorite)
                    },
                    onDeleteIconClick = {
                        isVisible = false
                    },
                    isFavorite = item.isFavorite,
                    modifier = Modifier.animateContentSize(),
                    item = item
                )
            }

            if (!isVisible) {
                LaunchedEffect(item) {
                    delay(300)
                    listOfItems.removeAt(index)
                }
            }
        }
    }
}

@Serializable
data class GuestItem(
    val id: Int,
    val name: String,
    val initials: String,
    val invitation: String,
    val status: String,
    val guests: Int,
    var isFavorite: Boolean = false
)