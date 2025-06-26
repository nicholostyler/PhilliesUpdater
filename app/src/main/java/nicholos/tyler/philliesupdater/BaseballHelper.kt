package nicholos.tyler.philliesupdater


class BaseballHelper {
    companion object {
        val teamAbbreviations = mapOf(
            "Arizona Diamondbacks" to "ARI",
            "Atlanta Braves" to "ATL",
            "Baltimore Orioles" to "BAL",
            "Boston Red Sox" to "BOS",
            "Chicago White Sox" to "CWS",
            "Chicago Cubs" to "CHC",
            "Cincinnati Reds" to "CIN",
            "Cleveland Guardians" to "CLE",
            "Colorado Rockies" to "COL",
            "Detroit Tigers" to "DET",
            "Houston Astros" to "HOU",
            "Kansas City Royals" to "KC",
            "Los Angeles Angels" to "LAA",
            "Los Angeles Dodgers" to "LAD",
            "Miami Marlins" to "MIA",
            "Milwaukee Brewers" to "MIL",
            "Minnesota Twins" to "MIN",
            "New York Mets" to "NYM",
            "New York Yankees" to "NYY",
            "Oakland Athletics" to "OAK",
            "Philadelphia Phillies" to "PHI",
            "Pittsburgh Pirates" to "PIT",
            "San Diego Padres" to "SD",
            "San Francisco Giants" to "SF",
            "Seattle Mariners" to "SEA",
            "St. Louis Cardinals" to "STL",
            "Tampa Bay Rays" to "TB",
            "Texas Rangers" to "TEX",
            "Toronto Blue Jays" to "TOR",
            "Washington Nationals" to "WSH"
        )
        fun abbreviateMatchup(homeTeam: String, awayTeam: String, yourTeam: String): String {
            val home = abbreviateTeamName(homeTeam)
            val away = abbreviateTeamName(awayTeam)

            return if (homeTeam == yourTeam) {
                "$home vs $away"
            } else {
                "$away @ $home"
            }
        }


        fun abbreviateTeamName(teamName: String): String {
            return teamAbbreviations[teamName] ?: teamName
        }

        fun teamFromID(teamId: Int): MLBTeam {
            return MLBTeam.values().find { it.teamId == teamId }
                ?: throw IllegalArgumentException("Invalid teamId: $teamId")
        }

        fun byDivision(division: Divisions): List<MLBTeam> {
            return MLBTeam.values().filter { it.Divisions == division }
        }

        fun getInningSuffix(inning: Int?): String {
            if (inning == null) {
                return ""
            }
            return when (inning) {
                1 -> "st"
                2 -> "nd"
                3 -> "rd"
                else -> "th"
            }
        }

        fun countRunnersOnBase(gameData: GameDetailResponse?): Int {
            val allPlays = gameData?.liveData?.plays?.allPlays.orEmpty()
            val firstPlay = allPlays.firstOrNull() ?: return 0

            var runners = 0
            if (firstPlay.matchup?.postOnFirst != null) runners += 1
            if (firstPlay.matchup?.postOnSecond != null) runners += 1
            if (firstPlay.matchup?.postOnThird != null) runners += 1

            return runners
        }

        }


    }