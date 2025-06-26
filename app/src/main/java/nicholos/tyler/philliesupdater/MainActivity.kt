package nicholos.tyler.philliesupdater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import nicholos.tyler.philliesupdater.ui.theme.PhilliesUpdaterTheme

val items = listOf(
    Screen.Home,
    Screen.Schedule,
    Screen.Team,
    Screen.Settings
)

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhilliesUpdaterTheme {
                val baseballViewModel : BaseballViewModel = viewModel()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val selectedTeam by baseballViewModel.selectedTeam.collectAsState()

                val currentScreen = items.find { screen ->
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (selectedTeam != null) {
                            val resolvedTitle = when {
                                currentDestination?.route?.startsWith(Screen.GameDetail.route.substringBefore("/{")) == true -> {
                                    "Game Details"
                                }
                                currentDestination?.route == Screen.Team.route -> {
                                    selectedTeam?.name ?: "Team"
                                }
                                else -> {
                                    currentScreen?.label ?: "Phillies Updater"
                                }
                            }

                            TopAppBar(
                                title = {
                                    Text(text = resolvedTitle)
                                },
                                navigationIcon = {
                                    if (currentDestination?.route?.startsWith("game_detail") == true) {
                                        IconButton(onClick = { navController.popBackStack() }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                },
                            )
                        }


                    },
                            bottomBar = {
                                if (currentDestination?.route?.startsWith("game_detail") != true) {
                                    NavigationBar {
                                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                                        val currentDestination = navBackStackEntry?.destination

                                        items.forEach { screen ->
                                            NavigationBarItem(
                                                icon = {
                                                    Icon(
                                                        screen.icon,
                                                        contentDescription = screen.label
                                                    )
                                                },
                                                label = { Text(screen.label) },
                                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                                onClick = {
                                                    navController.navigate(screen.route) {
                                                        // Pop up to the start destination of the graph to
                                                        // avoid building up a large stack of destinations
                                                        // on the back stack as users select items
                                                        popUpTo(navController.graph.findStartDestination().id) {
                                                            saveState = true
                                                        }
                                                        // Avoid multiple copies of the same destination when
                                                        // reselecting the same item
                                                        launchSingleTop = true
                                                        // Restore state when reselecting a previously selected item
                                                        restoreState = true
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Home.route) {
                            HomePage(modifier = Modifier.fillMaxSize(), baseballViewModel, navController)
                        }

                        composable(Screen.Schedule.route) {
                            SchedulePage(modifier = Modifier.fillMaxSize(), baseballViewModel, navController)
                        }
                        composable(Screen.Team.route) {
                            TeamPage(modifier = Modifier.fillMaxSize(), baseballViewModel, navController)
                        }
                        composable(Screen.Settings.route) {
                            SettingsPage()
                        }
                        composable(Screen.GameDetail.route) { navBackStackEntry ->
                            val gamePk = navBackStackEntry.arguments?.getString("gamePk")?.toLongOrNull()
                            val detailUiState by baseballViewModel.detailPageUiState.collectAsState()

                            if (gamePk != null && detailUiState.game.gamePk == gamePk) {
                                GameDetailPage().GameDetailScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    plays = detailUiState.plays,
                                    game = detailUiState.game
                                )
                            } else {
                                Text("Game not found!")
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ScoreCard(modifier: Modifier, game: Game?) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (game == null) {
                Text("Loading game...", style = MaterialTheme.typography.bodyMedium)
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(game.teams?.away?.team?.name.orEmpty(), style = MaterialTheme.typography.bodyLarge)
                        Text(game.teams?.away?.score?.toString().takeUnless { it.isNullOrEmpty() } ?: "0", style = MaterialTheme.typography.bodyLarge)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(game.teams?.home?.team?.name.orEmpty(), style = MaterialTheme.typography.bodyLarge)
                        Text(game.teams?.home?.score?.toString().takeUnless { it.isNullOrEmpty() } ?: "0", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhilliesUpdaterTheme {
        val game = Game(teams = Teams(away = Away(team = Team(name = "Atlanta Braves"), score = 1), home = Home(team = Team(name = "Philadelphia Phillies"), score = 5)))
        val dates = listOf(Date(games = listOf(game)))
        val selectedGame = game
        Column(modifier = Modifier) {
            //ScheduleCardList(dates, Modifier)
            ScoreCard(Modifier, game)
        }

    }
}