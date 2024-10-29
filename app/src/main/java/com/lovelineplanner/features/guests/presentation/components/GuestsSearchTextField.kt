package com.lovelineplanner.features.guests.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.lovelineplanner.R
import com.lovelineplanner.ui.theme.AppTheme

@Composable
fun GuestsSearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    Box(
        modifier = modifier.padding(
            start = AppTheme.size.normal,
            end = AppTheme.size.normal
        )
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = "Search...",
                    style = AppTheme.typography.body
                )
            },
            singleLine = true,
            shape = AppTheme.shape.button,
            textStyle = AppTheme.typography.body,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = AppTheme.colorScheme.background,
                focusedContainerColor = AppTheme.colorScheme.background,
                unfocusedIndicatorColor = AppTheme.colorScheme.primary,
                focusedIndicatorColor = AppTheme.colorScheme.secondary,
                unfocusedTextColor = AppTheme.colorScheme.onBackground.copy(0.8f),
                focusedTextColor = AppTheme.colorScheme.onBackground,
                unfocusedTrailingIconColor = AppTheme.colorScheme.onBackground,
                focusedTrailingIconColor = AppTheme.colorScheme.onBackground,
                unfocusedPlaceholderColor = AppTheme.colorScheme.onBackground,
                focusedPlaceholderColor = AppTheme.colorScheme.onBackground.copy(0.8f),
                cursorColor = AppTheme.colorScheme.onBackground
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(id = R.string.search),
                    tint = AppTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clickable { onSearch() }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically)
                .background(AppTheme.colorScheme.background),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
        )
    }
}

@PreviewLightDark
@Composable
private fun GuestsSearchTextFieldPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
        ) {
            GuestsSearchTextField(
                text = "",
                onValueChange = {},
                onSearch = {}
            )
        }
    }
}