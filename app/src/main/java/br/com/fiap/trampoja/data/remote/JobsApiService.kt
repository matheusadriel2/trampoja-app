package br.com.fiap.trampoja.data.remote

import br.com.fiap.trampoja.di.AdzunaApi
import br.com.fiap.trampoja.di.adzunaApi

typealias AdzunaApiService = AdzunaApi

object Services {
    val adzuna = adzunaApi
}
