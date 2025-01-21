package com.lovelineplanner.core.presentation.navigation.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lovelineplanner.features.profile.presentation.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object Profile

@Serializable
data object ProfileGraph

fun NavGraphBuilder.profileGraph(
    navController: NavController
) {
    navigation<ProfileGraph>(startDestination = Profile) {
        composable<Profile> {
            ProfileScreen()
        }
    }
}