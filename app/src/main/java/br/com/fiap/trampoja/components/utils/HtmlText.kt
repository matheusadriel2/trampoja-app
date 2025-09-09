package br.com.fiap.trampoja.components.utils

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.material3.MaterialTheme
import androidx.core.text.HtmlCompat

@Composable
fun HtmlText(
    html: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    fontSizeSp: Float = 16f
) {
    val context = LocalContext.current
    val spanned = remember(html) { HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY) }

    AndroidView(
        modifier = modifier,
        factory = {
            TextView(context).apply {
                setTextColor(color.toArgb())
                textSize = fontSizeSp
                linksClickable = true
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
        update = { tv -> tv.text = spanned }
    )
}