package br.com.fiap.trampoja.data.model

data class JobSummary(
    val id: String,
    val title: String,
    val company: String,
    val location: String,
    val salary: String? = null,
    val remote: Boolean = false
)
