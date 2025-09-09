package br.com.fiap.trampoja.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.trampoja.data.JobsRepository
import br.com.fiap.trampoja.di.adzunaApi

class JobsViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repo = JobsRepository(adzunaApi)
        return JobsViewModel(repo) as T
    }
}
