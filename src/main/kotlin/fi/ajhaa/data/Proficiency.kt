package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Proficiency private constructor(
    val index: String,
    val type: String,
    val name: String,
    @JsonProperty("classes")
    private val classesRef: List<ApiReference<Class>>,
    @JsonProperty("races")
    private val racesRef: List<ApiReference<Race>>,
    //@JsonProperty("references")
    //private val referencesRef: List<ApiReference<EquipmentCategory>>
) : ApiObject() {
    @get:JsonIgnore
    val classes: List<Class> by lazy {
        classesRef.fromApiReferenceList(api.classes)
    }

    @get:JsonIgnore
    val races: List<Race> by lazy {
        racesRef.fromApiReferenceList(api.races)
    }
}