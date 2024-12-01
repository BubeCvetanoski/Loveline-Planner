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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CardGiftcard
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.PeopleOutline
import androidx.compose.material.icons.rounded.TableRestaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lovelineplanner.core.presentation.composables.CountSwitch
import com.lovelineplanner.core.presentation.composables.DropdownSelector
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
    var nameSurname by remember { mutableStateOf("Bube Cvetanoski") }
    var contact by remember { mutableStateOf("Contact: +389 70 000 000") }
    val colors = TextFieldDefaults.colors(
        unfocusedContainerColor = AppTheme.colorScheme.background,
        focusedContainerColor = AppTheme.colorScheme.background,
        unfocusedIndicatorColor = AppTheme.colorScheme.onBackground.copy(0.8f),
        focusedIndicatorColor = AppTheme.colorScheme.onBackground,
        unfocusedTextColor = AppTheme.colorScheme.onBackground,
        focusedTextColor = AppTheme.colorScheme.onBackground,
        unfocusedPlaceholderColor = AppTheme.colorScheme.onBackground.copy(0.8f),
        focusedPlaceholderColor = AppTheme.colorScheme.onBackground.copy(0.8f),
        cursorColor = AppTheme.colorScheme.onBackground
    )

    OutlinedTextField(
        value = nameSurname,
        onValueChange = { nameSurname = it },
        placeholder = {
            Text(
                text = "Name and Surname",
                style = AppTheme.typography.labelNormal,
                color = AppTheme.colorScheme.onBackground
            )
        },
        modifier = Modifier.fillMaxWidth(),
        shape = AppTheme.shape.button,
        textStyle = AppTheme.typography.body,
        singleLine = true,
        colors = colors
    )
    OutlinedTextField(
        value = contact,
        onValueChange = { contact = it },
        placeholder = {
            Text(
                text = "Contact",
                style = AppTheme.typography.labelNormal,
                color = AppTheme.colorScheme.onBackground
            )
        },
        modifier = Modifier.fillMaxWidth(),
        shape = AppTheme.shape.button,
        textStyle = AppTheme.typography.body,
        singleLine = true,
        colors = colors
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
        var invitation by remember {
            mutableStateOf("")
        }
        var status by remember {
            mutableStateOf("Pending")
        }
        var guests by remember {
            mutableStateOf("")
        }
        var tableNumber by remember {
            mutableStateOf("")
        }
        if (status == "Confirmed") numberOfElements = 4

        val listOfPlaceholders = listOf(
            "Invitation",
            "Status",
            "Guests",
            "Table number"
        )
        val listOfOptions = listOf(
            listOf("Sent", "Not sent"),
            listOf("Confirmed", "Pending", "Rejected"),
            listOf((0..50).toString()),
            listOf((0..50).toString())
        )
        var listOfSelectedOptions = remember {
            mutableListOf(invitation, status, guests, tableNumber)
        }
        //TODO if the status is pending, then 3 dots or loading icon, if the status is Rejected then X icon
        repeat(numberOfElements) { index ->
            DropdownSelector(
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f),
                placeholder = listOfPlaceholders[index],
                options = listOfOptions[index],
                selectedOption = listOfSelectedOptions[index],
                onOptionSelected = { listOfSelectedOptions[index] = it }
            )
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = AppTheme.size.normal,
                    end = AppTheme.size.normal
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            var adultsCount by remember {
                mutableIntStateOf(0)
            }
            var kidsCount by remember {
                mutableIntStateOf(0)
            }
            CountSwitch(
                label = "Adults:",
                count = adultsCount,
                onCountChange = { adultsCount = it }
            )
            CountSwitch(
                label = "Kids:",
                count = kidsCount,
                onCountChange = { kidsCount = it }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = AppTheme.size.normal,
                    end = AppTheme.size.normal
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            var defaultMealTypeCount by remember {
                mutableIntStateOf(0)
            }
            var halalCount by remember {
                mutableIntStateOf(0)
            }
            var vegetarianCount by remember {
                mutableIntStateOf(0)
            }
            var veganCount by remember {
                mutableIntStateOf(0)
            }
            CountSwitch(
                label = "Default:",
                count = defaultMealTypeCount,
                onCountChange = { defaultMealTypeCount = it }
            )
            CountSwitch(
                label = "Halal:",
                count = halalCount,
                onCountChange = { halalCount = it }
            )
            CountSwitch(
                label = "Vegetarian:",
                count = vegetarianCount,
                onCountChange = { vegetarianCount = it }
            )
            CountSwitch(
                label = "Vegan:",
                count = veganCount,
                onCountChange = { veganCount = it }
            )
        }
        var accommodation by remember {
            mutableStateOf("")
        }
        DropdownSelector(
            modifier = Modifier
                .wrapContentWidth()
                .weight(1f),
            placeholder = "Accommodation",
            options = listOf("Needed", "Not needed"),
            selectedOption = accommodation,
            onOptionSelected = { accommodation = it }
        )
        var reminder by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = reminder,
            onValueChange = { reminder = it },
            placeholder = {
                Text(
                    text = "Reminder",
                    style = AppTheme.typography.labelNormal,
                    color = AppTheme.colorScheme.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = AppTheme.shape.button,
            textStyle = AppTheme.typography.body,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = AppTheme.colorScheme.background,
                focusedContainerColor = AppTheme.colorScheme.background,
                unfocusedIndicatorColor = AppTheme.colorScheme.onBackground.copy(0.8f),
                focusedIndicatorColor = AppTheme.colorScheme.onBackground,
                unfocusedTextColor = AppTheme.colorScheme.onBackground,
                focusedTextColor = AppTheme.colorScheme.onBackground,
                unfocusedPlaceholderColor = AppTheme.colorScheme.onBackground.copy(0.8f),
                focusedPlaceholderColor = AppTheme.colorScheme.onBackground.copy(0.8f),
                cursorColor = AppTheme.colorScheme.onBackground
            )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.size.normal)
    ) {
        var note by remember {
            mutableStateOf("")
        }
        val maxNoteLength = 2000

        Text(
            text = "Note",
            style = AppTheme.typography.body,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(2.dp))
        TextField(
            value = note,
            onValueChange = {
                if (it.length <= maxNoteLength) note = it
            },
            placeholder = {
                Text(
                    text = "The stupid one remembers, the smart one writes. (maximum 2000 characters)",
                    style = AppTheme.typography.labelNormal,
                    color = AppTheme.colorScheme.onBackground
                )
            },
            shape = AppTheme.shape.container,
            modifier = Modifier.fillMaxWidth(),
            textStyle = AppTheme.typography.labelNormal,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = AppTheme.colorScheme.primary.copy(0.3f),
                focusedContainerColor = AppTheme.colorScheme.primary.copy(0.3f),
                unfocusedIndicatorColor = AppTheme.colorScheme.background,
                focusedIndicatorColor = AppTheme.colorScheme.background,
                unfocusedTextColor = AppTheme.colorScheme.onBackground,
                focusedTextColor = AppTheme.colorScheme.onBackground,
                unfocusedPlaceholderColor = AppTheme.colorScheme.onBackground.copy(0.8f),
                focusedPlaceholderColor = AppTheme.colorScheme.onBackground.copy(0.8f),
                cursorColor = AppTheme.colorScheme.onBackground
            )
        )
    }
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