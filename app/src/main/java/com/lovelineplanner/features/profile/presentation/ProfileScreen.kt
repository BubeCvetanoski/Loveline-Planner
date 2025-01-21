package com.lovelineplanner.features.profile.presentation

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lovelineplanner.core.presentation.composables.EditSwitch
import com.lovelineplanner.core.presentation.theme.AppTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    var isAllowedToEdit by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.height(248.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(97.dp)
                        .clip(AppTheme.shape.button)
                        .background(AppTheme.colorScheme.background)
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(96.dp)
                            .clip(AppTheme.shape.button)
                            .background(
                                color = AppTheme.colorScheme.primary,
                                shape = AppTheme.shape.button
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (imageUri.value != null) {
                            AsyncImage(
                                model = imageUri.value,
                                contentDescription = "profile_image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Text(
                                text = "Blank\nImage",
                                style = AppTheme.typography.body,
                                color = AppTheme.colorScheme.onPrimary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            Text(
                text = "Bube Cvetanoski",
                style = AppTheme.typography.titleNormal,
                textAlign = TextAlign.Center,
                color = AppTheme.colorScheme.onBackground
            )
            Text(
                text = "Contact: +389 70 000 000",
                style = AppTheme.typography.body,
                color = AppTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(AppTheme.size.normal))
        }


        EditSwitch(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(AppTheme.size.normal),
            onSwitched = { getIsAllowedToEdit ->
                isAllowedToEdit = getIsAllowedToEdit
            }
        )
    }
}

@PreviewLightDark
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            modifier = Modifier.background(AppTheme.colorScheme.background)
        )
    }
}