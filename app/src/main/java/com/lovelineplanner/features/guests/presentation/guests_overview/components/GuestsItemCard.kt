package com.lovelineplanner.features.guests.presentation.guests_overview.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lovelineplanner.ui.theme.AppTheme

@Composable
fun GuestsItemCard(
    modifier: Modifier = Modifier,
    onItemCardClick: () -> Unit,
    isFavorite: Boolean
) {
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
                .clickable { onItemCardClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = AppTheme.colorScheme.primary,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "BC",
                    style = AppTheme.typography.labelLarge,
                    color = AppTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.width(AppTheme.size.large))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Bube Cvetanoski",
                    style = AppTheme.typography.labelLarge,
                    color = AppTheme.colorScheme.onBackground
                )
                Text(
                    text = "Invitation: Sent",
                    style = AppTheme.typography.labelNormal,
                    color = AppTheme.colorScheme.onBackground
                )
                Text(
                    text = "Status: Confirmed",
                    style = AppTheme.typography.labelNormal,
                    color = AppTheme.colorScheme.onBackground
                )
            }
            Text(
                text = "+2",
                style = AppTheme.typography.labelLarge,
                color = AppTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite)
                        Icons.Rounded.Favorite
                    else
                        Icons.Rounded.FavoriteBorder,
                    contentDescription = "guests_item_card_favorite",
                    tint = AppTheme.colorScheme.onBackground
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "guests_item_card_bin",
                    tint = AppTheme.colorScheme.onBackground
                )
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun GuestsItemCardPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
        ) {
            GuestsItemCard(
                onItemCardClick = {},
                isFavorite = false
            )
        }
    }
}