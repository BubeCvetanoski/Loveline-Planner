package com.lovelineplanner.features.login.presentation.components

import LoginDatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lovelineplanner.ui.composables.PrimaryButton
import com.lovelineplanner.ui.theme.AppTheme
import com.lovelineplanner.ui.theme.White

@Composable
fun SignUpFields(
    onSignInClicked: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<Long?>(null) }

    Text(
        text = "Get started",
        color = White,
        style = AppTheme.typography.titleLarge,
        fontSize = 32.sp
    )
    Text(
        text = "Create a free account",
        color = White,
        style = AppTheme.typography.body
    )
    Spacer(modifier = Modifier.height(AppTheme.size.large))
    LoginTextField(
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "Username",
                style = AppTheme.typography.body
            )
        },
        onValueChange = { newText -> username = newText },
        text = username,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "sign_up_username_icon"
            )
        }
    )
    Spacer(modifier = Modifier.height(AppTheme.size.medium))
    LoginDatePicker(
        selectedDate = selectedDate,
        onDateSelected = { newDate -> selectedDate = newDate }
    )
    Spacer(modifier = Modifier.height(AppTheme.size.medium))
    LoginTextField(
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "Email",
                style = AppTheme.typography.body
            )
        },
        onValueChange = { newText -> email = newText },
        text = "",
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = "sign_up_email_icon"
            )
        }
    )
    Spacer(modifier = Modifier.height(AppTheme.size.medium))
    LoginTextField(
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "Password",
                style = AppTheme.typography.body
            )
        },
        onValueChange = { newText -> password = newText },
        text = password,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "sign_up_lock_icon"
            )
        },
        isPasswordField = true,
        hasVisibilityToggle = true
    )
    Spacer(modifier = Modifier.height(AppTheme.size.large))
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        label = "CREATE",
        onClick = {}
    )
    Spacer(modifier = Modifier.height(AppTheme.size.large))
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Already have an account?",
            color = White,
            style = AppTheme.typography.body
        )
        Text(
            text = " Sign in",
            color = White,
            style = AppTheme.typography.body,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onSignInClicked.invoke() }
        )
    }
}