package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonProperty

class Race private constructor(
    val index: String,
    val name: String,
    val speed: Int,
    val alignment: String,
    @JsonProperty("ability_bonuses")
    val abilityBonuses: List<AbilityBonus>,
    @JsonProperty("ability_bonus_options")
    val abilityBonusOptions: Choice<AbilityBonus>?,
    val age: String,
    val size: String,
    @JsonProperty("size_description")
    val sizeDescription: String,
    @JsonProperty("starting_proficiencies")
    private val startingProficienciesRef: List<ApiReference<Proficiency>>,
    @JsonProperty("starting_proficiency_options")
    private val startingProficiencyOptionsRef: ApiChoice<Proficiency>?,
    @JsonProperty("languages")
    private val languagesRef: List<ApiReference<Language>>,
    @JsonProperty("language_options")
    private val languageOptionsRef: ApiChoice<Language>?,
    @JsonProperty("language_desc")
    val languageDesc: String,
    @JsonProperty("traits")
    private val traitsRef: List<ApiReference<Trait>>,
    @JsonProperty("subraces")
    private val subracesRef: List<ApiReference<Subrace>>,
    @JsonProperty("trait_options")
    private val traitOptionsRef: ApiChoice<Trait>?,
    private val url: String
) : ApiObject() {
    val subraces: List<Subrace> by lazy {
        subracesRef.fromApiReferenceList(api.subraces)
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

    val traits: List<Trait> by lazy {
        traitsRef.fromApiReferenceList(api.traits)
    }

    val traitOptions: Choice<Trait>? by lazy {
        traitOptionsRef?.toChoice(api.traits)
    }
}