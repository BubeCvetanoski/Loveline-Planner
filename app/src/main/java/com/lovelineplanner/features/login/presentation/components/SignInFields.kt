package com.lovelineplanner.features.login.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
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
import com.lovelineplanner.core.presentation.composables.PrimaryButton
import com.lovelineplanner.ui.theme.AppTheme
import com.lovelineplanner.ui.theme.White

@Composable
fun SignInFields(
    onSignUpClicked: () -> Unit,
    onLogInClicked: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Text(
        text = "Welcome",
        color = White,
        style = AppTheme.typography.titleLarge,
        fontSize = 32.sp
    )
    Text(
        text = "Please Login to get started",
        color = White,
        style = AppTheme.typography.body
    )
    Spacer(modifier = Modifier.height(AppTheme.size.large))
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
                contentDescription = "sign_in_email_icon"
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
                contentDescription = "sign_in_lock_icon"
            )
        },
        isPasswordField = true,
        onForgotClick = {}
    )
    Spacer(modifier = Modifier.height(AppTheme.size.large))
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        label = "LOGIN",
        onClick = onLogInClicked
    )
    Spacer(modifier = Modifier.height(AppTheme.size.large))
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Don't have an account?",
            color = White,
            style = AppTheme.typography.body
        )
        Text(
            text = " Sign up",
            color = White,
            style = AppTheme.typography.body,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onSignUpClicked.invoke() }
        )
    }
}