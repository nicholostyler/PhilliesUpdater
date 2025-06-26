package nicholos.tyler.philliesupdater

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

data class ScheduleGameData(
    val date: String,
    val time: String,
    val awayTeamName: String,
    val opponentLogoUrl: String?,
    val homeTeamName: String,
    val homeTeamScore: Long,
    val awayTeamScore: Long,
    val status: String,
    val venue: String ,
    val prefix: String,
    val game: Game
)

@Composable
fun SchedulePage(modifier: Modifier, baseballVM: BaseballViewModel, navController: NavHostController) {
    val scheduleUIPage by baseballVM.scheduleUiState.collectAsState()
    LaunchedEffect(Unit) {
        baseballVM.getSchedulePage()
    }

    if (scheduleUIPage.gameCardList.isNotEmpty()) {
        GameScheduleList(games = scheduleUIPage.gameCardList, modifier = modifier, baseballVM = baseballVM, navController = navController)
    }


}

@Composable
fun GameScheduleList(games: List<ScheduleGameData>, modifier: Modifier = Modifier, baseballVM: BaseballViewModel, navController: NavController) {
    LazyColumn(modifier = modifier) {
        items(games) { game ->
            GameScheduleCard(
                date = game.date,
                time = game.time,
                awayTeamName = game.awayTeamName,
                opponentLogoUrl = "",
                homeTeamName = game.homeTeamName,
                homeTeamScore = game.homeTeamScore,
                awayTeamScore = game.awayTeamScore,
                status = game.status,
                venue = game.venue,
                prefix = game.prefix,
                navController = navController,
                baseballVM = baseballVM,
                game = game.game
            )
        }
    }
}

fun getAllGames(scheduleData: GameRoot?): List<Game> {
    return scheduleData
        ?.dates
        ?.filterNotNull()
        ?.flatMap { it.games.orEmpty().filterNotNull()  }
        ?: emptyList()
}

