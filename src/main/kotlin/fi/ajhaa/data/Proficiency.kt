package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Proficiency private constructor(
    val index: String,
    val type: String,
    val name: String,
    private val classes: List<ApiReference<Class>>,
    private val races: List<ApiReference<Race>>
) : ApiObject()