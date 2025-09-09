package br.com.fiap.trampoja.data.remote.model

data class JoobleQuery(
    val keywords: String,
    val location: String = "Brazil",
    val page: Int = 1
)

data class JoobleResponse(
    val totalCount: Int? = null,
    val jobs: List<JoobleJob> = emptyList()
)

data class JoobleJob(
    val title: String? = null,
    val company: String? = null,
    val location: String? = null,
    val link: String? = null,
    val snippet: String? = null,
    val salary: String? = null,
    val type: String? = null,
    val updated: String? = null,
    val source: String? = null
)
