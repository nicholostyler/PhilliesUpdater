package nicholos.tyler.philliesupdater

import kotlinx.serialization.Serializable

@Serializable
data class RosterResponse(
    val copyright: String,
    val roster: List<PlayerRoster>,
    val link: String,
    val teamId: Int,
    val rosterType: String
)
@Serializable
data class PlayerRoster(
    val person: Person,
    val jerseyNumber: String,
    val position: Position,
    val status: PlayerStatus,
    val parentTeamId: Int
)
@Serializable
data class Person(
    val id: Int,
    val fullName: String,
    val link: String
)
@Serializable
data class PositionRoster(
    val code: String,
    val name: String,
    val type: String,
    val abbreviation: String
)
