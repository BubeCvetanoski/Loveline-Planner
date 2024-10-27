package com.lovelineplanner.ui.composables

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import com.lovelineplanner.ui.theme.AppTheme
import com.lovelineplanner.ui.theme.LightGray
import com.lovelineplanner.ui.theme.Transparent
import com.lovelineplanner.ui.theme.White
import com.lovelineplanner.core.common.util.HelperFunctions.convertMillisToDate

@Composable
fun LoginDatePicker(
    modifier: Modifier = Modifier,
    selectedDate: Long?,
    onDateSelected: (Long?) -> Unit,
) {
    var showModal by remember { mutableStateOf(false) }
    val transparentWhiteColor = LightGray.copy(alpha = 0.4f)

    OutlinedTextField(
        value = selectedDate?.let { convertMillisToDate(it) } ?: "",
        onValueChange = { },
        placeholder = {
            Text(
                text = "Wedding Date",
                style = AppTheme.typography.body
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = "Select date"
            )
        },
        shape = AppTheme.shape.button,
        textStyle = AppTheme.typography.body,
        singleLine = true,
        readOnly = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = transparentWhiteColor,
            focusedContainerColor = transparentWhiteColor,
            unfocusedIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent,
            unfocusedTextColor = White,
            focusedTextColor = White,
            unfocusedLeadingIconColor = White,
            focusedLeadingIconColor = White,
            unfocusedTrailingIconColor = White,
            focusedTrailingIconColor = White,
            unfocusedSuffixColor = White,
            focusedSuffixColor = White,
            unfocusedPlaceholderColor = White,
            focusedPlaceholderColor = White,
            cursorColor = White
        ),
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(selectedDate) {
                awaitEachGesture {
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        showModal = true
                    }
                }
            }
    )

    if (showModal) {
        DatePickerModal(
            selectedDate = selectedDate,
            onDateSelected = { onDateSelected(it) },
            onDismiss = { showModal = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    selectedDate: Long?,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate // Initialize with the current selected date
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Box(
                modifier = Modifier.padding(end = AppTheme.size.small)
            ) {
                PrimaryButton(
                    label = "OK",
                    onClick = {
                        onDateSelected(datePickerState.selectedDateMillis)
                        onDismiss()
                    }
                )
            }
        },
        dismissButton = {
            PrimaryButton(
                label = "Cancel",
                onClick = onDismiss
            )
        },
        colors = DatePickerDefaults.colors(
            containerColor = AppTheme.colorScheme.background
        )
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                containerColor = AppTheme.colorScheme.background,
                titleContentColor = AppTheme.colorScheme.onBackground,
                headlineContentColor = AppTheme.colorScheme.onBackground,
                weekdayContentColor = AppTheme.colorScheme.onBackground,
                subheadContentColor = AppTheme.colorScheme.onBackground,
                navigationContentColor = AppTheme.colorScheme.onBackground,
                yearContentColor = AppTheme.colorScheme.onBackground,
                currentYearContentColor = AppTheme.colorScheme.onBackground,
                selectedYearContainerColor = AppTheme.colorScheme.primary,
                selectedYearContentColor = AppTheme.colorScheme.onPrimary,
                dayContentColor = AppTheme.colorScheme.onBackground,
                todayContentColor = AppTheme.colorScheme.onBackground,
                todayDateBorderColor = AppTheme.colorScheme.onBackground,
                selectedDayContentColor = AppTheme.colorScheme.onPrimary,
                selectedDayContainerColor = AppTheme.colorScheme.primary,
                dividerColor = AppTheme.colorScheme.secondary,
                dateTextFieldColors = TextFieldDefaults.colors(
                    unfocusedContainerColor = AppTheme.colorScheme.background,
                    focusedContainerColor = AppTheme.colorScheme.background,
                    unfocusedIndicatorColor = AppTheme.colorScheme.onBackground,
                    focusedIndicatorColor = AppTheme.colorScheme.secondary,
                    unfocusedTextColor = AppTheme.colorScheme.onBackground,
                    focusedTextColor = AppTheme.colorScheme.onBackground,
                    unfocusedPlaceholderColor = AppTheme.colorScheme.onBackground,
                    focusedPlaceholderColor = AppTheme.colorScheme.onBackground,
                    cursorColor = AppTheme.colorScheme.onBackground,
                    focusedLabelColor = AppTheme.colorScheme.secondary,
                    unfocusedLabelColor = AppTheme.colorScheme.onBackground
                )
            )
        )
    }
}