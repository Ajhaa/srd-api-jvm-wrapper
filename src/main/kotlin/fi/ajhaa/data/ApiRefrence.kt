package fi.ajhaa.data

import fi.ajhaa.api.Api

internal class ApiReference<T: ApiObject>(
    val index: String,
    val name: String,
    val url: String
) : ApiObject()

internal fun <T: ApiObject>List<ApiReference<T>>.fromApiReferenceList(api: Api<T>) : List<T> {
    return this.map { api.get(it.index) }
}