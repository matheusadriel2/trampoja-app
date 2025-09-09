package br.com.fiap.trampoja.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.trampoja.screens.*

@Composable
fun TrampojaApp(startOnHome: Boolean = false) {
    val nav = rememberNavController()
    val start = if (startOnHome) Routes.Home else Routes.Login

    Surface(color = Color.White) {
        NavHost(navController = nav, startDestination = start) {
            composable(Routes.Login) {
                LoginScreen(
                    onLogin = {
                        nav.navigate(Routes.Home) {
                            popUpTo(Routes.Login) { inclusive = true }
                        }
                    },
                    onGoToRegister = { nav.navigate(Routes.Register) }
                )
            }
            composable(Routes.Register) {
                RegisterScreen(
                    onRegistered = {
                        nav.navigate(Routes.Home) {
                            popUpTo(Routes.Login) { inclusive = true }
                        }
                    },
                    onBackToLogin = { nav.popBackStack() }
                )
            }

            composable(Routes.Home) {
                HomeWithNavbar(
                    selected = "home",
                    onSelect = { key ->
                        when (key) {
                            "home" -> Unit
                            "saved" -> nav.navigate(Routes.Saved) {
                                launchSingleTop = true; restoreState = true
                                popUpTo(Routes.Home) { saveState = true }
                            }
                            "profile" -> nav.navigate(Routes.Profile) {
                                launchSingleTop = true; restoreState = true
                                popUpTo(Routes.Home) { saveState = true }
                            }
                        }
                    }
                ) {
                    HomeFeedScreen()
                }
            }

            composable(Routes.Saved) {
                HomeWithNavbar(
                    selected = "saved",
                    onSelect = { key ->
                        when (key) {
                            "home" -> nav.navigate(Routes.Home) {
                                launchSingleTop = true; restoreState = true
                                popUpTo(Routes.Home) { saveState = true }
                            }
                            "saved" -> Unit
                            "profile" -> nav.navigate(Routes.Profile) {
                                launchSingleTop = true; restoreState = true
                                popUpTo(Routes.Home) { saveState = true }
                            }
                        }
                    }
                ) {
                    SavedScreen()
                }
            }

            composable(Routes.Profile) {
                HomeWithNavbar(
                    selected = "profile",
                    onSelect = { key ->
                        when (key) {
                            "home" -> nav.navigate(Routes.Home) {
                                launchSingleTop = true; restoreState = true
                                popUpTo(Routes.Home) { saveState = true }
                            }
                            "saved" -> nav.navigate(Routes.Saved) {
                                launchSingleTop = true; restoreState = true
                                popUpTo(Routes.Home) { saveState = true }
                            }
                            "profile" -> Unit
                        }
                    }
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}
