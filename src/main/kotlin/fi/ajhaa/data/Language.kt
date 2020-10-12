package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonProperty

class Language private constructor(
    val index: String,
    val name: String,
    val type: String,
    val desc: String?,
    @JsonProperty("typical_speakers")
    val typicalSpeakers: List<String>,
    val script: String?,
    private val url: String
) : ApiObject() {
}