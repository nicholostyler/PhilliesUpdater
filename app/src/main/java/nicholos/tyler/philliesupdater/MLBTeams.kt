package nicholos.tyler.philliesupdater

enum class Divisions(val displayName: String) {
    AL_EAST("American League East"),
    AL_CENTRAL("American League Central"),
    AL_WEST("American League West"),
    NL_EAST("National League East"),
    NL_CENTRAL("National League Central"),
    NL_WEST("National League West")
}

enum class MLBTeam(val teamId: Int, val displayName: String, val Divisions: Divisions) {
    // American League East
    ORIOLES(110, "Baltimore Orioles", Divisions.AL_EAST),
    RED_SOX(111, "Boston Red Sox", Divisions.AL_EAST),
    YANKEES(147, "New York Yankees", Divisions.AL_EAST),
    RAYS(139, "Tampa Bay Rays", Divisions.AL_EAST),
    BLUE_JAYS(141, "Toronto Blue Jays", Divisions.AL_EAST),

    // American League Central
    WHITE_SOX(145, "Chicago White Sox", Divisions.AL_CENTRAL),
    GUARDIANS(114, "Cleveland Guardians", Divisions.AL_CENTRAL),
    TIGERS(116, "Detroit Tigers", Divisions.AL_CENTRAL),
    ROYALS(118, "Kansas City Royals", Divisions.AL_CENTRAL),
    TWINS(142, "Minnesota Twins", Divisions.AL_CENTRAL),

    // American League West
    ASTROS(117, "Houston Astros", Divisions.AL_WEST),
    ANGELS(108, "Los Angeles Angels", Divisions.AL_WEST),
    ATHLETICS(133, "Oakland Athletics", Divisions.AL_WEST),
    MARINERS(136, "Seattle Mariners", Divisions.AL_WEST),
    RANGERS(140, "Texas Rangers", Divisions.AL_WEST),

    // National League East
    BRAVES(144, "Atlanta Braves", Divisions.NL_EAST),
    MARLINS(146, "Miami Marlins", Divisions.NL_EAST),
    METS(121, "New York Mets", Divisions.NL_EAST),
    PHILLIES(143, "Philadelphia Phillies", Divisions.NL_EAST),
    NATIONALS(120, "Washington Nationals", Divisions.NL_EAST),

    // National League Central
    CUBS(112, "Chicago Cubs", Divisions.NL_CENTRAL),
    REDS(113, "Cincinnati Reds", Divisions.NL_CENTRAL),
    BREWERS(158, "Milwaukee Brewers", Divisions.NL_CENTRAL),
    PIRATES(134, "Pittsburgh Pirates", Divisions.NL_CENTRAL),
    CARDINALS(138, "St. Louis Cardinals", Divisions.NL_CENTRAL),

    // National League West
    DIAMONDBACKS(109, "Arizona Diamondbacks", Divisions.NL_WEST),
    ROCKIES(115, "Colorado Rockies", Divisions.NL_WEST),
    DODGERS(119, "Los Angeles Dodgers", Divisions.NL_WEST),
    PADRES(135, "San Diego Padres", Divisions.NL_WEST),
    GIANTS(137, "San Francisco Giants", Divisions.NL_WEST);
}
