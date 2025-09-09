package br.com.fiap.trampoja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.fiap.trampoja.navigation.TrampojaApp
import br.com.fiap.trampoja.ui.theme.TrampojaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrampojaTheme {
                TrampojaApp(startOnHome = false)
            }
        }
    }
}