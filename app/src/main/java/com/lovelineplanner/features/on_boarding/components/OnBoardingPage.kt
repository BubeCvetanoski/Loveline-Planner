package com.lovelineplanner.features.on_boarding.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lovelineplanner.R
import com.lovelineplanner.features.on_boarding.components.model.OnBoardingPageItem
import com.lovelineplanner.core.presentation.theme.AppTheme
import com.lovelineplanner.core.presentation.theme.DarkGold
import com.lovelineplanner.core.presentation.theme.Gold
import com.lovelineplanner.core.presentation.theme.Transparent
import com.lovelineplanner.core.presentation.theme.White

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    totalPages: Int,
    currentPageIndex: Int,
    page: OnBoardingPageItem,
    onButtonClick: () -> Unit
) {
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(
        key1 = Unit,
        key2 = currentPageIndex
    ) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 64.dp,
                        topEnd = 64.dp
                    )
                )
                .background(Color.Transparent.copy(alpha = 0.4f))
                .graphicsLayer {
                    this.alpha = alpha.value
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 32.dp,
                        end = 32.dp,
                        top = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = page.title,
                    style = AppTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(AppTheme.size.normal))
                Text(
                    text = page.description,
                    style = AppTheme.typography.body,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.size.normal))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 8.dp
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(totalPages) { index ->
                            val isCurrentImage = currentPageIndex == index

                            Box(
                                modifier = Modifier
                                    .size(
                                        width = if (isCurrentImage) 4.dp else 4.dp,
                                        height = if (isCurrentImage) 20.dp else 4.dp
                                    )
                                    .clip(CircleShape)
                                    .background(
                                        if (isCurrentImage)
                                            Gold
                                        else
                                            DarkGold
                                    )
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(color = Transparent)
                            .clickable(onClick = onButtonClick)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(32.dp)
                                .height(40.dp)
                                .background(color = White)
                                .align(Alignment.Center)
                        ) { }
                        Icon(
                            painter = painterResource(page.icon),
                            contentDescription = "on_boarding_arrow_right",
                            tint = Gold,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun OnBoardingPagePreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            OnBoardingPage(
                totalPages = 3,
                currentPageIndex = 2,
                page = OnBoardingPageItem(
                    title = "Guest List\nManagement",
                    description = "Track invitations and responses, note dietary preferences and accommodations or simply organize valuable details—all in one place.",
                    image = R.drawable.on_boarding_1,
                    icon = R.drawable.rounded_door_open_24
                ),
                onButtonClick = {}
            )
        }
    }
}