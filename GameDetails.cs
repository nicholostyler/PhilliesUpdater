// Root myDeserializedClass = JsonSerializer.Deserialize<Root>(myJsonResponse);

using System.Text.Json.Serialization;

public class About
    {
        [JsonPropertyName("atBatIndex")]
        public int AtBatIndex { get; set; }

        [JsonPropertyName("halfInning")]
        public string HalfInning { get; set; }

        [JsonPropertyName("isTopInning")]
        public bool IsTopInning { get; set; }

        [JsonPropertyName("inning")]
        public int Inning { get; set; }

        [JsonPropertyName("startTime")]
        public DateTime StartTime { get; set; }

        [JsonPropertyName("endTime")]
        public DateTime EndTime { get; set; }

        [JsonPropertyName("isComplete")]
        public bool IsComplete { get; set; }

        [JsonPropertyName("isScoringPlay")]
        public bool IsScoringPlay { get; set; }

        [JsonPropertyName("hasReview")]
        public bool HasReview { get; set; }

        [JsonPropertyName("hasOut")]
        public bool HasOut { get; set; }

        [JsonPropertyName("captivatingIndex")]
        public int CaptivatingIndex { get; set; }
    }

    public class AllPlay
    {
        [JsonPropertyName("result")]
        public Result Result { get; set; }

        [JsonPropertyName("about")]
        public About About { get; set; }

        [JsonPropertyName("count")]
        public Count Count { get; set; }

        [JsonPropertyName("matchup")]
        public Matchup Matchup { get; set; }

        [JsonPropertyName("pitchIndex")]
        public List<int> PitchIndex { get; set; }

        [JsonPropertyName("actionIndex")]
        public List<int> ActionIndex { get; set; }

        [JsonPropertyName("runnerIndex")]
        public List<int> RunnerIndex { get; set; }

        [JsonPropertyName("runners")]
        public List<Runner> Runners { get; set; }

        [JsonPropertyName("playEvents")]
        public List<PlayEvent> PlayEvents { get; set; }

        [JsonPropertyName("playEndTime")]
        public DateTime PlayEndTime { get; set; }

        [JsonPropertyName("atBatIndex")]
        public int AtBatIndex { get; set; }
    }

    public class AllPosition
    {
        [JsonPropertyName("code")]
        public string Code { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("abbreviation")]
        public string Abbreviation { get; set; }
    }

    public class Away
    {
        [JsonPropertyName("springLeague")]
        public SpringLeague SpringLeague { get; set; }

        [JsonPropertyName("allStarStatus")]
        public string AllStarStatus { get; set; }

        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }

        [JsonPropertyName("season")]
        public int Season { get; set; }

        [JsonPropertyName("venue")]
        public Venue Venue { get; set; }

        [JsonPropertyName("springVenue")]
        public SpringVenue SpringVenue { get; set; }

        [JsonPropertyName("teamCode")]
        public string TeamCode { get; set; }

        [JsonPropertyName("fileCode")]
        public string FileCode { get; set; }

        [JsonPropertyName("abbreviation")]
        public string Abbreviation { get; set; }

        [JsonPropertyName("teamName")]
        public string TeamName { get; set; }

        [JsonPropertyName("locationName")]
        public string LocationName { get; set; }

        [JsonPropertyName("firstYearOfPlay")]
        public string FirstYearOfPlay { get; set; }

        [JsonPropertyName("league")]
        public League League { get; set; }

        [JsonPropertyName("division")]
        public Division Division { get; set; }

        [JsonPropertyName("sport")]
        public Sport Sport { get; set; }

        [JsonPropertyName("shortName")]
        public string ShortName { get; set; }

        [JsonPropertyName("record")]
        public Record Record { get; set; }

        [JsonPropertyName("franchiseName")]
        public string FranchiseName { get; set; }

        [JsonPropertyName("clubName")]
        public string ClubName { get; set; }

        [JsonPropertyName("active")]
        public bool Active { get; set; }

        [JsonPropertyName("used")]
        public int Used { get; set; }

        [JsonPropertyName("remaining")]
        public int Remaining { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("team")]
        public Team Team { get; set; }

        [JsonPropertyName("inning")]
        public int Inning { get; set; }

        [JsonPropertyName("pitcher")]
        public Pitcher Pitcher { get; set; }

        [JsonPropertyName("batter")]
        public Batter Batter { get; set; }

        [JsonPropertyName("coordinates")]
        public Coordinates Coordinates { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }

        [JsonPropertyName("runs")]
        public int Runs { get; set; }

        [JsonPropertyName("hits")]
        public int Hits { get; set; }

        [JsonPropertyName("errors")]
        public int Errors { get; set; }

        [JsonPropertyName("leftOnBase")]
        public int LeftOnBase { get; set; }

        [JsonPropertyName("teamStats")]
        public TeamStats TeamStats { get; set; }

        [JsonPropertyName("players")]
        public Players Players { get; set; }

        [JsonPropertyName("batters")]
        public List<int> Batters { get; set; }

        [JsonPropertyName("pitchers")]
        public List<int> Pitchers { get; set; }

        [JsonPropertyName("bench")]
        public List<int> Bench { get; set; }

        [JsonPropertyName("bullpen")]
        public List<int> Bullpen { get; set; }

        [JsonPropertyName("battingOrder")]
        public List<int> BattingOrder { get; set; }

        [JsonPropertyName("info")]
        public List<Info> Info { get; set; }

        [JsonPropertyName("note")]
        public List<object> Note { get; set; }
    }

    public class BatSide
    {
        [JsonPropertyName("code")]
        public string Code { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }
    }

    public class Batter
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class BatterHotColdZone
    {
        [JsonPropertyName("zone")]
        public string Zone { get; set; }

        [JsonPropertyName("color")]
        public string Color { get; set; }

        [JsonPropertyName("temp")]
        public string Temp { get; set; }

        [JsonPropertyName("value")]
        public string Value { get; set; }
    }

    public class BatterHotColdZoneStats
    {
        [JsonPropertyName("stats")]
        public List<Stat> Stats { get; set; }
    }

    public class Batting
    {
        [JsonPropertyName("flyOuts")]
        public int FlyOuts { get; set; }

        [JsonPropertyName("groundOuts")]
        public int GroundOuts { get; set; }

        [JsonPropertyName("airOuts")]
        public int AirOuts { get; set; }

        [JsonPropertyName("runs")]
        public int Runs { get; set; }

        [JsonPropertyName("doubles")]
        public int Doubles { get; set; }

        [JsonPropertyName("triples")]
        public int Triples { get; set; }

        [JsonPropertyName("homeRuns")]
        public int HomeRuns { get; set; }

        [JsonPropertyName("strikeOuts")]
        public int StrikeOuts { get; set; }

        [JsonPropertyName("baseOnBalls")]
        public int BaseOnBalls { get; set; }

        [JsonPropertyName("intentionalWalks")]
        public int IntentionalWalks { get; set; }

        [JsonPropertyName("hits")]
        public int Hits { get; set; }

        [JsonPropertyName("hitByPitch")]
        public int HitByPitch { get; set; }

        [JsonPropertyName("avg")]
        public string Avg { get; set; }

        [JsonPropertyName("atBats")]
        public int AtBats { get; set; }

        [JsonPropertyName("obp")]
        public string Obp { get; set; }

        [JsonPropertyName("slg")]
        public string Slg { get; set; }

        [JsonPropertyName("ops")]
        public string Ops { get; set; }

        [JsonPropertyName("caughtStealing")]
        public int CaughtStealing { get; set; }

        [JsonPropertyName("stolenBases")]
        public int StolenBases { get; set; }

        [JsonPropertyName("stolenBasePercentage")]
        public string StolenBasePercentage { get; set; }

        [JsonPropertyName("groundIntoDoublePlay")]
        public int GroundIntoDoublePlay { get; set; }

        [JsonPropertyName("groundIntoTriplePlay")]
        public int GroundIntoTriplePlay { get; set; }

        [JsonPropertyName("plateAppearances")]
        public int PlateAppearances { get; set; }

        [JsonPropertyName("totalBases")]
        public int TotalBases { get; set; }

        [JsonPropertyName("rbi")]
        public int Rbi { get; set; }

        [JsonPropertyName("leftOnBase")]
        public int LeftOnBase { get; set; }

        [JsonPropertyName("sacBunts")]
        public int SacBunts { get; set; }

        [JsonPropertyName("sacFlies")]
        public int SacFlies { get; set; }

        [JsonPropertyName("catchersInterference")]
        public int CatchersInterference { get; set; }

        [JsonPropertyName("pickoffs")]
        public int Pickoffs { get; set; }

        [JsonPropertyName("atBatsPerHomeRun")]
        public string AtBatsPerHomeRun { get; set; }

        [JsonPropertyName("popOuts")]
        public int PopOuts { get; set; }

        [JsonPropertyName("lineOuts")]
        public int LineOuts { get; set; }

        [JsonPropertyName("summary")]
        public string Summary { get; set; }

        [JsonPropertyName("gamesPlayed")]
        public int GamesPlayed { get; set; }

        [JsonPropertyName("babip")]
        public string Babip { get; set; }

        [JsonPropertyName("groundOutsToAirouts")]
        public string GroundOutsToAirouts { get; set; }

        [JsonPropertyName("note")]
        public string Note { get; set; }
    }

    public class Boxscore
    {
        [JsonPropertyName("teams")]
        public Teams Teams { get; set; }

        [JsonPropertyName("officials")]
        public List<Official> Officials { get; set; }

        [JsonPropertyName("info")]
        public List<Info> Info { get; set; }

        [JsonPropertyName("pitchingNotes")]
        public List<object> PitchingNotes { get; set; }

        [JsonPropertyName("topPerformers")]
        public List<TopPerformer> TopPerformers { get; set; }
    }

    public class Breaks
    {
        [JsonPropertyName("breakAngle")]
        public double BreakAngle { get; set; }

        [JsonPropertyName("breakLength")]
        public double BreakLength { get; set; }

        [JsonPropertyName("breakY")]
        public double BreakY { get; set; }

        [JsonPropertyName("breakVertical")]
        public double BreakVertical { get; set; }

        [JsonPropertyName("breakVerticalInduced")]
        public double BreakVerticalInduced { get; set; }

        [JsonPropertyName("breakHorizontal")]
        public double BreakHorizontal { get; set; }

        [JsonPropertyName("spinRate")]
        public int SpinRate { get; set; }

        [JsonPropertyName("spinDirection")]
        public int SpinDirection { get; set; }
    }

    public class Call
    {
        [JsonPropertyName("code")]
        public string Code { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }
    }

    public class Catcher
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Center
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Coordinates
    {
        [JsonPropertyName("aY")]
        public double AY { get; set; }

        [JsonPropertyName("aZ")]
        public double AZ { get; set; }

        [JsonPropertyName("pfxX")]
        public double PfxX { get; set; }

        [JsonPropertyName("pfxZ")]
        public double PfxZ { get; set; }

        [JsonPropertyName("pX")]
        public double PX { get; set; }

        [JsonPropertyName("pZ")]
        public double PZ { get; set; }

        [JsonPropertyName("vX0")]
        public double VX0 { get; set; }

        [JsonPropertyName("vY0")]
        public double VY0 { get; set; }

        [JsonPropertyName("vZ0")]
        public double VZ0 { get; set; }

        [JsonPropertyName("x")]
        public double X { get; set; }

        [JsonPropertyName("y")]
        public double Y { get; set; }

        [JsonPropertyName("x0")]
        public double X0 { get; set; }

        [JsonPropertyName("y0")]
        public double Y0 { get; set; }

        [JsonPropertyName("z0")]
        public double Z0 { get; set; }

        [JsonPropertyName("aX")]
        public double AX { get; set; }

        [JsonPropertyName("coordX")]
        public double CoordX { get; set; }

        [JsonPropertyName("coordY")]
        public double CoordY { get; set; }
    }

    public class Count
    {
        [JsonPropertyName("balls")]
        public int Balls { get; set; }

        [JsonPropertyName("strikes")]
        public int Strikes { get; set; }

        [JsonPropertyName("outs")]
        public int Outs { get; set; }
    }

    public class Credit
    {
        [JsonPropertyName("player")]
        public Player Player { get; set; }

        [JsonPropertyName("position")]
        public Position Position { get; set; }

        [JsonPropertyName("credit")]
        public string GameCredit { get; set; }
    }

    public class CurrentPlay
    {
        [JsonPropertyName("result")]
        public Result Result { get; set; }

        [JsonPropertyName("about")]
        public About About { get; set; }

        [JsonPropertyName("count")]
        public Count Count { get; set; }

        [JsonPropertyName("matchup")]
        public Matchup Matchup { get; set; }

        [JsonPropertyName("pitchIndex")]
        public List<int> PitchIndex { get; set; }

        [JsonPropertyName("actionIndex")]
        public List<object> ActionIndex { get; set; }

        [JsonPropertyName("runnerIndex")]
        public List<int> RunnerIndex { get; set; }

        [JsonPropertyName("runners")]
        public List<Runner> Runners { get; set; }

        [JsonPropertyName("playEvents")]
        public List<PlayEvent> PlayEvents { get; set; }

        [JsonPropertyName("playEndTime")]
        public DateTime PlayEndTime { get; set; }

        [JsonPropertyName("atBatIndex")]
        public int AtBatIndex { get; set; }
    }

    public class Datetime
    {
        [JsonPropertyName("dateTime")]
        public DateTime DateTime { get; set; }

        [JsonPropertyName("originalDate")]
        public string OriginalDate { get; set; }

        [JsonPropertyName("officialDate")]
        public string OfficialDate { get; set; }

        [JsonPropertyName("dayNight")]
        public string DayNight { get; set; }

        [JsonPropertyName("time")]
        public string Time { get; set; }

        [JsonPropertyName("ampm")]
        public string Ampm { get; set; }
    }

    public class Decisions
    {
        [JsonPropertyName("winner")]
        public Winner Winner { get; set; }

        [JsonPropertyName("loser")]
        public Loser Loser { get; set; }

        [JsonPropertyName("save")]
        public Save Save { get; set; }
    }

    public class DefaultCoordinates
    {
        [JsonPropertyName("latitude")]
        public double Latitude { get; set; }

        [JsonPropertyName("longitude")]
        public double Longitude { get; set; }
    }

    public class Defense
    {
        [JsonPropertyName("pitcher")]
        public Pitcher Pitcher { get; set; }

        [JsonPropertyName("catcher")]
        public Catcher Catcher { get; set; }

        [JsonPropertyName("first")]
        public First First { get; set; }

        [JsonPropertyName("second")]
        public Second Second { get; set; }

        [JsonPropertyName("third")]
        public Third Third { get; set; }

        [JsonPropertyName("shortstop")]
        public Shortstop Shortstop { get; set; }

        [JsonPropertyName("left")]
        public Left Left { get; set; }

        [JsonPropertyName("center")]
        public Center Center { get; set; }

        [JsonPropertyName("right")]
        public Right Right { get; set; }

        [JsonPropertyName("batter")]
        public Batter Batter { get; set; }

        [JsonPropertyName("onDeck")]
        public OnDeck OnDeck { get; set; }

        [JsonPropertyName("inHole")]
        public InHole InHole { get; set; }

        [JsonPropertyName("battingOrder")]
        public int BattingOrder { get; set; }

        [JsonPropertyName("team")]
        public Team Team { get; set; }
    }

    public class Details
    {
        [JsonPropertyName("event")]
        public string Event { get; set; }

        [JsonPropertyName("eventType")]
        public string EventType { get; set; }

        [JsonPropertyName("movementReason")]
        public string MovementReason { get; set; }

        [JsonPropertyName("runner")]
        public Runner Runner { get; set; }

        [JsonPropertyName("responsiblePitcher")]
        public ResponsiblePitcher ResponsiblePitcher { get; set; }

        [JsonPropertyName("isScoringEvent")]
        public bool IsScoringEvent { get; set; }

        [JsonPropertyName("rbi")]
        public bool Rbi { get; set; }

        [JsonPropertyName("earned")]
        public bool Earned { get; set; }

        [JsonPropertyName("teamUnearned")]
        public bool TeamUnearned { get; set; }

        [JsonPropertyName("playIndex")]
        public int PlayIndex { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }

        [JsonPropertyName("awayScore")]
        public int AwayScore { get; set; }

        [JsonPropertyName("homeScore")]
        public int HomeScore { get; set; }

        [JsonPropertyName("isScoringPlay")]
        public bool IsScoringPlay { get; set; }

        [JsonPropertyName("isOut")]
        public bool IsOut { get; set; }

        [JsonPropertyName("hasReview")]
        public bool HasReview { get; set; }

        [JsonPropertyName("call")]
        public Call Call { get; set; }

        [JsonPropertyName("code")]
        public string Code { get; set; }

        [JsonPropertyName("ballColor")]
        public string BallColor { get; set; }

        [JsonPropertyName("trailColor")]
        public string TrailColor { get; set; }

        [JsonPropertyName("isInPlay")]
        public bool? IsInPlay { get; set; }

        [JsonPropertyName("isStrike")]
        public bool? IsStrike { get; set; }

        [JsonPropertyName("isBall")]
        public bool? IsBall { get; set; }

        [JsonPropertyName("type")]
        public Type Type { get; set; }

        [JsonPropertyName("fromCatcher")]
        public bool? FromCatcher { get; set; }

        [JsonPropertyName("disengagementNum")]
        public int? DisengagementNum { get; set; }

        [JsonPropertyName("runnerGoing")]
        public bool? RunnerGoing { get; set; }
    }

    public class Division
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class FieldInfo
    {
        [JsonPropertyName("capacity")]
        public int Capacity { get; set; }

        [JsonPropertyName("turfType")]
        public string TurfType { get; set; }

        [JsonPropertyName("roofType")]
        public string RoofType { get; set; }

        [JsonPropertyName("leftLine")]
        public int LeftLine { get; set; }

        [JsonPropertyName("left")]
        public int Left { get; set; }

        [JsonPropertyName("leftCenter")]
        public int LeftCenter { get; set; }

        [JsonPropertyName("center")]
        public int Center { get; set; }

        [JsonPropertyName("rightCenter")]
        public int RightCenter { get; set; }

        [JsonPropertyName("rightLine")]
        public int RightLine { get; set; }
    }

    public class Fielding
    {
        [JsonPropertyName("caughtStealing")]
        public int CaughtStealing { get; set; }

        [JsonPropertyName("stolenBases")]
        public int StolenBases { get; set; }

        [JsonPropertyName("stolenBasePercentage")]
        public string StolenBasePercentage { get; set; }

        [JsonPropertyName("assists")]
        public int Assists { get; set; }

        [JsonPropertyName("putOuts")]
        public int PutOuts { get; set; }

        [JsonPropertyName("errors")]
        public int Errors { get; set; }

        [JsonPropertyName("chances")]
        public int Chances { get; set; }

        [JsonPropertyName("passedBall")]
        public int PassedBall { get; set; }

        [JsonPropertyName("pickoffs")]
        public int Pickoffs { get; set; }

        [JsonPropertyName("gamesStarted")]
        public int GamesStarted { get; set; }

        [JsonPropertyName("fielding")]
        public string GameFielding { get; set; }
    }

    public class FieldList
    {
        [JsonPropertyName("label")]
        public string Label { get; set; }

        [JsonPropertyName("value")]
        public string Value { get; set; }
    }

    public class First
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Flags
    {
        [JsonPropertyName("noHitter")]
        public bool NoHitter { get; set; }

        [JsonPropertyName("perfectGame")]
        public bool PerfectGame { get; set; }

        [JsonPropertyName("awayTeamNoHitter")]
        public bool AwayTeamNoHitter { get; set; }

        [JsonPropertyName("awayTeamPerfectGame")]
        public bool AwayTeamPerfectGame { get; set; }

        [JsonPropertyName("homeTeamNoHitter")]
        public bool HomeTeamNoHitter { get; set; }

        [JsonPropertyName("homeTeamPerfectGame")]
        public bool HomeTeamPerfectGame { get; set; }
    }

    public class DetailGame
    {
        [JsonPropertyName("pk")]
        public int Pk { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("doubleHeader")]
        public string DoubleHeader { get; set; }

        [JsonPropertyName("id")]
        public string Id { get; set; }

        [JsonPropertyName("gamedayType")]
        public string GamedayType { get; set; }

        [JsonPropertyName("tiebreaker")]
        public string Tiebreaker { get; set; }

        [JsonPropertyName("gameNumber")]
        public int GameNumber { get; set; }

        [JsonPropertyName("calendarEventID")]
        public string CalendarEventID { get; set; }

        [JsonPropertyName("season")]
        public string Season { get; set; }

        [JsonPropertyName("seasonDisplay")]
        public string SeasonDisplay { get; set; }
    }

    public class GameData
    {
        [JsonPropertyName("game")]
        public DetailGame Game { get; set; }

        [JsonPropertyName("datetime")]
        public Datetime Datetime { get; set; }

        [JsonPropertyName("status")]
        public Status Status { get; set; }

        [JsonPropertyName("teams")]
        public Teams Teams { get; set; }

        [JsonPropertyName("players")]
        public Players Players { get; set; }

        [JsonPropertyName("venue")]
        public Venue Venue { get; set; }

        [JsonPropertyName("officialVenue")]
        public OfficialVenue OfficialVenue { get; set; }

        [JsonPropertyName("weather")]
        public Weather Weather { get; set; }

        [JsonPropertyName("gameInfo")]
        public GameInfo GameInfo { get; set; }

        [JsonPropertyName("review")]
        public Review Review { get; set; }

        [JsonPropertyName("flags")]
        public Flags Flags { get; set; }

        [JsonPropertyName("alerts")]
        public List<object> Alerts { get; set; }

        [JsonPropertyName("probablePitchers")]
        public ProbablePitchers ProbablePitchers { get; set; }

        [JsonPropertyName("officialScorer")]
        public OfficialScorer OfficialScorer { get; set; }

        [JsonPropertyName("primaryDatacaster")]
        public PrimaryDatacaster PrimaryDatacaster { get; set; }

        [JsonPropertyName("moundVisits")]
        public MoundVisits MoundVisits { get; set; }
    }

    public class GameInfo
    {
        [JsonPropertyName("attendance")]
        public int Attendance { get; set; }

        [JsonPropertyName("firstPitch")]
        public DateTime FirstPitch { get; set; }

        [JsonPropertyName("gameDurationMinutes")]
        public int GameDurationMinutes { get; set; }
    }

    public class GameStatus
    {
        [JsonPropertyName("isCurrentBatter")]
        public bool IsCurrentBatter { get; set; }

        [JsonPropertyName("isCurrentPitcher")]
        public bool IsCurrentPitcher { get; set; }

        [JsonPropertyName("isOnBench")]
        public bool IsOnBench { get; set; }

        [JsonPropertyName("isSubstitute")]
        public bool IsSubstitute { get; set; }
    }

    public class Group
    {
        [JsonPropertyName("displayName")]
        public string DisplayName { get; set; }
    }

    public class HitData
    {
        [JsonPropertyName("launchSpeed")]
        public double LaunchSpeed { get; set; }

        [JsonPropertyName("launchAngle")]
        public double LaunchAngle { get; set; }

        [JsonPropertyName("totalDistance")]
        public double TotalDistance { get; set; }

        [JsonPropertyName("trajectory")]
        public string Trajectory { get; set; }

        [JsonPropertyName("hardness")]
        public string Hardness { get; set; }

        [JsonPropertyName("location")]
        public string Location { get; set; }

        [JsonPropertyName("coordinates")]
        public Coordinates Coordinates { get; set; }
    }

    public class HitDistance
    {
    }

    public class Hits
    {
        [JsonPropertyName("away")]
        public List<Away> Away { get; set; }

        [JsonPropertyName("home")]
        public List<Home> Home { get; set; }
    }

    public class HitSpeed
    {
    }

    public class Home
    {
        [JsonPropertyName("springLeague")]
        public SpringLeague SpringLeague { get; set; }

        [JsonPropertyName("allStarStatus")]
        public string AllStarStatus { get; set; }

        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }

        [JsonPropertyName("season")]
        public int Season { get; set; }

        [JsonPropertyName("venue")]
        public Venue Venue { get; set; }

        [JsonPropertyName("springVenue")]
        public SpringVenue SpringVenue { get; set; }

        [JsonPropertyName("teamCode")]
        public string TeamCode { get; set; }

        [JsonPropertyName("fileCode")]
        public string FileCode { get; set; }

        [JsonPropertyName("abbreviation")]
        public string Abbreviation { get; set; }

        [JsonPropertyName("teamName")]
        public string TeamName { get; set; }

        [JsonPropertyName("locationName")]
        public string LocationName { get; set; }

        [JsonPropertyName("firstYearOfPlay")]
        public string FirstYearOfPlay { get; set; }

        [JsonPropertyName("league")]
        public League League { get; set; }

        [JsonPropertyName("division")]
        public Division Division { get; set; }

        [JsonPropertyName("sport")]
        public Sport Sport { get; set; }

        [JsonPropertyName("shortName")]
        public string ShortName { get; set; }

        [JsonPropertyName("record")]
        public Record Record { get; set; }

        [JsonPropertyName("franchiseName")]
        public string FranchiseName { get; set; }

        [JsonPropertyName("clubName")]
        public string ClubName { get; set; }

        [JsonPropertyName("active")]
        public bool Active { get; set; }

        [JsonPropertyName("used")]
        public int Used { get; set; }

        [JsonPropertyName("remaining")]
        public int Remaining { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("team")]
        public Team Team { get; set; }

        [JsonPropertyName("inning")]
        public int Inning { get; set; }

        [JsonPropertyName("pitcher")]
        public Pitcher Pitcher { get; set; }

        [JsonPropertyName("batter")]
        public Batter Batter { get; set; }

        [JsonPropertyName("coordinates")]
        public Coordinates Coordinates { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }

        [JsonPropertyName("runs")]
        public int Runs { get; set; }

        [JsonPropertyName("hits")]
        public int Hits { get; set; }

        [JsonPropertyName("errors")]
        public int Errors { get; set; }

        [JsonPropertyName("leftOnBase")]
        public int LeftOnBase { get; set; }

        [JsonPropertyName("teamStats")]
        public TeamStats TeamStats { get; set; }

        [JsonPropertyName("players")]
        public Players Players { get; set; }

        [JsonPropertyName("batters")]
        public List<int> Batters { get; set; }

        [JsonPropertyName("pitchers")]
        public List<int> Pitchers { get; set; }

        [JsonPropertyName("bench")]
        public List<int> Bench { get; set; }

        [JsonPropertyName("bullpen")]
        public List<int> Bullpen { get; set; }

        [JsonPropertyName("battingOrder")]
        public List<int> BattingOrder { get; set; }

        [JsonPropertyName("info")]
        public List<Info> Info { get; set; }

        [JsonPropertyName("note")]
        public List<Note> Note { get; set; }
    }


    public class Info
    {
        [JsonPropertyName("title")]
        public string Title { get; set; }

        [JsonPropertyName("fieldList")]
        public List<FieldList> FieldList { get; set; }

        [JsonPropertyName("label")]
        public string Label { get; set; }

        [JsonPropertyName("value")]
        public string Value { get; set; }
    }

    public class InHole
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Inning
    {
        [JsonPropertyName("num")]
        public int Num { get; set; }

        [JsonPropertyName("ordinalNum")]
        public string OrdinalNum { get; set; }

        [JsonPropertyName("home")]
        public Home Home { get; set; }

        [JsonPropertyName("away")]
        public Away Away { get; set; }
    }

    public class Leaders
    {
        [JsonPropertyName("hitDistance")]
        public HitDistance HitDistance { get; set; }

        [JsonPropertyName("hitSpeed")]
        public HitSpeed HitSpeed { get; set; }

        [JsonPropertyName("pitchSpeed")]
        public PitchSpeed PitchSpeed { get; set; }
    }

    public class League
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class LeagueRecord
    {
        [JsonPropertyName("wins")]
        public int Wins { get; set; }

        [JsonPropertyName("losses")]
        public int Losses { get; set; }

        [JsonPropertyName("ties")]
        public int Ties { get; set; }

        [JsonPropertyName("pct")]
        public string Pct { get; set; }
    }

    public class Left
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Linescore
    {
        [JsonPropertyName("currentInning")]
        public int CurrentInning { get; set; }

        [JsonPropertyName("currentInningOrdinal")]
        public string CurrentInningOrdinal { get; set; }

        [JsonPropertyName("inningState")]
        public string InningState { get; set; }

        [JsonPropertyName("inningHalf")]
        public string InningHalf { get; set; }

        [JsonPropertyName("isTopInning")]
        public bool IsTopInning { get; set; }

        [JsonPropertyName("scheduledInnings")]
        public int ScheduledInnings { get; set; }

        [JsonPropertyName("innings")]
        public List<Inning> Innings { get; set; }

        [JsonPropertyName("teams")]
        public Teams Teams { get; set; }

        [JsonPropertyName("defense")]
        public Defense Defense { get; set; }

        [JsonPropertyName("offense")]
        public Offense Offense { get; set; }

        [JsonPropertyName("balls")]
        public int Balls { get; set; }

        [JsonPropertyName("strikes")]
        public int Strikes { get; set; }

        [JsonPropertyName("outs")]
        public int Outs { get; set; }
    }

    public class LiveData
    {
        [JsonPropertyName("plays")]
        public Plays Plays { get; set; }

        [JsonPropertyName("linescore")]
        public Linescore Linescore { get; set; }

        [JsonPropertyName("boxscore")]
        public Boxscore Boxscore { get; set; }

        [JsonPropertyName("decisions")]
        public Decisions Decisions { get; set; }

        [JsonPropertyName("leaders")]
        public Leaders Leaders { get; set; }
    }

    public class Location
    {
        [JsonPropertyName("address1")]
        public string Address1 { get; set; }

        [JsonPropertyName("city")]
        public string City { get; set; }

        [JsonPropertyName("state")]
        public string State { get; set; }

        [JsonPropertyName("stateAbbrev")]
        public string StateAbbrev { get; set; }

        [JsonPropertyName("postalCode")]
        public string PostalCode { get; set; }

        [JsonPropertyName("defaultCoordinates")]
        public DefaultCoordinates DefaultCoordinates { get; set; }

        [JsonPropertyName("azimuthAngle")]
        public double AzimuthAngle { get; set; }

        [JsonPropertyName("elevation")]
        public int Elevation { get; set; }

        [JsonPropertyName("country")]
        public string Country { get; set; }

        [JsonPropertyName("phone")]
        public string Phone { get; set; }
    }

    public class Loser
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Matchup
    {
        [JsonPropertyName("batter")]
        public Batter Batter { get; set; }

        [JsonPropertyName("batSide")]
        public BatSide BatSide { get; set; }

        [JsonPropertyName("pitcher")]
        public Pitcher Pitcher { get; set; }

        [JsonPropertyName("pitchHand")]
        public PitchHand PitchHand { get; set; }

        [JsonPropertyName("postOnFirst")]
        public PostOnFirst PostOnFirst { get; set; }

        [JsonPropertyName("batterHotColdZones")]
        public List<object> BatterHotColdZones { get; set; }

        [JsonPropertyName("pitcherHotColdZones")]
        public List<object> PitcherHotColdZones { get; set; }
        

        [JsonPropertyName("postOnSecond")]
        public PostOnSecond PostOnSecond { get; set; }

        [JsonPropertyName("batterHotColdZoneStats")]
        public BatterHotColdZoneStats BatterHotColdZoneStats { get; set; }
    }

    public class MetaData
    {
        [JsonPropertyName("wait")]
        public int Wait { get; set; }

        [JsonPropertyName("timeStamp")]
        public string TimeStamp { get; set; }

        [JsonPropertyName("gameEvents")]
        public List<string> GameEvents { get; set; }

        [JsonPropertyName("logicalEvents")]
        public List<string> LogicalEvents { get; set; }
    }

    public class MoundVisits
    {
        [JsonPropertyName("away")]
        public Away Away { get; set; }

        [JsonPropertyName("home")]
        public Home Home { get; set; }
    }

    public class Movement
    {
        [JsonPropertyName("originBase")]
        public string OriginBase { get; set; }

        [JsonPropertyName("start")]
        public string Start { get; set; }

        [JsonPropertyName("end")]
        public string End { get; set; }

        [JsonPropertyName("outBase")]
        public string OutBase { get; set; }

        [JsonPropertyName("isOut")]
        public bool IsOut { get; set; }

        [JsonPropertyName("outNumber")]
        public int? OutNumber { get; set; }
    }

    public class Note
    {
        [JsonPropertyName("label")]
        public string Label { get; set; }

        [JsonPropertyName("value")]
        public string Value { get; set; }
    }

    public class Offense
    {
        [JsonPropertyName("batter")]
        public Batter Batter { get; set; }

        [JsonPropertyName("onDeck")]
        public OnDeck OnDeck { get; set; }

        [JsonPropertyName("inHole")]
        public InHole InHole { get; set; }

        [JsonPropertyName("pitcher")]
        public Pitcher Pitcher { get; set; }

        [JsonPropertyName("battingOrder")]
        public int BattingOrder { get; set; }

        [JsonPropertyName("team")]
        public Team Team { get; set; }
    }

    public class Official
    {
        [JsonPropertyName("official")]
        public Official GameOfficial { get; set; }

        [JsonPropertyName("officialType")]
        public string OfficialType { get; set; }
    }

    public class Official2
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class OfficialScorer
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class OfficialVenue
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class OnDeck
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Person
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }

        [JsonPropertyName("boxscoreName")]
        public string BoxscoreName { get; set; }
    }

    public class PitchData
    {
        [JsonPropertyName("startSpeed")]
        public double StartSpeed { get; set; }

        [JsonPropertyName("endSpeed")]
        public double EndSpeed { get; set; }

        [JsonPropertyName("strikeZoneTop")]
        public double StrikeZoneTop { get; set; }

        [JsonPropertyName("strikeZoneBottom")]
        public double StrikeZoneBottom { get; set; }

        [JsonPropertyName("coordinates")]
        public Coordinates Coordinates { get; set; }

        [JsonPropertyName("breaks")]
        public Breaks Breaks { get; set; }

        [JsonPropertyName("zone")]
        public int Zone { get; set; }

        [JsonPropertyName("typeConfidence")]
        public double TypeConfidence { get; set; }

        [JsonPropertyName("plateTime")]
        public double PlateTime { get; set; }

        [JsonPropertyName("extension")]
        public double Extension { get; set; }
    }

    public class Pitcher
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class PitchHand
    {
        [JsonPropertyName("code")]
        public string Code { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }
    }

    public class Pitching
    {
        [JsonPropertyName("flyOuts")]
        public int FlyOuts { get; set; }

        [JsonPropertyName("groundOuts")]
        public int GroundOuts { get; set; }

        [JsonPropertyName("airOuts")]
        public int AirOuts { get; set; }

        [JsonPropertyName("runs")]
        public int Runs { get; set; }

        [JsonPropertyName("doubles")]
        public int Doubles { get; set; }

        [JsonPropertyName("triples")]
        public int Triples { get; set; }

        [JsonPropertyName("homeRuns")]
        public int HomeRuns { get; set; }

        [JsonPropertyName("strikeOuts")]
        public int StrikeOuts { get; set; }

        [JsonPropertyName("baseOnBalls")]
        public int BaseOnBalls { get; set; }

        [JsonPropertyName("intentionalWalks")]
        public int IntentionalWalks { get; set; }

        [JsonPropertyName("hits")]
        public int Hits { get; set; }

        [JsonPropertyName("hitByPitch")]
        public int HitByPitch { get; set; }

        [JsonPropertyName("atBats")]
        public int AtBats { get; set; }

        [JsonPropertyName("obp")]
        public string Obp { get; set; }

        [JsonPropertyName("caughtStealing")]
        public int CaughtStealing { get; set; }

        [JsonPropertyName("stolenBases")]
        public int StolenBases { get; set; }

        [JsonPropertyName("stolenBasePercentage")]
        public string StolenBasePercentage { get; set; }

        [JsonPropertyName("numberOfPitches")]
        public int NumberOfPitches { get; set; }

        [JsonPropertyName("era")]
        public string Era { get; set; }

        [JsonPropertyName("inningsPitched")]
        public string InningsPitched { get; set; }

        [JsonPropertyName("saveOpportunities")]
        public int SaveOpportunities { get; set; }

        [JsonPropertyName("earnedRuns")]
        public int EarnedRuns { get; set; }

        [JsonPropertyName("whip")]
        public string Whip { get; set; }

        [JsonPropertyName("battersFaced")]
        public int BattersFaced { get; set; }

        [JsonPropertyName("outs")]
        public int Outs { get; set; }

        [JsonPropertyName("completeGames")]
        public int CompleteGames { get; set; }

        [JsonPropertyName("shutouts")]
        public int Shutouts { get; set; }

        [JsonPropertyName("pitchesThrown")]
        public int PitchesThrown { get; set; }

        [JsonPropertyName("balls")]
        public int Balls { get; set; }

        [JsonPropertyName("strikes")]
        public int Strikes { get; set; }

        [JsonPropertyName("strikePercentage")]
        public string StrikePercentage { get; set; }

        [JsonPropertyName("hitBatsmen")]
        public int HitBatsmen { get; set; }

        [JsonPropertyName("balks")]
        public int Balks { get; set; }

        [JsonPropertyName("wildPitches")]
        public int WildPitches { get; set; }

        [JsonPropertyName("pickoffs")]
        public int Pickoffs { get; set; }

        [JsonPropertyName("groundOutsToAirouts")]
        public string GroundOutsToAirouts { get; set; }

        [JsonPropertyName("rbi")]
        public int Rbi { get; set; }

        [JsonPropertyName("pitchesPerInning")]
        public string PitchesPerInning { get; set; }

        [JsonPropertyName("runsScoredPer9")]
        public string RunsScoredPer9 { get; set; }

        [JsonPropertyName("homeRunsPer9")]
        public string HomeRunsPer9 { get; set; }

        [JsonPropertyName("inheritedRunners")]
        public int InheritedRunners { get; set; }

        [JsonPropertyName("inheritedRunnersScored")]
        public int InheritedRunnersScored { get; set; }

        [JsonPropertyName("catchersInterference")]
        public int CatchersInterference { get; set; }

        [JsonPropertyName("sacBunts")]
        public int SacBunts { get; set; }

        [JsonPropertyName("sacFlies")]
        public int SacFlies { get; set; }

        [JsonPropertyName("passedBall")]
        public int PassedBall { get; set; }

        [JsonPropertyName("popOuts")]
        public int PopOuts { get; set; }

        [JsonPropertyName("lineOuts")]
        public int LineOuts { get; set; }

        [JsonPropertyName("gamesPlayed")]
        public int GamesPlayed { get; set; }

        [JsonPropertyName("gamesStarted")]
        public int GamesStarted { get; set; }

        [JsonPropertyName("wins")]
        public int Wins { get; set; }

        [JsonPropertyName("losses")]
        public int Losses { get; set; }

        [JsonPropertyName("saves")]
        public int Saves { get; set; }

        [JsonPropertyName("holds")]
        public int Holds { get; set; }

        [JsonPropertyName("blownSaves")]
        public int BlownSaves { get; set; }

        [JsonPropertyName("gamesPitched")]
        public int GamesPitched { get; set; }

        [JsonPropertyName("winPercentage")]
        public string WinPercentage { get; set; }

        [JsonPropertyName("gamesFinished")]
        public int GamesFinished { get; set; }

        [JsonPropertyName("strikeoutWalkRatio")]
        public string StrikeoutWalkRatio { get; set; }

        [JsonPropertyName("strikeoutsPer9Inn")]
        public string StrikeoutsPer9Inn { get; set; }

        [JsonPropertyName("walksPer9Inn")]
        public string WalksPer9Inn { get; set; }

        [JsonPropertyName("hitsPer9Inn")]
        public string HitsPer9Inn { get; set; }

        [JsonPropertyName("summary")]
        public string Summary { get; set; }

        [JsonPropertyName("note")]
        public string Note { get; set; }
    }

    public class PitchSpeed
    {
    }

    public class Player
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }

        [JsonPropertyName("person")]
        public Person Person { get; set; }

        [JsonPropertyName("jerseyNumber")]
        public string JerseyNumber { get; set; }

        [JsonPropertyName("position")]
        public Position Position { get; set; }

        [JsonPropertyName("status")]
        public Status Status { get; set; }

        [JsonPropertyName("parentTeamId")]
        public int ParentTeamId { get; set; }

        [JsonPropertyName("seasonStats")]
        public SeasonStats SeasonStats { get; set; }

        [JsonPropertyName("gameStatus")]
        public GameStatus GameStatus { get; set; }

        [JsonPropertyName("allPositions")]
        public List<AllPosition> AllPositions { get; set; }

        [JsonPropertyName("battingOrder")]
        public string BattingOrder { get; set; }
    }

    public class Players
    {
        
    }

    public class PlayEvent
    {
        [JsonPropertyName("details")]
        public Details Details { get; set; }

        [JsonPropertyName("count")]
        public Count Count { get; set; }

        [JsonPropertyName("index")]
        public int Index { get; set; }

        [JsonPropertyName("startTime")]
        public DateTime StartTime { get; set; }

        [JsonPropertyName("endTime")]
        public DateTime EndTime { get; set; }

        [JsonPropertyName("isPitch")]
        public bool IsPitch { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("player")]
        public Player Player { get; set; }

        [JsonPropertyName("pitchData")]
        public PitchData PitchData { get; set; }

        [JsonPropertyName("playId")]
        public string PlayId { get; set; }

        [JsonPropertyName("pitchNumber")]
        public int? PitchNumber { get; set; }

        [JsonPropertyName("hitData")]
        public HitData HitData { get; set; }

        [JsonPropertyName("isSubstitution")]
        public bool? IsSubstitution { get; set; }

        [JsonPropertyName("position")]
        public Position Position { get; set; }
    }

    public class Plays
    {
        [JsonPropertyName("allPlays")]
        public List<AllPlay> AllPlays { get; set; }

        [JsonPropertyName("currentPlay")]
        public CurrentPlay CurrentPlay { get; set; }

        [JsonPropertyName("scoringPlays")]
        public List<int> ScoringPlays { get; set; }

        [JsonPropertyName("playsByInning")]
        public List<PlaysByInning> PlaysByInning { get; set; }
    }

    public class PlaysByInning
    {
        [JsonPropertyName("startIndex")]
        public int StartIndex { get; set; }

        [JsonPropertyName("endIndex")]
        public int EndIndex { get; set; }

        [JsonPropertyName("top")]
        public List<int> Top { get; set; }

        [JsonPropertyName("bottom")]
        public List<int> Bottom { get; set; }

        [JsonPropertyName("hits")]
        public Hits Hits { get; set; }
    }

    public class Position
    {
        [JsonPropertyName("code")]
        public string Code { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("abbreviation")]
        public string Abbreviation { get; set; }
    }

    public class PostOnFirst
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class PostOnSecond
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class PrimaryDatacaster
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class PrimaryPosition
    {
        [JsonPropertyName("code")]
        public string Code { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("abbreviation")]
        public string Abbreviation { get; set; }
    }

    public class ProbablePitchers
    {
        [JsonPropertyName("away")]
        public Away Away { get; set; }

        [JsonPropertyName("home")]
        public Home Home { get; set; }
    }

    public class Record
    {
        [JsonPropertyName("gamesPlayed")]
        public int GamesPlayed { get; set; }

        [JsonPropertyName("wildCardGamesBack")]
        public string WildCardGamesBack { get; set; }

        [JsonPropertyName("leagueGamesBack")]
        public string LeagueGamesBack { get; set; }

        [JsonPropertyName("springLeagueGamesBack")]
        public string SpringLeagueGamesBack { get; set; }

        [JsonPropertyName("sportGamesBack")]
        public string SportGamesBack { get; set; }

        [JsonPropertyName("divisionGamesBack")]
        public string DivisionGamesBack { get; set; }

        [JsonPropertyName("conferenceGamesBack")]
        public string ConferenceGamesBack { get; set; }

        [JsonPropertyName("leagueRecord")]
        public LeagueRecord LeagueRecord { get; set; }

        [JsonPropertyName("records")]
        public Records Records { get; set; }

        [JsonPropertyName("divisionLeader")]
        public bool DivisionLeader { get; set; }

        [JsonPropertyName("wins")]
        public int Wins { get; set; }

        [JsonPropertyName("losses")]
        public int Losses { get; set; }

        [JsonPropertyName("winningPercentage")]
        public string WinningPercentage { get; set; }
    }

    public class Records
    {
    }

    public class ResponsiblePitcher
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Result
    {
        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("event")]
        public string Event { get; set; }

        [JsonPropertyName("eventType")]
        public string EventType { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }

        [JsonPropertyName("rbi")]
        public int Rbi { get; set; }

        [JsonPropertyName("awayScore")]
        public int AwayScore { get; set; }

        [JsonPropertyName("homeScore")]
        public int HomeScore { get; set; }

        [JsonPropertyName("isOut")]
        public bool IsOut { get; set; }
    }

    public class Review
    {
        [JsonPropertyName("hasChallenges")]
        public bool HasChallenges { get; set; }

        [JsonPropertyName("away")]
        public Away Away { get; set; }

        [JsonPropertyName("home")]
        public Home Home { get; set; }
    }

    public class Right
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class GameDetails
    {
        [JsonPropertyName("copyright")]
        public string Copyright { get; set; }

        [JsonPropertyName("gamePk")]
        public int GamePk { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }

        [JsonPropertyName("metaData")]
        public MetaData MetaData { get; set; }

        [JsonPropertyName("gameData")]
        public GameData GameData { get; set; }

        [JsonPropertyName("liveData")]
        public LiveData LiveData { get; set; }
    }

    public class Runner
    {
        [JsonPropertyName("movement")]
        public Movement Movement { get; set; }

        [JsonPropertyName("details")]
        public Details Details { get; set; }

        [JsonPropertyName("credits")]
        public List<Credit> Credits { get; set; }
    }

    public class Runner2
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Save
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class SeasonStats
    {
        [JsonPropertyName("batting")]
        public Batting Batting { get; set; }

        [JsonPropertyName("pitching")]
        public Pitching Pitching { get; set; }

        [JsonPropertyName("fielding")]
        public Fielding Fielding { get; set; }
    }

    public class Second
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Shortstop
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Split
    {
        [JsonPropertyName("stat")]
        public Stat Stat { get; set; }

        [JsonPropertyName("batter")]
        public string Batter { get; set; }

        [JsonPropertyName("pitcher")]
        public string Pitcher { get; set; }

        [JsonPropertyName("menOnBase")]
        public string MenOnBase { get; set; }
    }

    public class Sport
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }
    }

    public class SpringLeague
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }

        [JsonPropertyName("abbreviation")]
        public string Abbreviation { get; set; }
    }

    public class SpringVenue
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Stat
    {
        [JsonPropertyName("type")]
        public Type Type { get; set; }

        [JsonPropertyName("group")]
        public Group Group { get; set; }

        [JsonPropertyName("exemptions")]
        public List<object> Exemptions { get; set; }

        [JsonPropertyName("splits")]
        public List<Split> Splits { get; set; }

        [JsonPropertyName("batting")]
        public Batting Batting { get; set; }

        [JsonPropertyName("pitching")]
        public Pitching Pitching { get; set; }

        [JsonPropertyName("fielding")]
        public Fielding Fielding { get; set; }
    }

    public class Stat2
    {
        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("zones")]
        public List<Zone> Zones { get; set; }
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

        [JsonPropertyName("code")]
        public string Code { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }
    }

    public class Team
    {
        [JsonPropertyName("springLeague")]
        public SpringLeague SpringLeague { get; set; }

        [JsonPropertyName("allStarStatus")]
        public string AllStarStatus { get; set; }

        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Teams
    {
        [JsonPropertyName("away")]
        public Away Away { get; set; }

        [JsonPropertyName("home")]
        public Home Home { get; set; }
    }

    public class TeamStats
    {
        [JsonPropertyName("batting")]
        public Batting Batting { get; set; }

        [JsonPropertyName("pitching")]
        public Pitching Pitching { get; set; }

        [JsonPropertyName("fielding")]
        public Fielding Fielding { get; set; }
    }

    public class Third
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class TimeZone
    {
        [JsonPropertyName("id")]
        public string Id { get; set; }

        [JsonPropertyName("offset")]
        public int Offset { get; set; }

        [JsonPropertyName("offsetAtGameTime")]
        public int OffsetAtGameTime { get; set; }

        [JsonPropertyName("tz")]
        public string Tz { get; set; }
    }

    public class TopPerformer
    {
        [JsonPropertyName("player")]
        public Player Player { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("gameScore")]
        public int GameScore { get; set; }

        [JsonPropertyName("pitchingGameScore")]
        public int PitchingGameScore { get; set; }

        [JsonPropertyName("hittingGameScore")]
        public int? HittingGameScore { get; set; }
    }

    public class Type
    {
        [JsonPropertyName("code")]
        public string Code { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }

        [JsonPropertyName("displayName")]
        public string DisplayName { get; set; }
    }

    public class Venue
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }

        [JsonPropertyName("location")]
        public Location Location { get; set; }

        [JsonPropertyName("timeZone")]
        public TimeZone TimeZone { get; set; }

        [JsonPropertyName("fieldInfo")]
        public FieldInfo FieldInfo { get; set; }

        [JsonPropertyName("active")]
        public bool Active { get; set; }

        [JsonPropertyName("season")]
        public string Season { get; set; }
    }

    public class Weather
    {
        [JsonPropertyName("condition")]
        public string Condition { get; set; }

        [JsonPropertyName("temp")]
        public string Temp { get; set; }

        [JsonPropertyName("wind")]
        public string Wind { get; set; }
    }

    public class Winner
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("fullName")]
        public string FullName { get; set; }

        [JsonPropertyName("link")]
        public string Link { get; set; }
    }

    public class Zone
    {
        [JsonPropertyName("zone")]
        public string GameZone { get; set; }

        [JsonPropertyName("color")]
        public string Color { get; set; }

        [JsonPropertyName("temp")]
        public string Temp { get; set; }

        [JsonPropertyName("value")]
        public string Value { get; set; }
    }

