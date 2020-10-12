package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonProperty

class Skill private constructor(
    val index: String,
    val name: String,
    val desc: List<String>,
    @JsonProperty("ability_score")
    private val abilityScoreRef: ApiReference<AbilityScore>,
    private val url: String
) : ApiObject() {
    val abilityScore: AbilityScore by lazy {
        api.abilityScores.get(abilityScoreRef.index)
    }
}