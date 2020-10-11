package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Subclass (
    val index: String,
    @JsonProperty("class")
    val classRef: ApiReference<Class>,
    val name: String,
    @JsonProperty("subclass_flavor")
    val subclassFlavor: String,
    val desc: List<String>,
    @JsonProperty("subclass_levels")
    val subclassLevelsLink: String, // TODO make class for link
    private val url: String
) : ApiObject()