package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import fi.ajhaa.api.SRDApi

@JsonIgnoreProperties(ignoreUnknown = true)
data class Race(
    val index: String,
    val name: String,
    val speed: Int,
    @JsonProperty("ability_bonuses")
    val abilityBonuses: List<AbilityBonus>,
    @JsonProperty("ability_bonus_options")
    val abilityBonusOptions: Choice<AbilityBonus>,
    val alignment: String,
    val age: String,
    val size: String
) : ApiObject() {
    override fun initApi(api: SRDApi) {
        super.initApi(api)
        abilityBonuses.forEach { it.initApi(api!!) }
        abilityBonusOptions.from.forEach { it.initApi(api!!) }
    }
}