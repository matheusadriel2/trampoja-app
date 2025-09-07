package br.com.fiap.trampoja.components.utils

fun getTagEmoji(tag: String): String {
    return when (tag) {
        "Afirmativa para pessoas com deficiência" -> "♿"
        "Afirmativa para pessoas negras" -> "✊🏾"
        "Afirmativa para mulheres" -> "👩"
        "Afirmativa para refugiados" -> "🌍"
        "Afirmativa para pessoas LGBTQIA+" -> "🏳️‍🌈"
        else -> "✨"
    }
}

fun getLocationEmoji(location: String): String {
    return when {
        location.contains("Remoto", ignoreCase = true) -> "🏠"
        location.contains("Híbrido", ignoreCase = true) -> "🌐"
        else -> "📍"
    }
}
