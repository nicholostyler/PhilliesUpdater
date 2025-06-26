package nicholos.tyler.philliesupdater

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse (
    val copyright: String? = null,
    val teams: List<TeamDetails>? = emptyList()
)