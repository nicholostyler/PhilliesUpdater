package nicholos.tyler.philliesupdater

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement // Assuming this was an intended import based on usage

fun GameDetailResponse.toLiveGameData(selectedGame: Game?): LiveGameData? {
    val currentPlay = this?.liveData?.plays?.currentPlay
    val linescore = this.liveData?.linescore

    return LiveGameData(
        homeTeamName = selectedGame?.teams?.home?.team?.name ?: "Unknown",
        awayTeamName = selectedGame?.teams?.away?.team?.name ?: "Unknown",
        homeTeamScore = selectedGame?.teams?.home?.score ?: 0,
        awayTeamScore = selectedGame?.teams?.away?.score ?: 0,
        inning = currentPlay?.about?.inning ?: 0,
        inningSuffix = BaseballHelper.getInningSuffix(currentPlay?.about?.inning) ?: "0",
        isTopInning = currentPlay?.about?.isTopInning ?: false,
        outs = linescore?.outs ?: 0,
        runnersOnBase = BaseballHelper.countRunnersOnBase(this).toString() ?: "0",
        isGameOver = selectedGame?.status?.detailedState == ("Final" ?: false),
        status = selectedGame?.status?.detailedState ?: "Unknown"
    )
}

@Serializable
data class GameDetailResponse(
    val copyright: String? = null,
    val gamePk: Int? = null,
    val link: String? = null,
    val metaData: MetaData? = null,
    val gameData: GameData? = null,
    val liveData: LiveData? = null, // Added liveData
    val players: Map<String, Player>? = null
)

@Serializable
data class MetaData(
    val wait: Int? = null,
    val timeStamp: String? = null,
    val gameEvents: List<String>? = null,
    val logicalEvents: List<String>? = null
)

@Serializable
data class GameData(
    val game: GameDetail? = null,
    val datetime: Datetime? = null,
    val status: Status? = null, // Assuming Status data class exists or was intended
    val teams: TeamsDetail? = null
)

@Serializable
data class GameDetail(
    val pk: Int? = null,
    val type: String? = null,
    val doubleHeader: String? = null,
    val id: String? = null,
    val gamedayType: String? = null,
    val tiebreaker: String? = null,
    val season: String? = null,
    val calendarEventID: String? = null,
    val seasonDisplay: String? = null,
    val dayNight: String? = null,
    val scheduledInnings: Int? = null,
    val reverseHomeRoad: Boolean? = null,
    val league: String? = null // Assuming this was meant to be a simple String
)

@Serializable
data class Datetime(
    val dateTime: String? = null,
    val originalDate: String? = null,
    val officialDate: String? = null,
    val dayNight: String? = null,
    val time: String? = null,
    val ampm: String? = null
)

@Serializable
data class TeamsDetail(
    val away: TeamDetails? = null,
    val home: TeamDetails? = null
)

@Serializable
data class TeamDetails(
    val springLeague: League? = null,
    val allStarStatus: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val link: String? = null,
    val season: Int? = null,
    val venue: Venue? = null, // Assuming Venue data class exists
    val springVenue: SpringVenue? = null,
    val teamCode: String? = null,
    val fileCode: String? = null,
    val abbreviation: String? = null,
    val teamName: String? = null,
    val locationName: String? = null,
    val firstYearOfPlay: String? = null,
    val league: League? = null,
    val division: Division? = null,
    val sport: Sport? = null,
    val shortName: String? = null,
    val record: Record? = null,
    val franchiseName: String? = null,
    val clubName: String? = null,
    val active: Boolean? = null
)

@Serializable
data class SpringVenue(
    val id: Int? = null,
    val link: String? = null
)

@Serializable
data class League(
    val id: Int? = null,
    val name: String? = null,
    val link: String? = null,
    val abbreviation: String? = null
)

@Serializable
data class Division(
    val id: Int? = null,
    val name: String? = null,
    val link: String? = null
)

@Serializable
data class Sport(
    val id: Int? = null,
    val link: String? = null,
    val name: String? = null
)

@Serializable
data class Record(
    val gamesPlayed: Int? = null,
    val wildCardGamesBack: String? = null,
    val leagueGamesBack: String? = null,
    val springLeagueGamesBack: String? = null,
    val sportGamesBack: String? = null,
    val divisionGamesBack: String? = null,
    val conferenceGamesBack: String? = null,
    val leagueRecord: LeagueRecord? = null, // Assuming LeagueRecord data class exists
    val records: Map<String, JsonElement>? = null,
    val divisionLeader: Boolean? = null,
    val wins: Int? = null,
    val losses: Int? = null,
    val winningPercentage: String? = null
)

@Serializable
data class Player(
    val id: Int? = null,
    val fullName: String? = null,
    val link: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val primaryNumber: String? = null,
    val birthDate: String? = null,
    val currentAge: Int? = null,
    val birthCity: String? = null,
    val birthStateProvince: String? = null,
    val birthCountry: String? = null,
    val height: String? = null,
    val weight: Int? = null,
    val active: Boolean? = null,
    val primaryPosition: PrimaryPosition? = null,
    val useName: String? = null,
    val useLastName: String? = null,
    val middleName: String? = null,
    val boxscoreName: String? = null,
    val nickName: String? = null,
    val gender: String? = null,
    val nameMatrilineal: String? = null,
    val isPlayer: Boolean? = null,
    val isVerified: Boolean? = null,
    val draftYear: Int? = null,
    val pronunciation: String? = null,
    val mlbDebutDate: String? = null,
    val batSide: BatSide? = null,
    val pitchHand: PitchHand? = null,
    val nameFirstLast: String? = null,
    val nameTitle: String? = null,
    val nameSuffix: String? = null,
    val nameSlug: String? = null,
    val firstLastName: String? = null,
    val lastFirstName: String? = null,
    val lastInitName: String? = null,
    val initLastName: String? = null,
    val fullFMLName: String? = null,
    val fullLFMName: String? = null,
    val strikeZoneTop: Double? = null,
    val strikeZoneBottom: Double? = null
)

@Serializable
data class PrimaryPosition(
    val code: String? = null,
    val name: String? = null,
    val type: String? = null,
    val abbreviation: String? = null
)

@Serializable
data class BatSide(
    val code: String? = null,
    val description: String? = null
)

@Serializable
data class PitchHand(
    val code: String? = null,
    val description: String? = null
)

// New classes for liveData
@Serializable
data class LiveData(
    val plays: Plays? = null,
    val linescore: Linescore? = null,
    val boxscore: Boxscore? = null,
    val decisions: Decisions? = null,
    val leaders: Leaders? = null,
    val probables: Probables? = null,
    val onBase: OnBase? = null
)

@Serializable
data class Plays(
    val allPlays: List<Play>? = null,
    val currentPlay: Play? = null,
    val playsByInning: List<PlaysByInning>? = null,
    val scoringPlays: List<Int>? = null,
    val lastPlay: Play? = null,
    val reviewPlays: List<JsonElement>? = null // Empty array
)

@Serializable
data class Play(
    val playEvents: List<PlayEvent>? = null,
    val playEndTime: String? = null,
    val playStartTime: String? = null,
    val playId: String? = null,
    val hitters: List<Hitter>? = null, // Assuming Hitter data class exists
    val matchup: Matchup? = null,
    val result: Result? = null, // Assuming Result data class exists (different from Kotlin's Result)
    val about: About? = null,
    val count: Count? = null,
    val runnerEvents: List<RunnerEvent>? = null

)

@Serializable
data class PlayEvent(
    val details: Details? = null, // Assuming Details data class exists
    val index: Int? = null,
    val type: String? = null,
    val playback: Playback? = null, // Assuming Playback data class exists
    val startTime: String? = null,
    val endTime: String? = null,
    val isPitch: Boolean? = null,
    val pitchData: PitchData? = null,
    val player: PlayerRef? = null,
    val batSide: BatSide? = null,
    val strikeZoneTop: Double? = null,
    val strikeZoneBottom: Double? = null
)

@Serializable
data class Details(
    val description: String? = null,
    val event: String? = null,
    val eventType: String? = null,
    val awayScore: Int? = null,
    val homeScore: Int? = null,
    val isScoringPlay: Boolean? = null,
    val hasRBI: Boolean? = null,
    val `type`: PitchType? = null, // 'type' is a keyword, so backticks are needed
    val code: String? = null,
    val call: Call? = null, // Assuming Call data class exists
    val fromCodedEvent: Boolean? = null,
    val isOut: Boolean? = null,
    val isInPlay: Boolean? = null,
    val isStrike: Boolean? = null,
    val isBall: Boolean? = null,
    val isHit: Boolean? = null,
    val isAtBat: Boolean? = null
)

@Serializable
data class PitchType(
    val code: String? = null,
    val description: String? = null
)

@Serializable
data class Call(
    val code: String? = null,
    val description: String? = null
)

@Serializable
data class Playback(
    val start: Double? = null,
    val end: Double? = null,
    val coordinates: List<List<Double>>? = null
)

@Serializable
data class PitchData(
    val strikeZoneTop: Double? = null,
    val strikeZoneBottom: Double? = null,
    val coordinates: Coordinates? = null, // Assuming Coordinates data class exists
    val breaks: Breaks? = null, // Assuming Breaks data class exists
    val zone: Int? = null,
    val typeFlags: TypeFlags? = null, // Assuming TypeFlags data class exists
    val extension: Double? = null, // Assuming Extension data class exists
    val scuffed: Boolean? = null,
    val features: List<Feature>? = null // Assuming Feature data class exists
)

@Serializable
data class Coordinates(
    val x: Double? = null,
    val y: Double? = null,
    val z: Double? = null,
    val pfxX: Double? = null,
    val pfxZ: Double? = null,
    val aX: Double? = null,
    val aY: Double? = null,
    val aZ: Double? = null,
    val vX0: Double? = null,
    val vY0: Double? = null,
    val vZ0: Double? = null,
    val x0: Double? = null,
    val y0: Double? = null,
    val z0: Double? = null,
    val aX0: Double? = null,
    val aY0: Double? = null,
    val aZ0: Double? = null,
    val vX: Double? = null,
    val vY: Double? = null,
    val vZ: Double? = null,
    val x1: Double? = null,
    val y1: Double? = null,
    val z1: Double? = null
)

@Serializable
data class Breaks(
    val breakAngle: Double? = null,
    val breakLength: Double? = null,
    val breakVertical: Double? = null,
    val breakHorizontal: Double? = null,
    val breakVerticalInduced: Double? = null,
    val breakHorizontalInduced: Double? = null,
    val plateX: Double? = null,
    val plateZ: Double? = null,
    val spinRate: Double? = null,
    val spinDirection: Double? = null,
    val zoneSpeed: Double? = null
)

@Serializable
data class TypeFlags(
    val pitcherStance: Boolean? = null,
    val catcherDefense: Boolean? = null,
    val swingAndMiss: Boolean? = null
)

@Serializable
data class Extension(
    val atBatId: String? = null,
    val pitchId: String? = null,
    val trajectory: String? = null,
    val initialSpeed: Double? = null,
    val finalSpeed: Double? = null,
    val sprayAngle: Double? = null
)

@Serializable
data class Feature(
    val featureType: String? = null,
    val time: Double? = null,
    val description: String? = null
)

@Serializable
data class PlayerRef(
    val id: Int? = null,
    val link: String? = null
)

@Serializable
data class Hitter( // Assuming Hitter data class structure
    val id: Int? = null,
    val link: String? = null
    // Add other Hitter specific fields if any
)

@Serializable
data class Matchup(
    val batter: PlayerRef? = null,
    val pitcher: PlayerRef? = null,
    val batSide: BatSide? = null,
    val pitchHand: PitchHand? = null,
    val postOnFirst: PlayerRef? = null,
    val postOnSecond: PlayerRef? = null,
    val postOnThird: PlayerRef? = null
)

@Serializable
data class Result( // Renamed from PlayResult to avoid conflict if Play.Result was intended
    val type: String? = null,
    val event: String? = null,
    val eventType: String? = null,
    val description: String? = null,
    val rbi: Int? = null,
    val sacFly: Boolean? = null, // Assuming this field exists
    val out: Int? = null, // Assuming this field exists
    val runsBattedIn: Int? = null,
    val awayScore: Int? = null,
    val homeScore: Int? = null
)


@Serializable
data class About(
    val atBatIndex: Int? = null,
    val halfInning: String? = null,
    val inning: Int? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val isTopInning: Boolean? = null,
    val hasOut: Boolean? = null,
    val captured: Boolean? = null,
    val isComplete: Boolean? = null,
    val isScoringPlay: Boolean? = null,
    val scoringPlayId: String? = null,
    val scoringPlayUUID: String? = null
)

@Serializable
data class Count(
    val balls: Int? = null,
    val strikes: Int? = null,
    val outs: Int? = null
)

@Serializable
data class RunnerEvent(
    val runner: PlayerRef? = null,
    val details: RunnerDetails? = null, // Assuming RunnerDetails data class exists
    val credits: List<Credit>? = null, // Assuming Credit data class exists
    val movement: Movement? = null // Assuming Movement data class exists
)

@Serializable
data class RunnerDetails(
    val event: String? = null,
    val eventType: String? = null,
    val isOut: Boolean? = null,
    val isScoringPlay: Boolean? = null,
    val rbi: Boolean? = null,
    val responsiblePitcher: PlayerRef? = null,
    val playId: String? = null,
    val runnerGoing: Boolean? = null,
    val speed: Double? = null,
    val direction: String? = null,
    val originBase: String? = null,
    val destinationBase: String? = null
)

@Serializable
data class Credit(
    val player: PlayerRef? = null,
    val position: Position? = null, // Assuming Position data class exists (different from PrimaryPosition)
    val creditFor: String? = null
)

@Serializable
data class Position( // General Position, different from PrimaryPosition
    val code: String? = null,
    val name: String? = null,
    val type: String? = null,
    val abbreviation: String? = null
)

@Serializable
data class Movement(
    val start: String? = null,
    val end: String? = null,
    val isOut: Boolean? = null,
    val outBase: String? = null,
    val outNumber: Int? = null
)

@Serializable
data class PlaysByInning(
    val startIndex: Int? = null,
    val endIndex: Int? = null,
    val top: List<Int>? = null,
    val bottom: List<Int>? = null
)

@Serializable
data class Linescore(
    val innings: List<Inning>? = null, // Assuming Inning data class exists
    val teams: LinescoreTeams? = null, // Assuming LinescoreTeams data class exists
    val runs: Int? = null, // This seems to be a summary, ensure it's distinct from team runs
    val hits: Int? = null, // Summary
    val errors: Int? = null, // Summary
    val leftOnBase: Int? = null, // Summary
    val balls: Int? = null,
    val strikes: Int? = null,
    val outs: Int? = null,
)

@Serializable
data class Inning(
    val num: Int? = null,
    val ordinalNum: String? = null,
    val home: InningScore? = null, // Assuming InningScore data class exists
    val away: InningScore? = null,
    val runs: Int? = null, // This seems to be total runs in this specific inning half
    val hits: Int? = null,
    val errors: Int? = null,
    val leftOnBase: Int? = null
)

@Serializable
data class InningScore(
    val runs: Int? = null,
    val hits: Int? = null,
    val errors: Int? = null,
    val leftOnBase: Int? = null
)

@Serializable
data class LinescoreTeams(
    val home: TeamScore? = null, // Assuming TeamScore data class exists
    val away: TeamScore? = null
)

@Serializable
data class TeamScore(
    val runs: Int? = null,
    val hits: Int? = null,
    val errors: Int? = null,
    val leftOnBase: Int? = null
)

@Serializable
data class Boxscore(
    val teams: BoxscoreTeams? = null, // Assuming BoxscoreTeams data class exists
    val players: Map<String, PlayerBoxscore>? = null, // Assuming PlayerBoxscore data class exists
    val battingOrder: List<List<Int>>? = null,
    val pitchingCoaches: List<Int>? = null,
    val hittingCoaches: List<Int>? = null,
    val baseCoaches: List<Int>? = null,
    val bullPenCoaches: List<Int>? = null,
    val coaches: List<Coach>? = null, // Assuming Coach data class exists
    val umpires: List<Umpire>? = null, // Assuming Umpire data class exists
    val officialScorer: OfficialScorer? = null, // Assuming OfficialScorer data class exists
    val pitchers: Pitchers? = null, // Assuming Pitchers data class exists
    val catcher: Catcher? = null, // Assuming Catcher data class exists
    val firstBase: FirstBase? = null, // Assuming FirstBase data class exists
    val secondBase: SecondBase? = null, // Assuming SecondBase data class exists
    val thirdBase: ThirdBase? = null, // Assuming ThirdBase data class exists
    val shortStop: ShortStop? = null, // Assuming ShortStop data class exists
    val leftField: LeftField? = null, // Assuming LeftField data class exists
    val centerField: CenterField? = null, // Assuming CenterField data class exists
    val rightField: RightField? = null, // Assuming RightField data class exists
    val designatedHitter: DesignatedHitter? = null, // Assuming DesignatedHitter data class exists
    val benchCoaches: List<Int>? = null,
    val activePlayers: List<Int>? = null,
    val activePitchers: List<Int>? = null
)

@Serializable
data class BoxscoreTeams(
    val home: TeamBoxscore? = null, // Assuming TeamBoxscore data class exists
    val away: TeamBoxscore? = null
)

@Serializable
data class TeamBoxscore(
    val batters: List<Int>? = null,
    val pitchers: List<Int>? = null,
    val bench: List<Int>? = null,
    val bullpen: List<Int>? = null,
    val players: Map<String, PlayerDetail>? = null, // Assuming PlayerDetail data class exists
    val batting: List<Batting>? = null, // Assuming Batting data class exists
    val pitching: List<Pitching>? = null, // Assuming Pitching data class exists
    val fielding: List<Fielding>? = null, // Assuming Fielding data class exists
    val runs: Int? = null,
    val hits: Int? = null,
    val errors: Int? = null,
    val leftOnBase: Int? = null,
    val sacBunts: Int? = null,
    val sacFlies: Int? = null,
    val doublePlays: Int? = null,
    val triplePlays: Int? = null,
    val hr: Int? = null,
    val rbi: Int? = null,
    val team: PlayerRef? = null
)

@Serializable
data class PlayerBoxscore(
    val parentTeamId: Int? = null,
    val allPositions: List<PositionDetail>? = null, // Assuming PositionDetail data class exists
    val person: PlayerRef? = null,
    val jerseyNumber: String? = null,
    val position: Position? = null, // Using the general Position data class
    val status: PlayerStatus? = null, // Assuming PlayerStatus data class exists
    val hotColdZones: List<HotColdZone>? = null, // Assuming HotColdZone data class exists
    val stats: PlayerStats? = null, // Assuming PlayerStats data class exists
    val seasonStats: PlayerStats? = null
)

@Serializable
data class PositionDetail( // More specific than general Position
    val code: String? = null,
    val name: String? = null,
    val type: String? = null,
    val abbreviation: String? = null,
    val displayName: String? = null
)

@Serializable
data class PlayerStatus(
    val code: String? = null,
    val description: String? = null
)

@Serializable
data class HotColdZone(
    val zone: String? = null,
    val hotCold: String? = null
)

@Serializable
data class PlayerStats(
    val batting: BattingStats? = null, // Assuming BattingStats data class exists
    val pitching: PitchingStats? = null, // Assuming PitchingStats data class exists
    val fielding: FieldingStats? = null // Assuming FieldingStats data class exists
)

@Serializable
data class BattingStats(
    val gamesPlayed: Int? = null,
    val flyOuts: Int? = null,
    val groundOuts: Int? = null,
    val runs: Int? = null,
    val doubles: Int? = null,
    val triples: Int? = null,
    val homeRuns: Int? = null,
    val strikeOuts: Int? = null,
    val baseOnBalls: Int? = null,
    val intentionalWalks: Int? = null,
    val hits: Int? = null,
    val hitByPitch: Int? = null,
    val avg: String? = null,
    val atBats: Int? = null,
    val obp: String? = null,
    val slg: String? = null,
    val ops: String? = null,
    val caughtStealing: Int? = null,
    val stolenBases: Int? = null,
    val groundIntoDoublePlay: Int? = null,
    val groundIntoTriplePlay: Int? = null,
    val numberOfPitches: Int? = null,
    val plateAppearances: Int? = null,
    val totalBases: Int? = null,
    val rbi: Int? = null,
    val leftOnBase: Int? = null,
    val sacBunts: Int? = null,
    val sacFlies: Int? = null,
    val catchersInterference: Int? = null,
    val popOuts: Int? = null,
    val assists: Int? = null,
    val putOuts: Int? = null,
    val errors: Int? = null,
    val chances: Int? = null,
    val battingAverage: String? = null
)

@Serializable
data class PitchingStats(
    val gamesPlayed: Int? = null,
    val gamesStarted: Int? = null,
    val flyOuts: Int? = null,
    val groundOuts: Int? = null,
    val runs: Int? = null,
    val doubles: Int? = null,
    val triples: Int? = null,
    val homeRuns: Int? = null,
    val strikeOuts: Int? = null,
    val baseOnBalls: Int? = null,
    val intentionalWalks: Int? = null,
    val hits: Int? = null,
    val hitByPitch: Int? = null,
    val avg: String? = null,
    val atBats: Int? = null,
    val obp: String? = null,
    val slg: String? = null,
    val ops: String? = null,
    val caughtStealing: Int? = null,
    val stolenBases: Int? = null,
    val groundIntoDoublePlay: Int? = null,
    val groundIntoTriplePlay: Int? = null,
    val numberOfPitches: Int? = null,
    val plateAppearances: Int? = null,
    val totalBases: Int? = null,
    val rbi: Int? = null,
    val leftOnBase: Int? = null,
    val sacBunts: Int? = null,
    val sacFlies: Int? = null,
    val catchersInterference: Int? = null,
    val popOuts: Int? = null,
    val assists: Int? = null,
    val putOuts: Int? = null,
    val errors: Int? = null,
    val chances: Int? = null,
    val wins: Int? = null,
    val losses: Int? = null,
    val saves: Int? = null,
    val saveOpportunities: Int? = null,
    val holds: Int? = null,
    val blownSaves: Int? = null,
    val earnedRuns: Int? = null,
    val battersFaced: Int? = null,
    val outs: Int? = null,
    val pitchesThrown: Int? = null,
    val balls: Int? = null,
    val strikes: Int? = null,
    val hitBatsmen: Int? = null,
    val balks: Int? = null,
    val wildPitches: Int? = null,
    val pickoffs: Int? = null,
    val era: String? = null,
    val inningsPitched: String? = null,
    val inheritedRunnerScored: Int? = null,
    val inheritedRunners: Int? = null,
    val wp: Double? = null, // Assuming this is winning percentage as Double
    val runnersOnBase: Int? = null,
    val strikeoutsPer9Inn: String? = null,
    val walksPer9Inn: String? = null,
    val hitsPer9Inn: String? = null,
    val runsScoredPer9Inn: String? = null,
    val inningsPerGame: String? = null,
    val inningsPerStart: String? = null,
    val whip: String? = null,
    val pitchesPerInning: String? = null,
    val pitchesPerStart: String? = null
)

@Serializable
data class FieldingStats(
    val assists: Int? = null,
    val putOuts: Int? = null,
    val errors: Int? = null,
    val chances: Int? = null,
    val fielding: String? = null,
    val passedBall: Int? = null,
    val caughtStealing: Int? = null,
    val stolenBases: Int? = null,
    val pickoffs: Int? = null
)

@Serializable
data class PlayerDetail( // Used within TeamBoxscore.players
    val person: PlayerRef? = null,
    val jerseyNumber: String? = null,
    val position: Position? = null, // General Position
    val stats: PlayerStats? = null,
    val status: PlayerStatus? = null
)

@Serializable
data class Batting( // Team-level batting summary
    val team: PlayerRef? = null,
    val players: Map<String, BattingPlayerStats>? = null // Assuming BattingPlayerStats data class exists
)

@Serializable
data class BattingPlayerStats( // Stats for individual players under team batting
    val plateAppearances: Int? = null,
    val atBats: Int? = null,
    val runs: Int? = null,
    val hits: Int? = null,
    val doubles: Int? = null,
    val triples: Int? = null,
    val homeRuns: Int? = null,
    val rbi: Int? = null,
    val strikeOuts: Int? = null,
    val baseOnBalls: Int? = null,
    val intentionalWalks: Int? = null,
    val hitByPitch: Int? = null,
    val sacBunts: Int? = null,
    val sacFlies: Int? = null,
    val groundIntoDoublePlay: Int? = null,
    val groundIntoTriplePlay: Int? = null,
    val stolenBases: Int? = null,
    val caughtStealing: Int? = null,
    val leftOnBase: Int? = null,
    val avg: String? = null,
    val obp: String? = null,
    val slg: String? = null,
    val ops: String? = null,
    val totalBases: Int? = null,
    val flyOuts: Int? = null,
    val groundOuts: Int? = null,
    val catchersInterference: Int? = null,
    val popOuts: Int? = null
)

@Serializable
data class Pitching( // Team-level pitching summary
    val team: PlayerRef? = null,
    val players: Map<String, PitchingPlayerStats>? = null // Assuming PitchingPlayerStats data class exists
)

@Serializable
data class PitchingPlayerStats( // Stats for individual players under team pitching
    val rbi: Int? = null, // Note: RBI for a pitcher is unusual, but present in original
    val earnedRuns: Int? = null,
    val battersFaced: Int? = null,
    val outs: Int? = null,
    val pitchesThrown: Int? = null,
    val balls: Int? = null,
    val strikes: Int? = null,
    val hitBatsmen: Int? = null,
    val balks: Int? = null,
    val wildPitches: Int? = null,
    val pickoffs: Int? = null,
    val gamesPlayed: Int? = null,
    val gamesStarted: Int? = null,
    val flyOuts: Int? = null,
    val groundOuts: Int? = null,
    val runs: Int? = null,
    val doubles: Int? = null,
    val triples: Int? = null,
    val homeRuns: Int? = null,
    val strikeOuts: Int? = null,
    val baseOnBalls: Int? = null,
    val intentionalWalks: Int? = null,
    val hits: Int? = null,
    val hitByPitch: Int? = null,
    val avg: String? = null,
    val atBats: Int? = null,
    val obp: String? = null,
    val slg: String? = null,
    val ops: String? = null,
    val caughtStealing: Int? = null,
    val stolenBases: Int? = null,
    val groundIntoDoublePlay: Int? = null,
    val groundIntoTriplePlay: Int? = null,
    val numberOfPitches: Int? = null,
    val plateAppearances: Int? = null,
    val totalBases: Int? = null,
    val leftOnBase: Int? = null,
    val sacBunts: Int? = null,
    val sacFlies: Int? = null,
    val catchersInterference: Int? = null,
    val popOuts: Int? = null,
    val assists: Int? = null,
    val putOuts: Int? = null,
    val errors: Int? = null,
    val chances: Int? = null,
    val wins: Int? = null,
    val losses: Int? = null,
    val saves: Int? = null,
    val saveOpportunities: Int? = null,
    val holds: Int? = null,
    val blownSaves: Int? = null,
    val era: String? = null,
    val inningsPitched: String? = null
)

@Serializable
data class Fielding( // Team-level fielding summary
    val team: PlayerRef? = null,
    val players: Map<String, FieldingPlayerStats>? = null // Assuming FieldingPlayerStats data class exists
)

@Serializable
data class FieldingPlayerStats( // Stats for individual players under team fielding
    val assists: Int? = null,
    val putOuts: Int? = null,
    val errors: Int? = null,
    val chances: Int? = null,
    val fielding: String? = null,
    val passedBall: Int? = null,
    val caughtStealing: Int? = null,
    val stolenBases: Int? = null,
    val pickoffs: Int? = null
)

@Serializable
data class Coach(
    val person: PlayerRef? = null,
    val jerseyNumber: String? = null,
    val position: Position? = null, // General Position
    val `type`: String? = null
)

@Serializable
data class Umpire(
    val person: PlayerRef? = null,
    val position: String? = null, // Position here is a string like "HP", "1B"
    val type: String? = null // e.g. "Crew Chief"
)

@Serializable
data class OfficialScorer(
    val id: Int? = null,
    val link: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val fullName: String? = null
)

@Serializable
data class Pitchers( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class Catcher( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class FirstBase( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class SecondBase( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class ThirdBase( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class ShortStop( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class LeftField( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class CenterField( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class RightField( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class DesignatedHitter( // Lists of player IDs
    val home: List<Int>? = null,
    val away: List<Int>? = null
)

@Serializable
data class Decisions(
    val winner: PlayerRef? = null,
    val loser: PlayerRef? = null,
    val save: PlayerRef? = null
)

@Serializable
data class Leaders(
    val home: TeamLeaders? = null, // Assuming TeamLeaders data class exists
    val away: TeamLeaders? = null
)

@Serializable
data class TeamLeaders(
    val batting: List<LeaderStat>? = null, // Assuming LeaderStat data class exists
    val pitching: List<LeaderStat>? = null
)

@Serializable
data class LeaderStat(
    val leader: PlayerRef? = null,
    val stat: String? = null,
    val value: String? = null // Value can be numeric or string (e.g., average)
)

@Serializable
data class Probables(
    val home: ProbablePitcher? = null, // Assuming ProbablePitcher data class exists
    val away: ProbablePitcher? = null
)

@Serializable
data class ProbablePitcher(
    val id: Int? = null,
    val fullName: String? = null,
    val link: String? = null
)

@Serializable
data class OnBase(
    val menOnBase: String? = null // e.g., "Empty", "Man on first"
)
