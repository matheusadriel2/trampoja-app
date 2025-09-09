package br.com.fiap.trampoja.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.trampoja.data.model.JobSummary
import br.com.fiap.trampoja.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(
    private val repo: FavoritesRepository = FavoritesRepository
) : ViewModel() {
    val favorites: StateFlow<List<JobSummary>> =
        repo.favorites.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun isFavorite(id: String) = repo.isFavorite(id)
    fun toggle(job: JobSummary) = repo.toggle(job)
    fun remove(id: String) = repo.remove(id)
}
