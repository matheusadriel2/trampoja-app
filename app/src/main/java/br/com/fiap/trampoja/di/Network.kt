package br.com.fiap.trampoja.di

import androidx.annotation.Keep
import androidx.compose.ui.graphics.Color
import br.com.fiap.trampoja.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val ADZUNA_BASE = "https://api.adzuna.com/v1/api/jobs/"

/** Interceptor que injeta as credenciais na query e headers padrão */
private val authQueryInterceptor = Interceptor { chain ->
    val original = chain.request()
    val url = original.url.newBuilder()
        .addQueryParameter("app_id", BuildConfig.ADZUNA_APP_ID)
        .addQueryParameter("app_key", BuildConfig.ADZUNA_APP_KEY)
        .build()

    val req = original.newBuilder()
        .url(url)
        .header("Accept", "application/json")
        .header("User-Agent", "TrampoJA/1.0 (Android)")
        .build()

    chain.proceed(req)
}

/** Log só em debug; no release fica NONE */
private val logging by lazy {
    HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) BODY else HttpLoggingInterceptor.Level.NONE
    }
}

private val moshi: Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .retryOnConnectionFailure(true)
    .addInterceptor(authQueryInterceptor)
    .addInterceptor(logging) // deixe por último para logar a request final
    .build()

val adzunaRetrofit: Retrofit = Retrofit.Builder()
    .baseUrl(ADZUNA_BASE)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface AdzunaApi {
    @GET("{country}/search/{page}")
    suspend fun search(
        @Path("country") country: String = BuildConfig.ADZUNA_COUNTRY,
        @Path("page") page: Int = 1,
        @Query("what") what: String,
        @Query("where") where: String? = null,
        @Query("results_per_page") rpp: Int = 20,
        @Query("max_days_old") maxDaysOld: Int? = null,
        @Query("sort_by") sortBy: String? = "date"
    ): Response<AdzunaSearchResponse>
}

/** Models — marcadas com @Keep para o R8 não quebrar a (de)serialização do Moshi */
@Keep
data class AdzunaSearchResponse(
    val count: Int? = null,
    val results: List<AdzunaJob> = emptyList()
)

@Keep
data class AdzunaJob(
    val title: String? = null,
    val description: String? = null,
    val created: String? = null,
    @Json(name = "redirect_url") val redirectUrl: String? = null,
    @Json(name = "salary_min") val salaryMin: Double? = null,
    @Json(name = "salary_max") val salaryMax: Double? = null,
    val company: Company? = null,
    val location: Location? = null,
    val category: Category? = null
)

@Keep data class Company(@Json(name = "display_name") val displayName: String? = null)
@Keep data class Location(@Json(name = "display_name") val displayName: String? = null)
@Keep data class Category(val label: String? = null)

data class Job(
    val title: String,
    val company: String,
    val rating: Double,
    val location: String,
    val description: String,
    val responsibilities: String,
    val requirements: String,
    val benefits: String,
    val tag: String,
    val tagColor: Color
)

fun AdzunaJob.toUiJob(): Job {
    val title = this.title ?: "Vaga sem título"
    val company = this.company?.displayName ?: "Empresa não informada"
    val loc = this.location?.displayName ?: "Local não informado"
    val desc = this.description ?: "Ver descrição completa no link."
    val (tag, color) = inferTagAndColor("$desc $title $company")
    return Job(
        title = title,
        company = company,
        rating = 4.6,
        location = loc,
        description = desc,
        responsibilities = "Consulte a descrição da vaga.",
        requirements = "Consulte a descrição da vaga.",
        benefits = "Consulte a descrição da vaga.",
        tag = tag,
        tagColor = color
    )
}

fun inferTagAndColor(text: String): Pair<String, Color> {
    val t = text.lowercase()
    return when {
        listOf("pcd", "deficiência", "acessibilidade", "disability").any { it in t } ->
            "Afirmativa para PcD" to Color(0xFF6A5ACD)
        listOf("refugiado", "refugiados", "refugee").any { it in t } ->
            "Afirmativa para refugiados" to Color(0xFF2E8B57)
        listOf("lgbt", "lgbtq", "lgbtqia").any { it in t } ->
            "Afirmativa LGBTQIA+" to Color(0xFF8A2BE2)
        listOf("mulher", "mulheres", "women", "stem").any { it in t } ->
            "Afirmativa para mulheres" to Color(0xFF00A6F4)
        listOf("negro", "negra", "black", "afro").any { it in t } ->
            "Afirmativa para pessoas negras" to Color(0xFF000000)
        else -> "Diversidade & Inclusão" to Color(0xFF00A6F4)
    }
}

val adzunaApi: AdzunaApi = adzunaRetrofit.create(AdzunaApi::class.java)
