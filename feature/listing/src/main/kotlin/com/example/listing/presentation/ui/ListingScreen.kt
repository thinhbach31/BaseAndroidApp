package com.example.listing.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.listing.presentation.viewmodel.ListingUiEvent
import com.example.listing.presentation.viewmodel.ListingViewModel

@Composable
fun ListingScreen(
    modifier: Modifier = Modifier,
    viewModel: ListingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(state.items, key = { it.id }) { item ->
            // TODO: replace with ListingRow composable
            Text(text = item.title)
        }
    }

    // Pull-to-refresh:
    // viewModel.onEvent(ListingUiEvent.Refresh)
}
