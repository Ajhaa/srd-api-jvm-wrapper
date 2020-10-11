import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Proficiency(
    val index: String,
    val type: String,
    val name: String,
    val classes: List<ApiReference<Class>>,
    val races: List<ApiReference<Race>>
) : ApiObject()