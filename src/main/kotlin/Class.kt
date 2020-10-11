import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
class Class(
    val name: String,
    @JsonProperty("hit_die")
    val hitDie: String,
    @JsonProperty("proficiencies")
    val proficienciesRef: List<ApiReference<Proficiency>>,
    @JsonProperty("proficiency_choices")
    private val proficiencyChoicesRef: List<ApiChoice<Proficiency>>,
    @JsonProperty("saving_throws")
    private val savingThrowsRef: List<ApiReference<AbilityScore>>,
    @JsonProperty("subclasses")
    private val subclassesRef: List<ApiReference<Subclass>>
) : ApiObject() {
    val savingThrows: List<AbilityScore> by lazy {
        fromApiReferenceList(savingThrowsRef, api!!.abilityScores)
    }

    @get:JsonIgnore
    val proficiencies: List<Proficiency> by lazy {
        fromApiReferenceList(proficienciesRef, api!!.proficiencies)
    }

    val proficiencyChoices: List<Choice<Proficiency>> by lazy {
        proficiencyChoicesRef.map {
            Choice(
                it.choose,
                it.type,
                fromApiReferenceList(it.fromRef, api!!.proficiencies)
            )
        }
    }

    @get:JsonIgnore
    val subclasses: List<Subclass> by lazy {
        fromApiReferenceList(subclassesRef, api!!.subclasses)
    }
}
