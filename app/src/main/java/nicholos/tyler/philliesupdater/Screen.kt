package nicholos.tyler.philliesupdater

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Schedule : Screen("schedule", "Schedule", Icons.Filled.DateRange)
    object Team : Screen("team", "Team", Icons.Filled.People)
    object Settings : Screen("settings", "Settings", Icons.Filled.Settings)

    object GameDetail : Screen("game_detail/{gamePk}", "Game Detail", Icons.Filled.Info) { // Icon is optional here
        fun createRoute(gamePk: Long) = "game_detail/$gamePk"
    }
}

