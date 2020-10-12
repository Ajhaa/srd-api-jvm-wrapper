package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonProperty

internal class ApiChoice<T: ApiObject>(
    val choose: Int,
    val type: String,
    @JsonProperty("from")
    val fromRef: List<ApiReference<T>>
) : ApiObject()

class Choice<T: ApiObject> internal constructor(
    val choose: Int,
    val type: String,
    val from: List<T>
) : ApiObject()
