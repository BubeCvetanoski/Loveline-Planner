package com.lovelineplanner.presentation.ui.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lovelineplanner.presentation.ui.theme.AppTheme
import com.lovelineplanner.presentation.ui.theme.LightGray
import com.lovelineplanner.presentation.ui.theme.Transparent
import com.lovelineplanner.presentation.ui.theme.White

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val transparentWhiteColor = LightGray.copy(alpha = 0.4f)

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = AppTheme.shape.button,
        textStyle = AppTheme.typography.body,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = transparentWhiteColor,
            focusedContainerColor = transparentWhiteColor,
            unfocusedIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent,
            unfocusedTextColor = White,
            focusedTextColor = White,
            cursorColor = White,
            unfocusedLeadingIconColor = White,
            focusedLeadingIconColor = White,
            unfocusedTrailingIconColor = White,
            focusedTrailingIconColor = White,
            unfocusedSuffixColor = White,
            focusedSuffixColor = White
        )
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFF1E1E1E)
@Composable
private fun PreviewTextFields() {
    AppTheme {
        Column(
            modifier = Modifier.padding(AppTheme.size.medium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
        ) {
            LoginTextField(
                onValueChange = {},
                value = "Email",
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) }
            )
            LoginTextField(
                onValueChange = {},
                value = "Password",
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null) },
                trailingIcon = { Text("Forgot?") }
            )
        }
    }
}