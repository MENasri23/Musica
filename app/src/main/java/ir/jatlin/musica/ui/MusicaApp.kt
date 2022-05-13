package ir.jatlin.musica.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object Player : Screen("player/{songUrl}") {
        fun createRoute(songUrl: String): String {
            return "player/{$songUrl}"
        }
    }
}

