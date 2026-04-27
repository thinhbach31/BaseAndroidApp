package com.example.baseandroidapp.presentation.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.baseandroidapp.domain.users.User

@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    UsersContent(state = state, modifier = modifier)
}

@Composable
private fun UsersContent(state: UsersUiState, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.loading && state.users.isEmpty() -> CircularProgressIndicator()
            state.error != null && state.users.isEmpty() -> Text(
                text = "Error: ${state.error}",
                color = MaterialTheme.colorScheme.error,
            )
            else -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(0.dp),
            ) {
                items(state.users, key = User::id) { user ->
                    UserRow(user = user)
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun UserRow(user: User) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(text = user.name, style = MaterialTheme.typography.titleMedium)
        Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
    }
}
