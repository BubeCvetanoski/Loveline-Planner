package com.lovelineplanner.features.login.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.lovelineplanner.R
import com.lovelineplanner.features.login.presentation.components.SignInFields
import com.lovelineplanner.features.login.presentation.components.SignUpFields
import com.lovelineplanner.core.presentation.composables.EquallyDistributedOverlay

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLogInClicked: () -> Unit
) {
    val context = LocalContext.current
    var isSignUpScreen by remember { mutableStateOf(true) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.login_background)
                .build(),
            contentDescription = "login_background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        EquallyDistributedOverlay()

        AnimatedContent(
            targetState = isSignUpScreen
        ) { targetState ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(48.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (targetState) {
                    SignUpFields(
                        onSignInClicked = { isSignUpScreen = false }
                    )
                } else {
                    SignInFields(
                        onSignUpClicked = { isSignUpScreen = true },
                        onLogInClicked = onLogInClicked
                    )
                }
            }
        }
    }
}