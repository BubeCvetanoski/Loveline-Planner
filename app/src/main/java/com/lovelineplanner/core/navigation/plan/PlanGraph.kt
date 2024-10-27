package com.lovelineplanner.core.navigation.plan

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lovelineplanner.PlanScreen
import kotlinx.serialization.Serializable

@Serializable
data object Plan

@Serializable
data object PlanGraph

fun NavGraphBuilder.planGraph(
    navController: NavController
) {
    navigation<PlanGraph>(startDestination = Plan) {
        composable<Plan> {
            PlanScreen()
        }
    }
}