package br.com.fiap.trampoja.data.repository

import br.com.fiap.trampoja.data.model.JobSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object FavoritesRepository {
    private val _favorites = MutableStateFlow<List<JobSummary>>(emptyList())
    val favorites: StateFlow<List<JobSummary>> = _favorites.asStateFlow()

    fun isFavorite(id: String) = _favorites.value.any { it.id == id }
    fun add(job: JobSummary) { if (!isFavorite(job.id)) _favorites.value = _favorites.value + job }
    fun remove(id: String) { _favorites.value = _favorites.value.filterNot { it.id == id } }
    fun toggle(job: JobSummary) { if (isFavorite(job.id)) remove(job.id) else add(job) }
}
