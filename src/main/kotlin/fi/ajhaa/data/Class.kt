package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import fi.ajhaa.api.SRDApi


@JsonIgnoreProperties(ignoreUnknown = true)
class Class private constructor(
        val index: String,
        val name: String,
        @JsonProperty("hit_die")
        val hitDie: String,
        @JsonProperty("proficiencies")
        private val proficienciesRef: List<ApiReference<Proficiency>>,
        @JsonProperty("proficiency_choices")
        private val proficiencyChoicesRef: List<ApiChoice<Proficiency>>,
        @JsonProperty("saving_throws")
        private val savingThrowsRef: List<ApiReference<AbilityScore>>,
        @JsonProperty("subclasses")
        private val subclassesRef: List<ApiReference<Subclass>>
) : ApiObject() {
    @get:JsonIgnore
    val savingThrows: List<AbilityScore> by lazy {
        savingThrowsRef.fromApiReferenceList(api.abilityScores)
    }

    @get:JsonIgnore
    val proficiencies: List<Proficiency> by lazy {
        proficienciesRef.fromApiReferenceList(api.proficiencies)
    }

    @get:JsonIgnore
    val proficiencyChoices: List<Choice<Proficiency>> by lazy {
        proficiencyChoicesRef.toChoiceList(api.proficiencies)
    }

    @get:JsonIgnore
    val subclasses: List<Subclass> by lazy {
        subclassesRef.fromApiReferenceList(api.subclasses)
    }
}
