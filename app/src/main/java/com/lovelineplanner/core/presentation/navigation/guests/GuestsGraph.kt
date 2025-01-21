package com.lovelineplanner.core.presentation.navigation.guests

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.lovelineplanner.core.presentation.navigation.CustomNavType
import com.lovelineplanner.features.guests.presentation.guests_details.GuestsDetailsScreen
import com.lovelineplanner.features.guests.presentation.guests_overview.GuestsScreen
import com.lovelineplanner.features.guests.presentation.guests_overview.components.GuestItem
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class GuestsDetails(
    val item: GuestItem
)

@Serializable
data object Guests

@Serializable
data object GuestsGraph

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.guestsGraph(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope
) {
    sharedTransitionScope.apply {
        navigation<GuestsGraph>(startDestination = Guests) {
            composable<Guests> {
                GuestsScreen(
                    animatedVisibilityScope = this,
                    onItemCardClick = { item ->
                        navController.navigate(
                            GuestsDetails(item)
                        )
                    }
                )
            }
            composable<GuestsDetails>(
                typeMap = mapOf(
                    typeOf<GuestItem>() to CustomNavType.GuestItem
                )
            ) {
                val args = it.toRoute<GuestsDetails>()
                GuestsDetailsScreen(
                    animatedVisibilityScope = this,
                    item = args.item
                )
            }
        }
    }
}