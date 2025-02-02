package com.lovelineplanner.core.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem<T : Any>(
    val name: String,
    val route: T,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
