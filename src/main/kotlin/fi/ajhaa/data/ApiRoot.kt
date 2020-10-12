package fi.ajhaa.data

internal class ApiRoot<T: ApiObject> private constructor(
        val count: Int,
        val results: List<ApiReference<T>>
) : ApiObject() {
}