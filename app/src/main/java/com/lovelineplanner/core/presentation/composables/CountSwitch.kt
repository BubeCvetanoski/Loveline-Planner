package com.lovelineplanner.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lovelineplanner.core.presentation.theme.AppTheme
import com.lovelineplanner.core.presentation.theme.Transparent

@Composable
fun CountSwitch(
    modifier: Modifier = Modifier,
    label: String,
    count: Int,
    onCountChange: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = AppTheme.typography.body,
            color = AppTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(end = AppTheme.size.small)
        )
        Row(
            modifier = Modifier
                .height(46.dp)
                .border(
                    width = 1.dp,
                    color = AppTheme.colorScheme.primary,
                    shape = AppTheme.shape.button
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { if (count > 0) onCountChange(count - 1) },
                enabled = count > 0,
                modifier = Modifier
                    .size(40.dp)
                    .background(Transparent)
            ) {
                Text(
                    text = "-",
                    style = AppTheme.typography.body,
                    color = AppTheme.colorScheme.onBackground
                )
            }

            Text(
                text = count.toString(),
                style = AppTheme.typography.body,
                color = if (count > 0)
                    AppTheme.colorScheme.onBackground
                else
                    AppTheme.colorScheme.onBackground.copy(0.8f),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            IconButton(
                onClick = { onCountChange(count + 1) },
                modifier = Modifier
                    .size(40.dp)
                    .background(Transparent)
            ) {
                Text(
                    text = "+",
                    style = AppTheme.typography.body,
                    color = AppTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun NumberSwitchPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
        ) {
            CountSwitch(
                label = "Test:",
                count = 0,
                onCountChange = {}
            )
        }
    }
}