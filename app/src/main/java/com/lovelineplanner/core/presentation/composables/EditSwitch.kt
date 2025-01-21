package com.lovelineplanner.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material.icons.rounded.ModeEdit
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lovelineplanner.core.presentation.theme.AppTheme

@Composable
fun EditSwitch(
    modifier: Modifier = Modifier,
    onSwitched: (Boolean) -> Unit
) {
    var isChecked by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .size(72.dp, 36.dp)
            .background(
                color = if (isChecked)
                    AppTheme.colorScheme.primary
                else
                    AppTheme.colorScheme.primary.copy(alpha = 0.9f),
                shape = AppTheme.shape.button
            )
            .border(
                width = 1.dp,
                color = if (isChecked)
                    AppTheme.colorScheme.secondary
                else
                    AppTheme.colorScheme.secondary.copy(alpha = 0.8f),
                shape = AppTheme.shape.button
            )
            .clickable {
                isChecked = !isChecked
                onSwitched(isChecked)
            }
    ) {
        Icon(
            imageVector = if (isChecked)
                Icons.Rounded.ModeEdit
            else
                Icons.Outlined.ModeEdit,
            contentDescription = "guests_details_screen_header_switch",
            tint = if (isChecked)
                AppTheme.colorScheme.secondary
            else
                AppTheme.colorScheme.secondary.copy(alpha = 0.8f),
            modifier = Modifier
                .size(24.dp)
                .offset(x = if (isChecked) 12.dp else (-12).dp)
                .align(Alignment.Center)
        )
    }
}

@PreviewLightDark
@Composable
fun EditSwitchPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.background(AppTheme.colorScheme.background)
        ) {
            EditSwitch {  }
        }
    }
}