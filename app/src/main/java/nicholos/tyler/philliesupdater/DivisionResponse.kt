package nicholos.tyler.philliesupdater

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class StandingsResponse(
    val copyright: String? = null,
    val records: List<StandingsRecord>? = null
)

@Serializable
data class StandingsRecord(
    val standingsType: String? = null,
    val league: League? = null,
    val division: Division? = null,
    val sport: Sport? = null,
    val lastUpdated: String? = null,
    val teamRecords: List<TeamRecord>? = null
)

@Serializable
data class TeamRecord(
    val team: Team? = null,
    val season: String? = null,
    val streak: Streak? = null,
    val divisionRank: String? = null,
    val leagueRank: String? = null,
    val wildCardRank: String? = null,
    val sportRank: String? = null,
    val gamesPlayed: Int? = null,
    val gamesBack: String? = null,
    val wildCardGamesBack: String? = null,
    val leagueGamesBack: String? = null,
    val springLeagueGamesBack: String? = null,
    val sportGamesBack: String? = null,
    val divisionGamesBack: String? = null,
    val conferenceGamesBack: String? = null,
    val leagueRecord: LeagueRecord? = null,
    val lastUpdated: String? = null,
    val records: Records? = null,
    val runsAllowed: Int? = null,
    val runsScored: Int? = null,
    val divisionChamp: Boolean? = null,
    val divisionLeader: Boolean? = null,
    val wildCardLeader: Boolean? = null,
    val hasWildcard: Boolean? = null,
    val clinched: Boolean? = null,
    val eliminationNumber: String? = null,
    val eliminationNumberSport: String? = null,
    val eliminationNumberLeague: String? = null,
    val eliminationNumberDivision: String? = null,
    val eliminationNumberConference: String? = null,
    val wildCardEliminationNumber: String? = null,
    val magicNumber: String? = null,
    val wins: Int? = null,
    val losses: Int? = null,
    val runDifferential: Int? = null,
    val winningPercentage: String? = null
)

@Serializable
data class Streak(
    val streakType: String? = null,
    val streakNumber: Int? = null,
    val streakCode: String? = null
)

@Serializable
data class SplitRecord(
    val wins: Int? = null,
    val losses: Int? = null,
    val type: String? = null,
    val pct: String? = null
)

@Serializable
data class DivisionRecord(
    val wins: Int? = null,
    val losses: Int? = null,
    val pct: String? = null,
    val division: DivisionName? = null
)

@Serializable
data class OverallRecord(
    val wins: Int? = null,
    val losses: Int? = null,
    val type: String? = null,
    val pct: String? = null
)

@Serializable
data class LeagueWinRecord(
    val wins: Int? = null,
    val losses: Int? = null,
    val pct: String? = null,
    val league: LeagueName? = null
)

@Serializable
data class ExpectedRecord(
    val wins: Int? = null,
    val losses: Int? = null,
    val type: String? = null,
    val pct: String? = null
)

@Serializable
data class Records(
    val splitRecords: List<SplitRecord>? = null,
    val divisionRecords: List<DivisionRecord>? = null,
    val overallRecords: List<OverallRecord>? = null,
    val leagueRecords: List<LeagueWinRecord>? = null,
    val expectedRecords: List<ExpectedRecord>? = null
)

@Serializable
data class DivisionName(val id: Int? = null, val name: String? = null, val link: String? = null)

@Serializable
data class LeagueName(val id: Int? = null, val name: String? = null, val link: String? = null)
