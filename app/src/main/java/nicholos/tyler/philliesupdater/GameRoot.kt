package nicholos.tyler.philliesupdater

import kotlinx.serialization.Serializable

@Serializable
data class GameRoot(
    val copyright: String? = null,
    val totalItems: Long? = null,
    val totalEvents: Long? = null,
    val totalGames: Long? = null,
    val totalGamesInProgress: Long? = null,
    val dates: List<Date>? = null, // List itself can be null, and items within can be null
)

@Serializable
data class Date(
    val date: String? = null,
    val totalItems: Long? = null,
    val totalEvents: Long? = null,
    val totalGames: Long? = null,
    val totalGamesInProgress: Long? = null,
    val games: List<Game?>? = null,
    val events: List<String>? = null,
)

@Serializable
data class Game(
    val gamePk: Long? = null,
    val gameGuid: String? = null,
    val link: String? = null,
    val gameType: String? = null,
    val season: String? = null,
    val gameDate: String? = null,
    val officialDate: String? = null,
    val status: Status? = null,
    val teams: Teams? = null,
    val venue: Venue? = null,
    val content: Content? = null,
    val gameNumber: Long? = null,
    val publicFacing: Boolean? = null,
    val doubleHeader: String? = null,
    val gamedayType: String? = null,
    val tiebreaker: String? = null,
    val calendarEventId: String? = null,
    val seasonDisplay: String? = null,
    val dayNight: String? = null,
    val scheduledInnings: Long? = null,
    val reverseHomeAwayStatus: Boolean? = null,
    val inningBreakLength: Long? = null,
    val gamesInSeries: Long? = null,
    val seriesGameNumber: Long? = null,
    val seriesDescription: String? = null,
    val recordSource: String? = null,
    val ifNecessary: String? = null,
    val ifNecessaryDescription: String? = null,
)

@Serializable
data class Status(
    val abstractGameState: String? = null,
    val codedGameState: String? = null,
    val detailedState: String? = null,
    val statusCode: String? = null,
    val startTimeTbd: Boolean? = null, // Was already nullable, kept it
    val abstractGameCode: String? = null,
)

@Serializable
data class Teams(
    val away: Away? = null,
    val home: Home? = null,
)

@Serializable
data class Away(
    val leagueRecord: LeagueRecord? = null,
    val score: Long? = null,
    val team: Team? = null,
    val splitSquad: Boolean? = null,
    val seriesNumber: Long? = null,
)

@Serializable
data class LeagueRecord(
    val wins: Long? = null,
    val losses: Long? = null,
    val ties: Int? = null,         // Was already nullable, kept it
    val pct: String? = null,       // Was already nullable, kept it
)

@Serializable
data class Team(
    val id: Long? = null,
    val name: String? = null,
    val link: String? = null,
)



@Serializable
data class Home(
    val leagueRecord: LeagueRecord? = null,
    val score: Long? = null,
    val team: Team? = null,
    val splitSquad: Boolean? = null,
    val seriesNumber: Long? = null,
)

@Serializable
data class Venue(
    val id: Long? = null,
    val name: String? = null,
    val link: String? = null,
)

@Serializable
data class Content(
    val link: String? = null,
)

fun List<Game>.toScheduleGameDataList(yourTeamName: String): List<ScheduleGameData> {
    return this.mapNotNull { game ->
        val gameDateTime = game.gameDate ?: return@mapNotNull null
        val dateTimeParts = gameDateTime.split("T")
        val date = dateTimeParts.getOrNull(0) ?: return@mapNotNull null
        val time = dateTimeParts.getOrNull(1)?.removeSuffix("Z") ?: "TBD"

        val homeTeam = game.teams?.home?.team?.name ?: return@mapNotNull null
        val awayTeam = game.teams.away?.team?.name ?: return@mapNotNull null

        val homeScore = game.teams.home.score ?: 0
        val awayScore = game.teams.away.score ?: 0

        val status = game.status?.detailedState ?: "Unknown"
        val venueName = game.venue?.name ?: "Unknown Venue"

        ScheduleGameData(
            date = date,
            time = time,
            awayTeamName = awayTeam,
            opponentLogoUrl = "",
            homeTeamName = homeTeam,
            homeTeamScore = homeScore,
            awayTeamScore = awayScore,
            status = status,
            venue = venueName,
            prefix = if (homeTeam == yourTeamName) "vs" else "@",
            game = game
        )
    }
}
