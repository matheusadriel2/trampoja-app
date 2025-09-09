package br.com.fiap.trampoja.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.fiap.trampoja.components.Navbar

@Composable
fun HomeWithNavbar(
    selected: String = "home",
    onSelect: (String) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            Navbar(
                selected = selected,
                onItemSelected = onSelect
            )
        }
    ) { inner ->
        Surface(color = Color.White) {
            Box(Modifier.padding(inner)) {
                content()
            }
        }
    }
}
