package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class Trait private constructor(
    val index: String,
    @JsonProperty("races")
    private val racesRef: List<ApiReference<Race>>,
    @JsonProperty("subraces")
    private val subracesRef: List<ApiReference<Subrace>>,
    val name: String,
    val desc: List<String>,
    @JsonProperty("proficiencies")
    private val proficienciesRef: List<ApiReference<Proficiency>>,
    @JsonProperty("proficiency_choices")
    private val proficiencyChoicesRef: ApiChoice<Proficiency>?,
    private val url: String
) : ApiObject() {
    @get:JsonIgnore
    val races: List<Race> by lazy {
        racesRef.fromApiReferenceList(api.races)
    }

    @get:JsonIgnore
    val subraces: List<Subrace> by lazy {
        subracesRef.fromApiReferenceList(api.subraces)
    }

    @get:JsonIgnore
    val proficiencies: List<Proficiency> by lazy {
        proficienciesRef.fromApiReferenceList(api.proficiencies)
    }

    @get:JsonIgnore
    val proficiencyChoices: Choice<Proficiency>? by lazy {
        proficiencyChoicesRef?.toChoice(api.proficiencies)
    }
}