package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AbilityScore(
    val index: String,
    val name: String,
    @JsonProperty("full_name")
    val fullName: String,
    //val desc: List<String>
) : ApiObject()
