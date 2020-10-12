package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class AbilityBonus private constructor(
    val index: String,
    val name: String,
    val bonus: Int,
    private val url: String,
) : ApiObject() {
    val abilityScore: AbilityScore by lazy {
        api.abilityScores.get(index)
    }
}