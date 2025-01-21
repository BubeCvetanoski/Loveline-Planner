package com.lovelineplanner.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.lovelineplanner.core.presentation.theme.AppTheme

@Composable
fun DropdownSelector(
    modifier: Modifier = Modifier,
    placeholder: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var parentWidth by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .border(
                    width = 1.dp,
                    color = if (expanded)
                        AppTheme.colorScheme.onBackground
                    else
                        AppTheme.colorScheme.onBackground.copy(0.8f),
                    shape = AppTheme.shape.button
                )
                .clip(AppTheme.shape.button)
                .background(AppTheme.colorScheme.background)
                .padding(AppTheme.size.medium)
                .onGloballyPositioned { layoutCoordinates ->
                    parentWidth = layoutCoordinates.size.width
                }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOption.ifEmpty { placeholder },
                    style = AppTheme.typography.labelNormal,
                    color = if (selectedOption.isNotEmpty())
                        AppTheme.colorScheme.onBackground
                    else
                        AppTheme.colorScheme.onBackground.copy(0.8f)
                )
                Icon(
                    imageVector = if (expanded)
                        Icons.Rounded.KeyboardArrowUp
                    else
                        Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "add_guests_dialog_arrow_down",
                    tint = AppTheme.colorScheme.onBackground
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(
                x = with(LocalDensity.current) { (parentWidth / 6).toDp() },
                y = 0.dp
            ),
            shape = AppTheme.shape.container,
            modifier = Modifier
                .width(with(LocalDensity.current) { parentWidth.toDp() })
                .background(AppTheme.colorScheme.background.copy(alpha = 0.9f))
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            style = AppTheme.typography.labelNormal,
                            color = AppTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun DropdownSelectorPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
        ) {
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
                    selectedOption = "",
                    onOptionSelected = { }
                )
                DropdownSelector(
                    modifier = Modifier
                        .wrapContentWidth()
                        .weight(1f),
                    placeholder = "Status",
                    options = listOf("Confirmed", "Pending", "Rejected"),
                    selectedOption = "",
                    onOptionSelected = { }
                )
            }
        }
    }
}