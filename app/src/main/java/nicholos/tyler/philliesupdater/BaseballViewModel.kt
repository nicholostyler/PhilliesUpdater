package nicholos.tyler.philliesupdater

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.coroutines.cancellation.CancellationException

data class LiveGameData(
    val homeTeamName: String,
    val awayTeamName: String,
    val homeTeamScore: Long,
    val awayTeamScore: Long,
    val inning: Int,
    val inningSuffix: String,
    val isTopInning: Boolean,
    val outs: Int ,
    val runnersOnBase: String,
    val isGameOver: Boolean,
    val status: String
)

data class HomePageUiState(
    val liveGameData: LiveGameData?  = null,
    val scheduledGames: List<Date> = emptyList(),
    val division: List<TeamRecord> = emptyList(),
    val tenDaySchedule: List<Date> = emptyList()
)

data class TeamPageUiState(
    val teamDetails: TeamDetails  = TeamDetails(),
    val roster: List<PlayerRoster> = emptyList(),
    val divisionRank: Int = 0,
)

data class SchedulePageUiState(
    val scheduledGames: List<Game> = emptyList(),
    val gameCardList: List<ScheduleGameData> = emptyList()
)

data class DetailPageUiState(
    val plays: List<Play> = emptyList(),
    val game: Game = Game()
)

class BaseballViewModel : ViewModel() {

    private val _baseballScheduleData = MutableStateFlow<GameRoot?>(null)
    val baseballScheduleData: MutableStateFlow<GameRoot?> = _baseballScheduleData

    private val _baseballGameData = MutableStateFlow<GameDetailResponse?>(null)
    val baseballGameData: MutableStateFlow<GameDetailResponse?> = _baseballGameData

    private val _selectedGame = MutableStateFlow<Game?>(null)
    val selectedGame: StateFlow<Game?> = _selectedGame


    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: MutableStateFlow<String?> = _errorMessage

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _homePageUiState = MutableStateFlow(HomePageUiState())
    val homePageUiState: MutableStateFlow<HomePageUiState> = _homePageUiState

    private val _scheduleUiState = MutableStateFlow(SchedulePageUiState())
    val scheduleUiState: MutableStateFlow<SchedulePageUiState> = _scheduleUiState

    private val _detailPageUiState = MutableStateFlow(DetailPageUiState())
    val detailPageUiState: MutableStateFlow<DetailPageUiState> = _detailPageUiState

    private val _teamPageUiState = MutableStateFlow(TeamPageUiState())
    val teamPageUiState: MutableStateFlow<TeamPageUiState> = _teamPageUiState

    private val _division = MutableStateFlow<List<StandingsRecord>>(emptyList())
    val division: MutableStateFlow<List<StandingsRecord>> = _division

    private val _teamRoster = MutableStateFlow<List<PlayerRoster>>(emptyList())
    val teamRoster: MutableStateFlow<List<PlayerRoster>> = _teamRoster

    val selectedTeam: MutableStateFlow<MLBTeam?> = MutableStateFlow(MLBTeam.PHILLIES)

    init {
        Log.d("BaseballViewModel", "ViewModel initialized")
        selectedTeam.value = MLBTeam.PHILLIES

        viewModelScope.launch {
            fetchBaseballSchedule()
            _isRefreshing.value = false
        }

    }

    fun setSelectedGame(game: Game?) {
        if (game != null) {
            _selectedGame.value = game
            //_selectedGame.value!!.gamePk?.let { fetchGameDetails(it.toLong()) }
        }
    }

    fun refreshDetailPage(selectedGame: Game) {
        _isRefreshing.value = true
        viewModelScope.launch {

            if (selectedGame?.gamePk != null) {
                val gameDetailResponse = fetchGameDetails(selectedGame.gamePk)
                if (gameDetailResponse != null) {
                    val allPlays = gameDetailResponse.liveData?.plays?.allPlays
                    if (!allPlays.isNullOrEmpty()) {
                        val plays: List<Play> = allPlays
                        _detailPageUiState.value = DetailPageUiState(
                            plays = plays,
                            game = selectedGame
                        )
                    }


                }
            }
        }
    }

    fun refreshTeamPage() {
        viewModelScope.launch {
            try {
                selectedTeam.value?.teamId?.let { teamId ->
                    val teamRoster = fetchTeamRoster(teamId)
                    val team = fetchTeam(teamId)
                    _teamPageUiState.update {
                        it.copy(
                            roster = teamRoster,
                            teamDetails = team
                        )
                    }

                }
            } catch (e: Exception) {
                Log.e("BaseballViewModel", "Error in refreshHomePage", e)
            }
        }
    }

    fun getTodaysGame(): Game? {
        val scheduleRoot = _baseballScheduleData.value ?: return null;

        if (scheduleRoot.dates.isNullOrEmpty()) {
            Log.d("BaseballViewModel", "No dates available in schedule data.")
            return null;
        }

        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val todayFormattedString = today.format(formatter)

        val todaysScheduleDate: Date? = scheduleRoot.dates.find { scheduleDate ->
            scheduleDate.date == todayFormattedString
        }

        if (todaysScheduleDate == null) {
            Log.d(
                "BaseballViewModel",
                "No schedule entry found for today's date: $todayFormattedString"
            )
            return null
        }

        if (todaysScheduleDate.games.isNullOrEmpty()) {
            Log.d(
                "BaseballViewModel",
                "No games listed for today's date: $todayFormattedString, even though date entry exists."
            )
            return null
        }

        var firstGameToday: Game? = todaysScheduleDate.games.firstOrNull()


        if (firstGameToday?.status?.detailedState == "Final" && todaysScheduleDate.games.size > 1) {
            firstGameToday = todaysScheduleDate.games[1]
        }

        if (firstGameToday == null) {
            Log.d(
                "BaseballViewModel",
                "Games list for today is present but empty, or first game is null."
            )
        } else {
            Log.d(
                "BaseballViewModel",
                "Found first game for today. GamePK: ${firstGameToday.gamePk}"
            )
        }

        return firstGameToday
    }

    fun refreshHomePage() {
        _isRefreshing.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val schedule = _baseballScheduleData
                    .filterNotNull()
                    .first()

                if (schedule != null) {
                    val todayGame = getTodaysGame()
                    val datesToSet = _baseballScheduleData.value?.dates ?: emptyList()
                    val tenDaySchedule = getDatesWithinTenDayRange()

                    // Fetch standings
                    _division.value = fetchBaseballStandings()

                    // Prepare division records
                    val eastDivisionTeamRecords = _division.value
                        .firstOrNull { it.division?.id == 204 }
                        ?.teamRecords
                        ?.filterNotNull()
                        .orEmpty()

                    // Prepare live game data
                    val mappedLiveData = if (todayGame?.gamePk != null) {
                        val details = fetchGameDetails(todayGame.gamePk)
                        details.toLiveGameData(todayGame)
                    } else null

                    // Commit full UI state update
                    _homePageUiState.value = HomePageUiState(
                        scheduledGames = datesToSet,
                        tenDaySchedule = tenDaySchedule,
                        division = eastDivisionTeamRecords,
                        liveGameData = mappedLiveData
                    )
                }
            } catch (e: Exception) {
                Log.e("BaseballViewModel", "Error in refreshHomePage", e)
            } finally {
                _isRefreshing.value = false
            }
        }
    }


    fun getSchedulePage() {
        _isRefreshing.value = true
        if (_baseballScheduleData.value != null) {
            if (_baseballScheduleData.value?.dates?.isEmpty() == false) {
                val games = getAllGames(_baseballScheduleData.value)
                selectedTeam.value?.name?.takeIf { it.isNotBlank() }?.let { teamName ->
                    val scheduleGameDataList = games.toScheduleGameDataList(teamName)
                    if (games.isNotEmpty()) {
                        _scheduleUiState.update {
                            it.copy(scheduledGames = games, gameCardList = scheduleGameDataList)
                        }
                    }
                }

            }
        }
    }

    private suspend fun fetchBaseballStandings(): List<StandingsRecord> {
        _isRefreshing.value = true
        Log.i(
            "BaseballViewModel",
            "FETCH_SCHEDULE_DATA_CALLED. ViewModel HashCode: ${this.hashCode()}"
        )

        // Log the state of the viewModelScope's job BEFORE launching
        Log.d(
            "BaseballViewModel",
            "viewModelScope.isActive: ${viewModelScope.coroutineContext.job.isActive}"
        )
        Log.d(
            "BaseballViewModel",
            "viewModelScope.isCancelled: ${viewModelScope.coroutineContext.job.isCancelled}"
        )
         Log.i("BaseballViewModel", "COROUTINE_STARTED. ViewModel HashCode: ${this.hashCode()}")
            _errorMessage.value = null
            _division.value = emptyList() // Clear previous data while loading

            try {
                Log.d("BaseballViewModel", "TRY_BLOCK_ENTERED. Calling API...")
                // Replace with your actual RetrofitClient setup if it's different
                val response = RetrofitClient().baseballApiService.getStandings(
                    leagueId = 104
                )

                Log.d(
                    "BaseballViewModel",
                    "API_RESPONSE_RECEIVED. Code: ${response.code()}, Successful: ${response.isSuccessful}"
                )

                if (response.isSuccessful) {
                    val scheduleData = response.body()
                    if (scheduleData != null) {
                        Log.i("BaseballViewModel", "API_SUCCESS. Data: $baseballScheduleData")
                        if (scheduleData.records.isNullOrEmpty() == false)
                        return scheduleData.records


                    } else {
                        Log.e(
                            "BaseballViewModel",
                            "API_SUCCESS_BUT_NULL_BODY. Code: ${response.code()}"
                        )
                        _errorMessage.value = "Error: Empty response from server."
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.e(
                        "BaseballViewModel",
                        "API_ERROR. Code: ${response.code()}, Message: ${response.message()}, Body: $errorBody"
                    )
                    _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: CancellationException) {
                Log.w(
                    "BaseballViewModel",
                    "CATCH_BLOCK_CANCELLATION_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                    e
                )
                _errorMessage.value = "Request was cancelled."
                throw e // Re-throw cancellation exceptions as per best practice
            } catch (e: Exception) {
                Log.e(
                    "BaseballViewModel",
                    "CATCH_BLOCK_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                    e
                )
                _errorMessage.value = "Network error: ${e.message}"
            } finally {
                Log.i(
                    "BaseballViewModel",
                    "FINALLY_BLOCK_EXECUTED. ViewModel HashCode: ${this.hashCode()}"
                )
            }
            return emptyList()
    }

    private suspend fun fetchTeamRoster(teamId: Int): List<PlayerRoster> {
        _isRefreshing.value = true
        _errorMessage.value = null
        _teamRoster.value = emptyList()

        try {
            val response = RetrofitClient().baseballApiService.getTeamRoster(
                teamId = teamId
            )

            if (response.isSuccessful) {
                val rosterData = response.body()
                if (rosterData != null) {
                    if (rosterData.roster.isNotEmpty()) {
                        return rosterData.roster
                    }

                } else {
                    _errorMessage.value = "Error: Empty response from server."
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Log.e(
                    "BaseballViewModel",
                    "API_ERROR. Code: ${response.code()}, Message: ${response.message()}, Body: $errorBody"
                )
                _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
            }
        } catch (e: CancellationException) {
            Log.w(
                "BaseballViewModel",
                "CATCH_BLOCK_CANCELLATION_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                e
            )
            _errorMessage.value = "Request was cancelled."
            throw e // Re-throw cancellation exceptions as per best practice
        } catch (e: Exception) {
            Log.e(
                "BaseballViewModel",
                "CATCH_BLOCK_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                e
            )
            _errorMessage.value = "Network error: ${e.message}"
        } finally {
            Log.i(
                "BaseballViewModel",
                "FINALLY_BLOCK_EXECUTED. ViewModel HashCode: ${this.hashCode()}"
            )
        }
        return emptyList()
    }

    private suspend fun fetchTeam(teamId: Int): TeamDetails {
        _isRefreshing.value = true
        _errorMessage.value = null

        try {
            val response = RetrofitClient().baseballApiService.getTeam(
                teamId = teamId
            )

            if (response.isSuccessful) {
                val teamData = response.body()
                if (teamData != null) {
                    val team = teamData.teams?.firstOrNull()
                    if (team != null) {
                        return mapTeamDetails(team)
                    }

                } else {
                    _errorMessage.value = "Error: Empty response from server."
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Log.e(
                    "BaseballViewModel",
                    "API_ERROR. Code: ${response.code()}, Message: ${response.message()}, Body: $errorBody"
                )
                _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
            }
        } catch (e: CancellationException) {
            Log.w(
                "BaseballViewModel",
                "CATCH_BLOCK_CANCELLATION_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                e
            )
            _errorMessage.value = "Request was cancelled."
            throw e // Re-throw cancellation exceptions as per best practice
        } catch (e: Exception) {
            Log.e(
                "BaseballViewModel",
                "CATCH_BLOCK_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                e
            )
            _errorMessage.value = "Network error: ${e.message}"
        } finally {
            Log.i(
                "BaseballViewModel",
                "FINALLY_BLOCK_EXECUTED. ViewModel HashCode: ${this.hashCode()}"
            )
        }
        return TeamDetails()
    }

    suspend fun fetchBaseballSchedule() {
        _isRefreshing.value = true
        Log.i(
            "BaseballViewModel",
            "FETCH_SCHEDULE_DATA_CALLED. ViewModel HashCode: ${this.hashCode()}"
        )

        // Log the state of the viewModelScope's job BEFORE launching
        Log.d(
            "BaseballViewModel",
            "viewModelScope.isActive: ${viewModelScope.coroutineContext.job.isActive}"
        )
        Log.d(
            "BaseballViewModel",
            "viewModelScope.isCancelled: ${viewModelScope.coroutineContext.job.isCancelled}"
        )

        val job = withContext(Dispatchers.IO) {
            // THIS IS THE VERY FIRST LINE INSIDE THE COROUTINE
            Log.i("BaseballViewModel", "COROUTINE_STARTED. ViewModel HashCode: ${this.hashCode()}")
            _errorMessage.value = null
            _baseballScheduleData.value = null // Clear previous data while loading

            try {
                Log.d("BaseballViewModel", "TRY_BLOCK_ENTERED. Calling API...")
                // Replace with your actual RetrofitClient setup if it's different
                val response = RetrofitClient().baseballApiService.getMlbSchedule(
                    sportId = 1,
                    startDate = "2025-05-25",
                    endDate = "2025-06-28",
                    teamId = 143
                )

                Log.d(
                    "BaseballViewModel",
                    "API_RESPONSE_RECEIVED. Code: ${response.code()}, Successful: ${response.isSuccessful}"
                )

                if (response.isSuccessful) {
                    val scheduleData = response.body()
                    if (scheduleData != null) {
                        Log.i("BaseballViewModel", "API_SUCCESS. Data: $baseballScheduleData")
                        _baseballScheduleData.value = scheduleData



                    } else {
                        Log.e(
                            "BaseballViewModel",
                            "API_SUCCESS_BUT_NULL_BODY. Code: ${response.code()}"
                        )
                        _errorMessage.value = "Error: Empty response from server."
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.e(
                        "BaseballViewModel",
                        "API_ERROR. Code: ${response.code()}, Message: ${response.message()}, Body: $errorBody"
                    )
                    _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: CancellationException) {
                Log.w(
                    "BaseballViewModel",
                    "CATCH_BLOCK_CANCELLATION_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                    e
                )
                _errorMessage.value = "Request was cancelled."
                throw e // Re-throw cancellation exceptions as per best practice
            } catch (e: Exception) {
                Log.e(
                    "BaseballViewModel",
                    "CATCH_BLOCK_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                    e
                )
                _errorMessage.value = "Network error: ${e.message}"
            } finally {
                Log.i(
                    "BaseballViewModel",
                    "FINALLY_BLOCK_EXECUTED. ViewModel HashCode: ${this.hashCode()}"
                )
            }
        }
    }

    private suspend fun fetchGameDetails(gameId: Long): GameDetailResponse {
        Log.i(
            "BaseballViewModel",
            "FETCH_SCHEDULE_DATA_CALLED. ViewModel HashCode: ${this.hashCode()}"
        )

        // Log the state of the viewModelScope's job BEFORE launching
        Log.d(
            "BaseballViewModel",
            "viewModelScope.isActive: ${viewModelScope.coroutineContext.job.isActive}"
        )
        Log.d(
            "BaseballViewModel",
            "viewModelScope.isCancelled: ${viewModelScope.coroutineContext.job.isCancelled}"
        )

        // THIS IS THE VERY FIRST LINE INSIDE THE COROUTINE
        Log.i("BaseballViewModel", "COROUTINE_STARTED. ViewModel HashCode: ${this.hashCode()}")
        _errorMessage.value = null
        _baseballGameData.value = null // Clear previous data while loading

        try {
            Log.d("BaseballViewModel", "TRY_BLOCK_ENTERED. Calling API...")
            // Replace with your actual RetrofitClient setup if it's different
            val response = RetrofitClient().baseballApiService.getGameDetails(
                gamePk = gameId
            )

            Log.d(
                "BaseballViewModel",
                "API_RESPONSE_RECEIVED. Code: ${response.code()}, Successful: ${response.isSuccessful}"
            )

            if (response.isSuccessful) {
                val gameData = response.body()
                if (gameData != null) {
                    Log.i("BaseballViewModel", "API_SUCCESS. Data: $baseballGameData")
                    //_baseballGameData.value = gameData
                    return gameData


                } else {
                    Log.e(
                        "BaseballViewModel",
                        "API_SUCCESS_BUT_NULL_BODY. Code: ${response.code()}"
                    )
                    _errorMessage.value = "Error: Empty response from server."
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Log.e(
                    "BaseballViewModel",
                    "API_ERROR. Code: ${response.code()}, Message: ${response.message()}, Body: $errorBody"
                )
                _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
            }
        } catch (e: CancellationException) {
            Log.w(
                "BaseballViewModel",
                "CATCH_BLOCK_CANCELLATION_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                e
            )
            _errorMessage.value = "Request was cancelled."
            throw e // Re-throw cancellation exceptions as per best practice
        } catch (e: Exception) {
            Log.e(
                "BaseballViewModel",
                "CATCH_BLOCK_EXCEPTION. ViewModel HashCode: ${this.hashCode()}",
                e
            )
            _errorMessage.value = "Network error: ${e.message}"
        } finally {
            Log.i(
                "BaseballViewModel",
                "FINALLY_BLOCK_EXECUTED. ViewModel HashCode: ${this.hashCode()}"
            )
        }

        return GameDetailResponse()
    }

    fun getDatesWithinTenDayRange(): List<Date> {
        val allDates = _baseballScheduleData.value?.dates ?: return emptyList()

        val today = LocalDate.now()
        val startDate = today.minusDays(5)
        val endDate = today.plusDays(5)
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE

        return allDates.filterNotNull().filter { dateEntry ->
            val parsedDate = try {
                LocalDate.parse(dateEntry.date, formatter)
            } catch (e: Exception) {
                null
            }

            parsedDate != null && !parsedDate.isBefore(startDate) && !parsedDate.isAfter(endDate)
        }
    }

    fun getAllGames(scheduleData: GameRoot?): List<Game> {
        return scheduleData
            ?.dates
            ?.filterNotNull()
            ?.flatMap { it.games.orEmpty().filterNotNull() }
            ?: emptyList()
    }

    fun mapTeamDetails(raw: TeamDetails?): TeamDetails {
        return TeamDetails(
            springLeague = raw?.springLeague ?: League(-1, "Unknown", "", ""),
            allStarStatus = raw?.allStarStatus ?: "N",
            id = raw?.id ?: -1,
            name = raw?.name ?: "Unnamed Team",
            link = raw?.link ?: "",
            season = raw?.season ?: 0,
            venue = raw?.venue ?: Venue(-1, "Unknown", ""),
            springVenue = raw?.springVenue ?: SpringVenue(-1, ""),
            teamCode = raw?.teamCode ?: "XXX",
            fileCode = raw?.fileCode ?: "unknown",
            abbreviation = raw?.abbreviation ?: "UNK",
            teamName = raw?.teamName ?: "Unknown",
            locationName = raw?.locationName ?: "Unknown",
            firstYearOfPlay = raw?.firstYearOfPlay ?: "N/A",
            league = raw?.league ?: League(-1, "Unknown", "", ""),
            division = raw?.division ?: Division(-1, "Unknown", ""),
            sport = raw?.sport ?: Sport(-1, "Unknown", ""),
            shortName = raw?.shortName ?: "N/A",
            record = raw?.record ?: Record(0, "0"),
            franchiseName = raw?.franchiseName ?: "N/A",
            clubName = raw?.clubName ?: "N/A",
            active = raw?.active ?: false
        )
    }

}