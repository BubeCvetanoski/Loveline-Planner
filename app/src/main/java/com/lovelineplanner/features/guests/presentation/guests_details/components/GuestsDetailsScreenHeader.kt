package com.lovelineplanner.features.guests.presentation.guests_details.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.lovelineplanner.R
import com.lovelineplanner.core.presentation.composables.InvertedVerticalGradientOverlay
import com.lovelineplanner.core.presentation.theme.AppTheme
import com.lovelineplanner.core.presentation.theme.White

@Composable
fun GuestsDetailsScreenHeader(
    modifier: Modifier = Modifier,
    text: String,
    context: Context
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.guests_details_background)
                .build(),
            contentDescription = "guests_details_header_image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        InvertedVerticalGradientOverlay()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.size.normal),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                color = White,
                style = AppTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}