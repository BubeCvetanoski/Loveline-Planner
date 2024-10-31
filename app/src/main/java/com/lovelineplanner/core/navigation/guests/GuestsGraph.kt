package com.lovelineplanner.core.navigation.guests

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lovelineplanner.features.guests.presentation.guests_overview.GuestsScreen
import kotlinx.serialization.Serializable

@Serializable
data object Guests

@Serializable
data object GuestsGraph

fun NavGraphBuilder.guestsGraph(
    navController: NavController
) {
    navigation<GuestsGraph>(startDestination = Guests) {
        composable<Guests> {
            AdaptiveGuestsListDetailPane()
        }
    }
}