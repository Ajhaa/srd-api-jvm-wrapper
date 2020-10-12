package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

class Subrace private constructor(
    val index: String,
    val name: String,
    val desc: String,
    @JsonProperty("race")
    private val raceRef: ApiReference<Race>,
    @JsonProperty("ability_bonuses")
    val abilityBonuses: List<AbilityBonus>,
    @JsonProperty("ability_bonus_options")
    val abilityBonusOptions: Choice<AbilityBonus>?,
    @JsonProperty("starting_proficiencies")
    private val startingProficienciesRef: List<ApiReference<Proficiency>>,
    @JsonProperty("starting_proficiency_options")
    private val startingProficiencyOptionsRef: ApiChoice<Proficiency>?,
    @JsonProperty("languages")
    private val languagesRef: List<ApiReference<Language>>,
    @JsonProperty("language_options")
    private val languageOptionsRef: ApiChoice<Language>?,
    @JsonProperty("racial_traits")
    private val racialTraitsRef: List<ApiReference<Trait>>,
    @JsonProperty("racial_trait_options")
    private val racialTraitOptionsRef: ApiChoice<Trait>?,
    private val url: String
) : ApiObject() {
    val race: Race by lazy {
        api.races.get(raceRef.index)
    }

    val startingProficiencies: List<Proficiency> by lazy {
        startingProficienciesRef.fromApiReferenceList(api.proficiencies)
    }

    val startingProficiencyOptions: Choice<Proficiency>? by lazy {
        startingProficiencyOptionsRef?.toChoice(api.proficiencies)
    }

    val languages: List<Language> by lazy {
        languagesRef.fromApiReferenceList(api.languages)
    }

    val languageOptions: Choice<Language>? by lazy {
        languageOptionsRef?.toChoice(api.languages)
    }

    val racialTraits: List<Trait> by lazy {
        racialTraitsRef.fromApiReferenceList(api.traits)
    }

    val racialTraitOptions: Choice<Trait>? by lazy {
        racialTraitOptionsRef?.toChoice(api.traits)
    }
}