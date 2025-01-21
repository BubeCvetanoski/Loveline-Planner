package com.lovelineplanner.features.on_boarding.components.model

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.ui.graphics.vector.ImageVector
import com.lovelineplanner.R

data class OnBoardingPageItem(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
    @DrawableRes val icon: Int = R.drawable.round_arrow_circle_right_24
) {
    companion object {
        val pages = listOf(
            OnBoardingPageItem(
                title = "Dashboard\nSummary",
                description = "Comprehensive overview including budget and guests insights, upcoming tasks and a clear timeline to plan your journey seamlessly.",
                image = R.drawable.on_boarding_1
            ),
            OnBoardingPageItem(
                title = "Tasks and Budget Management",
                description = "Keep your wedding plans on track by managing tasks and budgets with ease, ensuring every detail is perfectly organized.",
                image = R.drawable.on_boarding_2
            ),
            OnBoardingPageItem(
                title = "Guest List\nManagement",
                description = "Track invitations and responses, note dietary preferences and accommodations or simply organize valuable details—all in one place.",
                image = R.drawable.on_boarding_3,
                icon = R.drawable.rounded_door_open_24
            )
        )
    }
}
