package br.com.fiap.trampoja.data

import android.util.Log
import br.com.fiap.trampoja.di.AdzunaApi
import br.com.fiap.trampoja.di.AdzunaJob
import br.com.fiap.trampoja.di.Job
import br.com.fiap.trampoja.di.toUiJob

class JobsRepository(private val api: AdzunaApi) {

    suspend fun searchJobs(
        query: String,
        location: String? = null,
        page: Int = 1
    ): Result<List<Job>> = try {
        val q = if (query.isBlank()) "developer" else query
        val resp = api.search(
            page = page,
            what = q,
            where = location,
            rpp = 20,
            sortBy = "date"
        )
        if (resp.isSuccessful) {
            val list = resp.body()?.results?.map(AdzunaJob::toUiJob).orEmpty()
            Result.success(list)
        } else {
            val code = resp.code()
            val err = resp.errorBody()?.string()?.take(800)
            Log.e("Adzuna", "HTTP $code - $err")
            Result.failure(IllegalStateException("Erro $code"))
        }
    } catch (e: Exception) {
        Log.e("Adzuna", "Falha de requisição/conversão", e)
        Result.failure(e)
    }
}
