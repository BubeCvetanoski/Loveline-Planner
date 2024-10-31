package com.lovelineplanner.core.navigation.guests

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lovelineplanner.features.guests.presentation.guests_details.GuestsDetailsScreen
import com.lovelineplanner.features.guests.presentation.guests_overview.GuestsScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveGuestsListDetailPane(
    modifier: Modifier = Modifier
) {
//    val context = LocalContext.current

//    ObserveAsEvents(events = viewModel.events) { event ->
//        when (event) {
//            is CoinListEvent.Error -> Toast.makeText(
//                context,
//                event.error.toString(context),
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                GuestsScreen(
                    onItemCardClick = {
                        navigator.navigateTo(
                            pane = ListDetailPaneScaffoldRole.Detail
                        )
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                GuestsDetailsScreen()
            }
        },
        modifier = modifier
    )
}