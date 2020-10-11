package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Proficiency(
    val index: String,
    val type: String,
    val name: String,
    val classes: List<ApiReference<Class>>,
    val races: List<ApiReference<Race>>
) : ApiObject()