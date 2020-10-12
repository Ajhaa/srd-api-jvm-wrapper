package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonProperty
import fi.ajhaa.api.Api

data class ApiChoice<T: ApiObject>(
    val choose: Int,
    val type: String,
    @JsonProperty("from")
    val fromRef: List<ApiReference<T>>
) : ApiObject()

data class Choice<T: ApiObject>(
    val choose: Int,
    val type: String,
    val from: List<T>
) : ApiObject()
