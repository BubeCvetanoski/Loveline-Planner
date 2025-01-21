package com.lovelineplanner.features.guests.presentation.guests_overview.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lovelineplanner.core.presentation.theme.AppTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GuestsItemCard(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemCardClick: (GuestItem) -> Unit,
    onFavoriteIconClick: (Boolean) -> Unit,
    onDeleteIconClick: () -> Unit,
    isFavorite: Boolean,
    item: GuestItem
) {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(isFavorite) {
        val targetScale = if (isFavorite) 1.2f else 0.8f

        scale.animateTo(
            targetValue = targetScale,
            animationSpec = tween(durationMillis = 150)
        )
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 150)
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
                .background(AppTheme.colorScheme.background)
                .clip(AppTheme.shape.container)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = AppTheme.colorScheme.primary
                    ),
                    shape = AppTheme.shape.container
                )
                .padding(AppTheme.size.normal)
                .clickable { onItemCardClick(item) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            InitialsCircle(
                modifier = Modifier
                    .size(48.dp)
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
                textStyle = AppTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.width(AppTheme.size.large))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = AppTheme.typography.labelLarge,
                    color = AppTheme.colorScheme.onBackground
                )
                Text(
                    text = "Invitation: ${item.invitation}",
                    style = AppTheme.typography.labelNormal,
                    color = AppTheme.colorScheme.onBackground
                )
                Text(
                    text = "Status: ${item.status}",
                    style = AppTheme.typography.labelNormal,
                    color = AppTheme.colorScheme.onBackground
                )
            }
            Text(
                text = "+${item.guests}",
                style = AppTheme.typography.labelLarge,
                color = AppTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onFavoriteIconClick(!isFavorite) },
                modifier = Modifier
                    .size(26.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite)
                        Icons.Rounded.Favorite
                    else
                        Icons.Rounded.FavoriteBorder,
                    contentDescription = "guests_item_card_favorite",
                    tint = AppTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(2.dp)
                        .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
                )
            }
            IconButton(
                onClick = onDeleteIconClick,
                modifier = Modifier.size(26.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "guests_item_card_bin",
                    tint = AppTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}


//@OptIn(ExperimentalSharedTransitionApi::class)
//@PreviewLightDark
//@Composable
//private fun SharedTransitionScope.GuestsItemCardPreview() {
//    AppTheme {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(AppTheme.colorScheme.background)
//        ) {
//            GuestsItemCard(
//                animatedVisibilityScope = this,
//                onItemCardClick = {},
//                onFavoriteIconClick = {},
//                onDeleteIconClick = {},
//                isFavorite = false,
//                index = 1
//            )
//        }
//    }
//}