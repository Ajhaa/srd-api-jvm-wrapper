import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class Choice<T>(
    val choose: Int,
    val type: String,
    @JsonProperty("from")
    val fromRef: List<ApiReference<T>>
) : ApiObject() {
   // @get:JsonIgnore
   // val from: List<T> by lazy { fromApiReferenceList(fromRef) }
}