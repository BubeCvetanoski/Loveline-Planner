package com.lovelineplanner.features.guests.presentation.guests_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CardGiftcard
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.PeopleOutline
import androidx.compose.material.icons.rounded.TableRestaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.lovelineplanner.features.guests.presentation.guests_details.components.EditSwitch
import com.lovelineplanner.features.guests.presentation.guests_details.components.GuestsDetailsScreenHeader
import com.lovelineplanner.ui.theme.AppTheme

@Composable
fun GuestsDetailsScreen(
    modifier: Modifier = Modifier
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
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(96.dp)
                            .clip(AppTheme.shape.button)
                            .background(
                                color = AppTheme.colorScheme.primary,
                                shape = AppTheme.shape.button
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "BC",
                            style = AppTheme.typography.titleNormal,
                            color = AppTheme.colorScheme.onPrimary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            if (isAllowedToEdit)
                EditableScreen()
            else
                NonEditableScreen()
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

@Composable
private fun EditableScreen() {

}

@Composable
private fun NonEditableScreen() {
    Text(
        text = "Bube Cvetanoski",
        style = AppTheme.typography.titleNormal,
        textAlign = TextAlign.Center,
        color = AppTheme.colorScheme.onBackground
    )
    Text(
        text = "Contact: +389 70 000 000",
        style = AppTheme.typography.body,
        color = AppTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(AppTheme.size.large))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = AppTheme.size.normal,
                end = AppTheme.size.normal
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
    ) {
        var numberOfElements by remember {
            mutableIntStateOf(4)
        }
        var status by remember {
            mutableStateOf("Pending")
        }
        if (status == "Confirmed") numberOfElements = 4

        val listOfTexts = listOf(
            "Sent",
            "Confirmed",
            "+2",
            "1"
        )
        val listOfTexts2 = listOf(
            "Invitation",
            "Status",
            "Guests",
            "Table number"
        )
        //TODO if the status is pending, then 3 dots or loading icon, if the status is Rejected then X icon
        val listOfIcons = listOf(
            Icons.Rounded.CardGiftcard,
            Icons.Rounded.Check,
            Icons.Rounded.PeopleOutline,
            Icons.Rounded.TableRestaurant
        )
        repeat(numberOfElements) { index ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(AppTheme.shape.button)
                        .background(AppTheme.colorScheme.onBackground.copy(alpha = 0.8f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = listOfIcons[index],
                        contentDescription = "Icon",
                        tint = AppTheme.colorScheme.background,
                        modifier = Modifier.padding(AppTheme.size.small)
                    )
                }
                Text(
                    text = listOfTexts[index],
                    style = AppTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    color = AppTheme.colorScheme.onBackground
                )
                Text(
                    text = listOfTexts2[index],
                    style = AppTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    color = AppTheme.colorScheme.onBackground.copy(alpha = 0.9f)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(AppTheme.size.small))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(AppTheme.size.normal),
        verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
    ) {
        Text(
            text = "Guest status: 2 adults",
            style = AppTheme.typography.body,
            color = AppTheme.colorScheme.onBackground
        )
        Text(
            text = "Dietary preferences: 1 vegetarian, 1 HALAL",
            style = AppTheme.typography.body,
            color = AppTheme.colorScheme.onBackground
        )
        Text(
            text = "Accommodation: Needed",
            style = AppTheme.typography.body,
            color = AppTheme.colorScheme.onBackground
        )
        Text(
            text = "Reminder: Tell them to bring something specific",
            style = AppTheme.typography.body,
            color = AppTheme.colorScheme.onBackground
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.size.normal)
    ) {
        Text(
            text = "Note",
            style = AppTheme.typography.body,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(2.dp))
        Column(
            modifier = Modifier
                .clip(AppTheme.shape.container)
                .background(
                    color = AppTheme.colorScheme.primary.copy(0.3f),
                    shape = AppTheme.shape.container
                )
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier.padding(AppTheme.size.small),
                text = "The stupid one remembers, the smart one writes." +
                        "The stupid one remembers, the smart one writes." +
                        "The stupid one remembers, the smart one writes." +
                        "The stupid one remembers, the smart one writes." +
                        "The stupid one remembers, the smart one writes." +
                        "The stupid one remembers, the smart one writes." +
                        "The stupid one remembers, the smart one writes." +
                        "The stupid one remembers, the smart one writes.",
                style = AppTheme.typography.body,
                color = AppTheme.colorScheme.onBackground
            )
        }
    }
}


@PreviewLightDark
@Composable
private fun GuestsDetailsScreenPreview() {
    AppTheme {
        GuestsDetailsScreen(
            modifier = Modifier.background(AppTheme.colorScheme.background)
        )
    }
}