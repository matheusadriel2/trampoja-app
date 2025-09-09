package br.com.fiap.trampoja.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.fiap.trampoja.ui.theme.BlueLight

data class Tab(val key: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

private val tabs = listOf(
    Tab("home", "Início", Icons.Filled.Home),
    Tab("saved", "Favoritos", Icons.Filled.Bookmark),
    Tab("profile", "Perfil", Icons.Filled.Person),
)

@Composable
fun Navbar(
    selected: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = BlueLight,
        contentColor = Color.Black
    ) {
        tabs.forEach { t ->
            NavigationBarItem(
                selected = selected == t.key,
                onClick = { onItemSelected(t.key) },
                icon = { Icon(t.icon, contentDescription = t.label) },
                label = { Text(t.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Black.copy(alpha = 0.75f),
                    unselectedTextColor = Color.Black.copy(alpha = 0.75f),
                    indicatorColor = Color.Black.copy(alpha = 0.06f)
                )
            )
        }
    }
}
