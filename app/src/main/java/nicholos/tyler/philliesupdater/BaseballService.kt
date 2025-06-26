package nicholos.tyler.philliesupdater

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BaseballService {
    // @PATH adds /1234
    @GET("api/v1.1/game/{gamePk}/feed/live")
    suspend fun getGameDetails(@Path("gamePk") gamePk: Long): Response<GameDetailResponse>

    // QUERY adds &sportsID=1 etc
    @GET("api/v1/schedule")
    suspend fun getMlbSchedule(
        @Query("sportId") sportId: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("teamId") teamId: Int,
    ): Response<GameRoot>

    @GET("api/v1/standings")
    suspend fun getStandings(
        @Query("leagueId") leagueId: Int,
    ): Response<StandingsResponse>

    @GET("api/v1/teams/{teamId}/roster")
    suspend fun getTeamRoster(
        @Path("teamId") teamId: Int,
    ): Response<RosterResponse>

    @GET("api/v1/teams/{teamId}")
    suspend fun getTeam(
        @Path("teamId") teamId: Int,
    ): Response<TeamResponse>


}