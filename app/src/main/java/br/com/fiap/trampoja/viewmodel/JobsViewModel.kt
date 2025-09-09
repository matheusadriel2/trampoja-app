package br.com.fiap.trampoja.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.trampoja.data.JobsRepository
import br.com.fiap.trampoja.di.Job
import kotlinx.coroutines.Job as CoJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface JobsUiState {
    data object Idle : JobsUiState
    data object Loading : JobsUiState
    data class Success(val jobs: List<Job>) : JobsUiState
    data class Error(val message: String) : JobsUiState
}

class JobsViewModel(private val repo: JobsRepository) : ViewModel() {

    private val _state = MutableStateFlow<JobsUiState>(JobsUiState.Idle)
    val state: StateFlow<JobsUiState> = _state

    private var searchJob: CoJob? = null

    fun load(query: String = "", location: String? = null, page: Int = 1) {
        _state.value = JobsUiState.Loading
        viewModelScope.launch {
            repo.searchJobs(query, location, page)
                .onSuccess { _state.value = JobsUiState.Success(it) }
                .onFailure { _state.value = JobsUiState.Error(it.message ?: "Erro") }
        }
    }

    fun debouncedSearch(query: String, location: String? = null, page: Int = 1, delayMs: Long = 450) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(delayMs)
            load(query, location, page)
        }
    }
}
