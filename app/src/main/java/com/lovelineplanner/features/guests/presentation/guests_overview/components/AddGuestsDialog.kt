package com.lovelineplanner.features.guests.presentation.guests_overview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lovelineplanner.core.presentation.composables.DropdownSelector
import com.lovelineplanner.ui.theme.AppTheme

@Composable
fun AddGuestsDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirm: (
        nameSurname: String,
        guests: Int,
        invitation: String,
        status: String,
        note: String
    ) -> Unit
) {
    var nameSurname by remember { mutableStateOf("") }
    var guests by remember { mutableStateOf("") }
    var invitation by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    val maxNoteLength = 2000
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

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "Enter Details",
                    style = AppTheme.typography.titleLarge,
                    color = AppTheme.colorScheme.onBackground
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.size.small)
                    .verticalScroll(rememberScrollState()),
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
                    value = guests,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }) guests = newValue
                    },
                    placeholder = {
                        Text(
                            text = "Guests",
                            style = AppTheme.typography.labelNormal,
                            color = AppTheme.colorScheme.onBackground
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = AppTheme.shape.button,
                    textStyle = AppTheme.typography.body,
                    singleLine = true,
                    colors = colors
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DropdownSelector(
                        modifier = Modifier
                            .wrapContentWidth()
                            .weight(1f),
                        placeholder = "Invitation",
                        options = listOf("Sent", "Not sent"),
                        selectedOption = invitation,
                        onOptionSelected = { invitation = it }
                    )
                    DropdownSelector(
                        modifier = Modifier
                            .wrapContentWidth()
                            .weight(1f),
                        placeholder = "Status",
                        options = listOf("Confirmed", "Pending", "Rejected"),
                        selectedOption = status,
                        onOptionSelected = { status = it }
                    )
                }
                Column {
                    Text(
                        text = "Note",
                        style = AppTheme.typography.labelNormal,
                        color = AppTheme.colorScheme.onBackground.copy(0.8f)
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
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (guests.isNotEmpty()) {
                        onConfirm(
                            nameSurname,
                            guests.toInt(),
                            invitation,
                            status,
                            note
                        )
                        onDismissRequest()
                    }
                }) {
                Text(
                    text = "Add",
                    style = AppTheme.typography.body,
                    color = AppTheme.colorScheme.onBackground
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    text = "Cancel",
                    style = AppTheme.typography.body,
                    color = AppTheme.colorScheme.onBackground.copy(0.8f)
                )
            }
        },
        containerColor = AppTheme.colorScheme.background
    )
}

@PreviewLightDark
@Composable
private fun AddGuestsDialogPreview() {
    AppTheme {
        AddGuestsDialog(
            modifier = Modifier.background(AppTheme.colorScheme.onBackground),
            onDismissRequest = {},
            onConfirm = { _, _, _, _, _ -> }
        )
    }
}