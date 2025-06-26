package nicholos.tyler.philliesupdater

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import nicholos.tyler.philliesupdater.ui.theme.PhilliesUpdaterTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(modifier: Modifier, baseballVM: BaseballViewModel, navController: NavController) {

    val selectedGame by baseballVM.selectedGame.collectAsState()
    val selectedGameDetails by baseballVM.baseballGameData.collectAsState()
    val homePageUiState by baseballVM.homePageUiState.collectAsState()
    val isRefreshing by baseballVM.isRefreshing.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val pullToRefreshState = rememberPullToRefreshState()

    if (selectedGame == null) {
        LaunchedEffect(Unit) {
            baseballVM.refreshHomePage()
        }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                baseballVM.refreshHomePage()
            }
        },
        state = pullToRefreshState,
        modifier = Modifier.fillMaxSize()
    ) {

        if (homePageUiState != null) {
            val liveGameData = homePageUiState.liveGameData
            var flatGames = emptyList<Game?>()
            if (homePageUiState.tenDaySchedule != null && homePageUiState.tenDaySchedule.isNotEmpty()) {
                flatGames = homePageUiState.tenDaySchedule?.flatMap { date ->
                    date.games!!
                } ?: emptyList()
                if (liveGameData != null) {

                }
            }


            Column(modifier = modifier.verticalScroll(rememberScrollState()), ) {
                LiveGameScoreCard(
                    modifier = Modifier,
                    isTopInning = liveGameData?.isTopInning ?: false,
                    inning = liveGameData?.inning ?: 0,
                    inningSuffix = liveGameData?.inningSuffix,
                    awayTeamName = liveGameData?.awayTeamName ?: "",
                    homeTeamName = liveGameData?.homeTeamName ?: "",
                    awayTeamScore = liveGameData?.awayTeamScore ?: 0,
                    homeTeamScore = liveGameData?.homeTeamScore ?: 0,
                    outs = liveGameData?.outs ?: 0,
                    leftOnBase = liveGameData?.runnersOnBase ?: "0",
                    isGameOver = liveGameData?.isGameOver ?: false,
                    status = liveGameData?.status ?: "No Game Today"
                )

                Text(
                    text = "10-Day Stretch", // Or "Scheduled Games"
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp), // Overall padding for the row
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between items
                ) {
                    items(flatGames) { game ->
                        if (game != null) {
                            var status = ""
                            if (game.status?.detailedState == "Final") {
                                status = "Final"
                            } else {
                                status = "Scheduled"
                            }
                            val homeTeamName = game.teams?.home?.team?.name ?: "N/A"
                            val awayTeamName = game.teams?.away?.team?.name ?: "N/A"
                            val homeTeamScore = game.teams?.home?.score ?: 0
                            val awayTeamScore = game.teams?.away?.score ?: 0
                            val yourTeamName = "Philadelphia Phillies"
                            val prefix = if (homeTeamName == yourTeamName) "vs" else "@"

                            GameScheduleCard(
                                modifier = Modifier.width(300.dp).height(100.dp),
                                date = DateHelper.formatIsoDateToDisplayString(game.gameDate!!).toString(), //
                                time = DateHelper.formatIsoDateToTimeString(game.gameDate.toString()),
                                awayTeamName = awayTeamName,
                                opponentLogoUrl = null,
                                homeTeamName = homeTeamName,
                                homeTeamScore = homeTeamScore,
                                awayTeamScore = awayTeamScore,
                                status = status,
                                venue = game.venue?.name ?: "N/A",
                                prefix = prefix,
                                navController = navController,
                                baseballVM = baseballVM,
                                game = game
                            )




                        }
                    }
                }
                Text(
                    text = "Division Standings",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )

                if(homePageUiState.division.isNotEmpty()) {
                    val divisionRecords = homePageUiState.division.sortedBy { record ->
                        record.divisionRank
                    }
                    for(record in divisionRecords) {
                        TeamStandingsSnippet(
                            modifier = Modifier.fillMaxWidth(),
                            standingInfo = TeamStandingInfo(
                                BaseballHelper.abbreviateTeamName(record.team?.name.toString()),
                                teamFullName = record.team?.name.toString(),
                                divisionRank = record.divisionRank?.toInt(),
                                gamesBehind = record.divisionGamesBack,
                                wins = record.wins,
                                losses = record.losses,
                                divisionName = "National League East"
                            )
                        )
                    }

                }

            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun HomepagePreview() {

}

data class TeamStandingInfo(
    val teamAbbreviation: String, // e.g., "PHI"
    val teamFullName: String,     // e.g., "Philadelphia Phillies"
    val divisionRank: Int?,        // e.g., 1 for 1st place
    val gamesBehind: String?,      // e.g., "0.0", "1.5", "-" (if leading or tied for first)
    val wins: Int?,
    val losses: Int?,
    val divisionName: String      // e.g., "NL East"
)

// Example of how you might get this for the homepage (simplified)
data class DivisionStandings(
    val divisionName: String,
    val teams: List<TeamStandingInfo>
)

enum class GameStatus {
    SCHEDULED,
    IN_PROGRESS,
    FINAL
}

@Composable
fun LiveGameScoreCard(
    modifier: Modifier = Modifier,
    isTopInning: Boolean,
    inning: Int,
    inningSuffix: String?,
    awayTeamName: String,
    homeTeamName: String,
    awayTeamScore: Long,
    homeTeamScore: Long,
    outs: Int,
    leftOnBase: String,
    isGameOver: Boolean,
    status: String?
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            if (isGameOver == false) {
                // Top Section: Inning
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (status == "In Progress") {
                        Icon(
                            imageVector = if (isTopInning) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                            contentDescription = if (isTopInning) "Top of Inning" else "Bottom of Inning",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    InningStatusText(
                        status = status,
                        isTopInning = isTopInning,
                        inning = inning,
                        inningSuffix = inningSuffix
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Final",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Middle Section: Teams and Scores
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Away Team (Left)
                TeamScoreColumn(
                    teamName = awayTeamName,
                    score = awayTeamScore,
                    isBold = !isTopInning // Bold if their half of inning or game over
                )

                Text(
                    text = "vs",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                // Home Team (Right)
                TeamScoreColumn(
                    teamName = homeTeamName,
                    score = homeTeamScore,
                    isBold = isTopInning
                )
            }

            if (status == "In Progress") {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    outs?.let {
                        Text(
                            text = "$it Out${if (it != 1) "s" else ""}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    leftOnBase?.let {
                        if (outs != null) { // Add a divider if both are present
                            Text("•", modifier = Modifier.padding(horizontal = 8.dp))
                        }
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun InningStatusText(
    status: String?, // e.g., "In Progress", "Final", "Scheduled", "Preview"
    isTopInning: Boolean,
    inning: Int,
    inningSuffix: String?, // "st", "nd", "rd", "th" -
) {
    val textToShow = when (status) {
        "In Progress" -> "${if (isTopInning) "Top" else "Bot"} ${inning}${inningSuffix ?: ""}"
        "Final" -> "Final"
        "No Game Today" -> "No Game Today"
        else -> "Today"
    }

    Text(
        text = textToShow,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TeamScoreColumn(
    teamName: String,
    score: Long,
    isBold: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = teamName,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = if (isBold) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = score.toString(),
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 30.sp), // Larger score
            fontWeight = FontWeight.ExtraBold,
            color = if (isBold) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}


@Composable
fun GameScheduleCard(
    modifier: Modifier = Modifier,
    date: String, // e.g., "MON, JUL 29"
    time: String?, // e.g., "7:05 PM ET", null if game is final
    awayTeamName: String,
    opponentLogoUrl: String?, // Optional
    homeTeamName: String, // The user's selected team
    homeTeamScore: Long,
    awayTeamScore: Long?,
    status: String,
    venue: String, // e.g. "Citizens Bank Park"
    prefix: String,
    navController: NavController,
    baseballVM: BaseballViewModel,
    game: Game
) {
    val isPastGame = status == "Final"
    val isFutureGame = status == "Scheduled"
    val yourTeamName = "Philadelphia Phillies"
    val opponentScore = if (homeTeamName == yourTeamName) awayTeamScore else homeTeamScore
    val yourTeamScore = if (homeTeamName == yourTeamName) homeTeamScore else awayTeamScore
    var win: Boolean = false

    if (homeTeamScore != null && awayTeamScore != null) {
        if (yourTeamScore!! > opponentScore!!) {
            win = true
        } else {
            win = false
        }
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                baseballVM.refreshDetailPage(game)
                navController.navigate(Screen.GameDetail.createRoute(gamePk = game.gamePk!!))
                       },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Date & Time Column (Left)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.25f) // Give it some defined space
            ) {
                Text(
                    text = date.toString(), // e.g., "MON"
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                if (isFutureGame && time != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = time,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .height(50.dp) // Adjust height as needed
                    .width(1.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            )

            // Game Info Column (Center)
            Column(
                modifier = Modifier
                    .weight(0.5f) // Main content area
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                // Team Logo (Optional)
                /*
                AsyncImage(
                    model = game.opponentLogoUrl,
                    contentDescription = "${game.opponentName} Logo",
                    modifier = Modifier
                        .size(24.dp) // Adjust size as needed
                        .padding(bottom = 4.dp),
                    // placeholder = painterResource(id = R.drawable.default_logo), // Optional
                    // error = painterResource(id = R.drawable.default_logo) // Optional
                )
                */
                Text(
                    text = BaseballHelper.abbreviateMatchup(homeTeamName, awayTeamName, yourTeamName),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = venue,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Score/Outcome Column (Right)
            if (isPastGame && yourTeamScore != null && opponentScore != null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.25f) // Give it defined space
                ) {
                    Text(
                        text = "${yourTeamScore} - ${opponentScore}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    if (win != null)
                    {
                        Text(
                            text = if (win == true) {
                                "Win"
                            } else if (win == false) {
                                "Loss"
                            } else {
                                "N/A"
                            },
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = when (win) {
                                true -> Color(0xFF2E7D32)
                                false -> MaterialTheme.colorScheme.error
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            } else {
                // For future games, this space could be empty or show something else,
                // or the weights could be adjusted. For now, it will be empty if not a past game.
                Spacer(Modifier.weight(0.25f))
            }
        }
    }
}


@Composable
fun TeamStandingsSnippet(
    modifier: Modifier = Modifier,
    standingInfo: TeamStandingInfo,
    onViewFullStandingsClicked: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onViewFullStandingsClicked() }, // Make the whole card clickable
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left Side: Team Info & Rank
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Leaderboard,
                        contentDescription = "Standings",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = standingInfo.divisionName, // "NL East Standings"
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${standingInfo.teamFullName} (${standingInfo.wins}-${standingInfo.losses})",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Right Side: Rank and Games Behind
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = formatRank(standingInfo.divisionRank),
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = if (standingInfo.divisionRank == 1 && (standingInfo.gamesBehind == "-" || standingInfo.gamesBehind == "0.0")) {
                        "Leading"
                    } else if (standingInfo.gamesBehind == "0.0" && standingInfo.divisionRank!! > 1 && standingInfo.divisionRank != null) {
                        "Tied" // Or handle tie for first specifically
                    }
                    else {
                        "${standingInfo.gamesBehind} GB"
                    },
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

fun formatRank(rank: Int?): String {
    return when (rank) {
        1 -> "1st"
        2 -> "2nd"
        3 -> "3rd"
        else -> "${rank}th"
    }
}

@Preview(showBackground = true, name = "Live Game - Top 3rd")
@Composable
fun HomePagePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            LiveGameScoreCardPreview_TopInning()
            //PastGameLossCardPreview()
            //FutureGameHomeCardPreview()
            StandingsSnippetFirstPlacePreview()
        }
    )
}

@Preview(showBackground = true, name = "Live Game - Top 3rd")
@Composable
fun LiveGameScoreCardPreview_TopInning() {
    PhilliesUpdaterTheme {
        LiveGameScoreCard(
                modifier = Modifier,
                homeTeamName = "PHI",
                awayTeamName = "NYM",
                homeTeamScore = 2,
                awayTeamScore = 1,
                inning = 3,
                inningSuffix = "rd",
                isTopInning = true,
                outs = 1,
                leftOnBase = "Runner on 2nd",
                isGameOver = false,
                status =  "In Progress"
            )

    }
}


//@Preview(showBackground = true, name = "Past Game - Win")
//@Composable
//fun PastGameWinCardPreview() {
//    PhilliesUpdaterTheme {
//        GameScheduleCard(
//            game = ScheduledGameData(
//                gameId = "1",
//                date = "FRI, JUL 26",
//                time = null,
//                opponentName = "Braves",
//                opponentLogoUrl = null,
//                homeTeamName = "Phillies",
//                yourTeamName = "Phillies",
//                yourTeamScore = 5,
//                opponentScore = 2,
//                status = GameStatus.FINAL,
//                venue = "Citizens Bank Park"
//            )
//        )
//    }
//}
//
//@Preview(showBackground = true, name = "Past Game - Loss")
//@Composable
//fun PastGameLossCardPreview() {
//    PhilliesUpdaterTheme {
//        GameScheduleCard(
//            game = ScheduledGameData(
//                gameId = "2",
//                date = "SAT, JUL 27",
//                time = null,
//                opponentName = "Mets",
//                opponentLogoUrl = null,
//                homeTeamName = "Mets",
//                yourTeamName = "Phillies",
//                yourTeamScore = 1,
//                opponentScore = 4,
//                status = GameStatus.FINAL,
//                venue = "Citi Field"
//            )
//        )
//    }
//}
//
//@Preview(showBackground = true, name = "Future Game - Home")
//@Composable
//fun FutureGameHomeCardPreview() {
//    PhilliesUpdaterTheme {
//        GameScheduleCard(
//            game = ScheduledGameData(
//                gameId = "3",
//                date = "TUE, JUL 30",
//                time = "7:05 PM ET",
//                opponentName = "Marlins",
//                opponentLogoUrl = null,
//                homeTeamName = "Phillies",
//                yourTeamName = "Phillies",
//                yourTeamScore = null,
//                opponentScore = null,
//                status = GameStatus.SCHEDULED,
//                venue = "Citizens Bank Park"
//            )
//        )
//    }
//}
//
//@Preview(showBackground = true, name = "Future Game - Away")
//@Composable
//fun FutureGameAwayCardPreview() {
//    PhilliesUpdaterTheme {
//        GameScheduleCard(
//            game = ScheduledGameData(
//                gameId = "4",
//                date = "WED, JUL 31",
//                time = "6:40 PM ET",
//                opponentName = "Nationals",
//                opponentLogoUrl = null,
//                homeTeamName = "Nationals",
//                yourTeamName = "Phillies",
//                yourTeamScore = null,
//                opponentScore = null,
//                status = GameStatus.SCHEDULED,
//                venue = "Nationals Park"
//            )
//        )
//    }
//}

@Preview(showBackground = true, name = "Standings Snippet - 1st Place")
@Composable
fun StandingsSnippetFirstPlacePreview() {
    PhilliesUpdaterTheme {
        TeamStandingsSnippet(
            standingInfo = TeamStandingInfo(
                teamAbbreviation = "PHI",
                teamFullName = "Philadelphia Phillies",
                divisionRank = 1,
                gamesBehind = "-", // Or "0.0"
                wins = 70,
                losses = 45,
                divisionName = "NL East"
            )
        )
    }
}

@Preview(showBackground = true, name = "Standings Snippet - 2nd Place")
@Composable
fun StandingsSnippetSecondPlacePreview() {
    PhilliesUpdaterTheme {
        TeamStandingsSnippet(
            standingInfo = TeamStandingInfo(
                teamAbbreviation = "ATL",
                teamFullName = "Atlanta Braves",
                divisionRank = 2,
                gamesBehind = "3.5",
                wins = 66,
                losses = 48,
                divisionName = "NL East"
            )
        )
    }
}

@Preview(showBackground = true, name = "Standings Snippet - Further Back")
@Composable
fun StandingsSnippetFurtherBackPreview() {
    PhilliesUpdaterTheme {
        TeamStandingsSnippet(
            standingInfo = TeamStandingInfo(
                teamAbbreviation = "NYM",
                teamFullName = "New York Mets",
                divisionRank = 4,
                gamesBehind = "10.0",
                wins = 60,
                losses = 55,
                divisionName = "NL East"
            )
        )
    }
}