package com.lovelineplanner.core.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PeopleOutline
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lovelineplanner.core.navigation.guests.GuestsGraph
import com.lovelineplanner.core.navigation.home.HomeGraph
import com.lovelineplanner.core.navigation.planning.PlanningGraph
import com.lovelineplanner.core.navigation.profile.ProfileGraph
import com.lovelineplanner.ui.theme.AppTheme
import com.lovelineplanner.ui.theme.Transparent

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val bottomNavDestinations = remember {
        listOf(
            BottomNavItem(
                name = "Home",
                route = HomeGraph,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            BottomNavItem(
                name = "Planning",
                route = PlanningGraph,
                selectedIcon = Icons.Filled.Task,
                unselectedIcon = Icons.Outlined.Task
            ),
            BottomNavItem(
                name = "Guests",
                route = GuestsGraph,
                selectedIcon = Icons.Filled.People,
                unselectedIcon = Icons.Filled.PeopleOutline
            ),
            BottomNavItem(
                name = "Profile",
                route = ProfileGraph,
                selectedIcon = Icons.Filled.AccountCircle,
                unselectedIcon = Icons.Outlined.AccountCircle
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clip(AppTheme.shape.container)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = AppTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    ),
                    shape = AppTheme.shape.container
                )
        )
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(108.dp)
                .align(Alignment.BottomCenter)
                .clip(AppTheme.shape.container),
            containerColor = AppTheme.colorScheme.background,
            contentColor = AppTheme.colorScheme.onBackground
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            bottomNavDestinations.forEach { destination ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.hasRoute(destination.route::class)
                } == true

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(destination.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        if (isSelected) Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = destination.selectedIcon,
                            contentDescription = destination.name
                        )
                        else Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = destination.unselectedIcon,
                            contentDescription = destination.name
                        )
                    },
                    label = { Text(destination.name) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AppTheme.colorScheme.onBackground,
                        selectedTextColor = AppTheme.colorScheme.onBackground,
                        unselectedIconColor = AppTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                        unselectedTextColor = AppTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                        indicatorColor = Transparent
                    )
                )
            }
        }
    }
}