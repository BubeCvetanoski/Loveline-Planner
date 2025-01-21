package com.lovelineplanner.features.guests.presentation.guests_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lovelineplanner.core.presentation.composables.CountSwitch
import com.lovelineplanner.core.presentation.composables.LabeledDropdownSelector
import com.lovelineplanner.core.presentation.theme.AppTheme

@Composable
fun EditableScreen() {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
        ) {
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
        }
        Spacer(modifier = Modifier.height(AppTheme.size.large))
        Text(
            text = "Invitation info",
            style = AppTheme.typography.titleNormal,
            color = AppTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.size.normal)
        )
        Spacer(modifier = Modifier.height(AppTheme.size.normal))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.size.normal),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
        ) {
            val listOfPlaceholders = listOf(
                "Invitation:",
                "Status:",
                "Guests:",
                "Table number:",
                "Accommodation:"
            )
            val listOfOptions = listOf(
                listOf("Sent", "Not sent"),
                listOf("Confirmed", "Pending", "Rejected"),
                (0..50).map { it.toString() },
                (0..50).map { it.toString() },
                listOf("Needed", "Not needed")
            )
            val listOfSelectedOptions = remember {
                mutableStateListOf("", "", "", "", "")
            }

            listOfPlaceholders.forEachIndexed { index, placeholder ->
                LabeledDropdownSelector(
                    label = placeholder,
                    options = listOfOptions[index],
                    selectedOption = listOfSelectedOptions[index],
                    onOptionSelected = { listOfSelectedOptions[index] = it }
                )
            }
        }
        Spacer(modifier = Modifier.height(AppTheme.size.normal))
        Text(
            text = "Guests info",
            style = AppTheme.typography.titleNormal,
            color = AppTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.size.normal)
        )
        Spacer(modifier = Modifier.height(AppTheme.size.normal))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.size.normal),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small),
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
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small)
            ) {
                var defaultMealTypeCount by remember {
                    mutableIntStateOf(0)
                }
                var halalCount by remember {
                    mutableIntStateOf(0)
                }
                CountSwitch(
                    label = "Standard:",
                    count = defaultMealTypeCount,
                    onCountChange = { defaultMealTypeCount = it }
                )
                CountSwitch(
                    label = "Halal:",
                    count = halalCount,
                    onCountChange = { halalCount = it }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small)
            ) {
                var vegetarianCount by remember {
                    mutableIntStateOf(0)
                }
                var veganCount by remember {
                    mutableIntStateOf(0)
                }
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
            Spacer(modifier = Modifier.height(AppTheme.size.normal))
            Text(
                text = "Additional info",
                style = AppTheme.typography.titleNormal,
                color = AppTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(AppTheme.size.small))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = AppTheme.size.normal)
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
    }
}