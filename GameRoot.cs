using System.Text.Json.Serialization;

namespace PhilliesUpdater;

public class GameRoot
{
    [JsonPropertyName("copyright")]
    public string Copyright { get; set; }

    [JsonPropertyName("totalItems")]
    public int TotalItems { get; set; }

    [JsonPropertyName("totalEvents")]
    public int TotalEvents { get; set; }

    [JsonPropertyName("totalGames")]
    public int TotalGames { get; set; }

    [JsonPropertyName("totalGamesInProgress")]
    public int TotalGamesInProgress { get; set; }

    [JsonPropertyName("dates")]
    public List<DateInfo> Dates { get; set; }
}

public class DateInfo
{
    [JsonPropertyName("date")]
    public string Date { get; set; }

    [JsonPropertyName("totalItems")]
    public int TotalItems { get; set; }

    [JsonPropertyName("totalEvents")]
    public int TotalEvents { get; set; }

    [JsonPropertyName("totalGames")]
    public int TotalGames { get; set; }

    [JsonPropertyName("totalGamesInProgress")]
    public int TotalGamesInProgress { get; set; }

    [JsonPropertyName("games")]
    public List<Game> Games { get; set; }

    [JsonPropertyName("events")]
    public List<object> Events { get; set; }
}

public class Game
{
    [JsonPropertyName("gamePk")]
    public int GamePk { get; set; }

    [JsonPropertyName("gameGuid")]
    public string GameGuid { get; set; }

    [JsonPropertyName("link")]
    public string Link { get; set; }

    [JsonPropertyName("gameType")]
    public string GameType { get; set; }

    [JsonPropertyName("season")]
    public string Season { get; set; }

    [JsonPropertyName("gameDate")]
    public DateTime GameDate { get; set; }

    [JsonPropertyName("officialDate")]
    public string OfficialDate { get; set; }

    [JsonPropertyName("status")]
    public Status Status { get; set; }

    [JsonPropertyName("teams")]
    public Teams Teams { get; set; }

    [JsonPropertyName("venue")]
    public Venue Venue { get; set; }

    [JsonPropertyName("content")]
    public Content Content { get; set; }

    [JsonPropertyName("isTie")]
    public bool IsTie { get; set; }

    [JsonPropertyName("gameNumber")]
    public int GameNumber { get; set; }

    [JsonPropertyName("publicFacing")]
    public bool PublicFacing { get; set; }

    [JsonPropertyName("doubleHeader")]
    public string DoubleHeader { get; set; }

    [JsonPropertyName("gamedayType")]
    public string GamedayType { get; set; }

    [JsonPropertyName("tiebreaker")]
    public string Tiebreaker { get; set; }

    [JsonPropertyName("calendarEventID")]
    public string CalendarEventID { get; set; }

    [JsonPropertyName("seasonDisplay")]
    public string SeasonDisplay { get; set; }

    [JsonPropertyName("dayNight")]
    public string DayNight { get; set; }

    [JsonPropertyName("scheduledInnings")]
    public int ScheduledInnings { get; set; }

    [JsonPropertyName("reverseHomeAwayStatus")]
    public bool ReverseHomeAwayStatus { get; set; }

    [JsonPropertyName("inningBreakLength")]
    public int InningBreakLength { get; set; }

    [JsonPropertyName("gamesInSeries")]
    public int GamesInSeries { get; set; }

    [JsonPropertyName("seriesGameNumber")]
    public int SeriesGameNumber { get; set; }

    [JsonPropertyName("seriesDescription")]
    public string SeriesDescription { get; set; }

    [JsonPropertyName("recordSource")]
    public string RecordSource { get; set; }

    [JsonPropertyName("ifNecessary")]
    public string IfNecessary { get; set; }

    [JsonPropertyName("ifNecessaryDescription")]
    public string IfNecessaryDescription { get; set; }
}

public class Status
{
    [JsonPropertyName("abstractGameState")]
    public string AbstractGameState { get; set; }

    [JsonPropertyName("codedGameState")]
    public string CodedGameState { get; set; }

    [JsonPropertyName("detailedState")]
    public string DetailedState { get; set; }

    [JsonPropertyName("statusCode")]
    public string StatusCode { get; set; }

    [JsonPropertyName("startTimeTBD")]
    public bool StartTimeTBD { get; set; }

    [JsonPropertyName("abstractGameCode")]
    public string AbstractGameCode { get; set; }
}

public class Teams
{
    [JsonPropertyName("away")]
    public TeamInfo Away { get; set; }

    [JsonPropertyName("home")]
    public TeamInfo Home { get; set; }
}

public class TeamInfo
{
    [JsonPropertyName("leagueRecord")]
    public LeagueRecord LeagueRecord { get; set; }

    [JsonPropertyName("score")]
    public int Score { get; set; }

    [JsonPropertyName("team")]
    public Team Team { get; set; }

    [JsonPropertyName("isWinner")]
    public bool IsWinner { get; set; }

    [JsonPropertyName("splitSquad")]
    public bool SplitSquad { get; set; }

    [JsonPropertyName("seriesNumber")]
    public int SeriesNumber { get; set; }
}

public class LeagueRecord
{
    [JsonPropertyName("wins")]
    public int Wins { get; set; }

    [JsonPropertyName("losses")]
    public int Losses { get; set; }

    [JsonPropertyName("pct")]
    public string Pct { get; set; }
}

public class Team
{
    [JsonPropertyName("id")]
    public int Id { get; set; }

    [JsonPropertyName("name")]
    public string Name { get; set; }

    [JsonPropertyName("link")]
    public string Link { get; set; }
}

public class Venue
{
    [JsonPropertyName("id")]
    public int Id { get; set; }

    [JsonPropertyName("name")]
    public string Name { get; set; }

    [JsonPropertyName("link")]
    public string Link { get; set; }
}

public class Content
{
    [JsonPropertyName("link")]
    public string Link { get; set; }
}

