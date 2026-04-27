package com.example.baseandroidapp.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidapp.domain.users.GetUsersUseCase
import com.example.baseandroidapp.domain.users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class UsersViewModel @Inject constructor(
    getUsers: GetUsersUseCase,
    private val repository: UsersRepository,
) : ViewModel() {

    private val errorState = MutableStateFlow<String?>(null)
    private val loadingState = MutableStateFlow(true)

    val state: StateFlow<UsersUiState> = combine(
        getUsers().catch { errorState.value = it.message ?: "Unknown error" },
        loadingState,
        errorState,
    ) { users, loading, error -> UsersUiState(users = users, loading = loading, error = error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UsersUiState(loading = true),
        )

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            loadingState.value = true
            errorState.value = null
            runCatching { repository.refresh() }
                .onFailure { errorState.value = it.message ?: "Unknown error" }
            loadingState.value = false
        }
    }
}
