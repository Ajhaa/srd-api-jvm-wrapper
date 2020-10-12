package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

class AbilityScore private constructor(
    val index: String,
    val name: String,
    @JsonProperty("full_name")
    val fullName: String,
    val desc: List<String>,
    @JsonProperty("skills")
    private val skillsRef: List<ApiReference<Skill>>,
    private val url: String
) : ApiObject() {
    val skills: List<Skill> by lazy {
        skillsRef.fromApiReferenceList(api.skills)
    }
}
