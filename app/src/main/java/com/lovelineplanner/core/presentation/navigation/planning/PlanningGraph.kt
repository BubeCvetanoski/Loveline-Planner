package com.lovelineplanner.core.presentation.navigation.planning

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lovelineplanner.features.planning.presentation.PlanningScreen
import kotlinx.serialization.Serializable

@Serializable
data object Planning

@Serializable
data object PlanningGraph

fun NavGraphBuilder.planningGraph(
    navController: NavController
) {
    navigation<PlanningGraph>(startDestination = Planning) {
        composable<Planning> {
            PlanningScreen()
        }
    }
}