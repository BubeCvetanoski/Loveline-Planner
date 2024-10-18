package com.lovelineplanner

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.lovelineplanner.presentation.ui.composables.EquallyDistributedOverlay
import com.lovelineplanner.presentation.ui.composables.LoginTextField
import com.lovelineplanner.presentation.ui.composables.PrimaryButton
import com.lovelineplanner.presentation.ui.theme.AppTheme
import com.lovelineplanner.presentation.ui.theme.White
import com.lovelineplanner.util.ExtensionFunctions.animate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                screen.animate()
            }
        }
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val view = LocalView.current
            val window = (view.context as Activity).window
            var shouldShowSystemUi by remember { mutableStateOf(false) }
            val insetController = WindowCompat.getInsetsController(window, view)

            LaunchedEffect(shouldShowSystemUi) {
                if (shouldShowSystemUi) {
                    insetController.show(WindowInsetsCompat.Type.statusBars())
                } else {
                    insetController.hide(WindowInsetsCompat.Type.statusBars())
                }
            }

            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .pointerInput(Unit) {
                                    detectVerticalDragGestures(
                                        onVerticalDrag = { _, dragAmount ->
                                            if (dragAmount > 0) {
                                                shouldShowSystemUi = true
                                            }
                                        },
                                        onDragEnd = {
                                            shouldShowSystemUi = false
                                        }
                                    )
                                }
                        )
                        LoginScreen(context = context)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    context: Context
) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                onValueChange = {},
                value = "Email",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "login_email_icon"
                    )
                }
            )
            Spacer(modifier = Modifier.height(AppTheme.size.medium))
            LoginTextField(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {},
                value = "Password",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "login_lock_icon"
                    )
                },
                trailingIcon = {
                    Text(
                        text = "Forgot?",
                        fontWeight = FontWeight.Bold,
                        style = AppTheme.typography.labelNormal,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            )
            Spacer(modifier = Modifier.height(AppTheme.size.large))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                label = "LOGIN",
                onClick = {}
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
                    text = " Sign Up",
                    color = White,
                    style = AppTheme.typography.body,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}