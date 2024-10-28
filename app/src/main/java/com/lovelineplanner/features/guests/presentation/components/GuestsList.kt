package com.lovelineplanner.features.guests.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lovelineplanner.ui.theme.AppTheme

@Composable
fun GuestsList(
    modifier: Modifier = Modifier
) {
    val listOfItems = listOf(
        "Item1",
        "Item2",
        "Item3",
        "Item4",
        "Item5",
        "Item6",
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(AppTheme.size.normal),
        verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
    ) {
        items(listOfItems) { item ->
            GuestsItemCard()
        }
    }
}