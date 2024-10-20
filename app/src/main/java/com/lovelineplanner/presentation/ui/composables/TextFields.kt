package com.lovelineplanner.presentation.ui.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
    text: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    isPasswordField: Boolean = false,
    hasVisibilityToggle: Boolean = false,
    onForgotClick: (() -> Unit)? = null
) {
    var isPasswordVisible by remember { mutableStateOf(true) }
    val transparentWhiteColor = LightGray.copy(alpha = 0.4f)

    OutlinedTextField(
        modifier = modifier.onFocusChanged { focusState->
            if (focusState.isFocused && isPasswordField) {
                isPasswordVisible = !isPasswordVisible
            }
        },
        placeholder = placeholder,
        value = text,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (isPasswordField) {
                if (hasVisibilityToggle) {
                    Icon(
                        imageVector = if (isPasswordVisible)
                            Icons.Outlined.Visibility
                        else
                            Icons.Outlined.VisibilityOff,
                        contentDescription = if (isPasswordVisible)
                            "Hide password"
                        else
                            "Show password",
                        modifier = Modifier
                            .clickable { isPasswordVisible = !isPasswordVisible }
                            .padding(end = 8.dp)
                    )
                } else {
                    Text(
                        text = "Forgot?",
                        fontWeight = FontWeight.Bold,
                        style = AppTheme.typography.labelNormal,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { onForgotClick?.invoke() }
                    )
                }
            }
        },
        visualTransformation = if (isPasswordField && !isPasswordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
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
            unfocusedLeadingIconColor = White,
            focusedLeadingIconColor = White,
            unfocusedTrailingIconColor = White,
            focusedTrailingIconColor = White,
            unfocusedSuffixColor = White,
            focusedSuffixColor = White,
            unfocusedPlaceholderColor = White,
            focusedPlaceholderColor = White,
            cursorColor = White
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
                text = "Email",
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) }
            )
            LoginTextField(
                onValueChange = {},
                text = "Password",
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null) },
                isPasswordField = true,
                hasVisibilityToggle = true
            )
        }
    }
}