package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonProperty
import fi.ajhaa.api.Api

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

internal fun <T: ApiObject>ApiChoice<T>.toChoice(api: Api<T>) : Choice<T> {
    return Choice(
        this.choose,
        this.type,
        this.fromRef.map { api.get(it.index) }
    )
}

internal fun <T: ApiObject>List<ApiChoice<T>>.toChoiceList(api: Api<T>) : List<Choice<T>> {
    return this.map { it.toChoice(api) }
}
