package com.lovelineplanner.core.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lovelineplanner.features.home.presentation.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object Home

@Serializable
data object HomeGraph

fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation<HomeGraph>(startDestination = Home) {
        composable<Home> {
            HomeScreen()
        }
    }
}