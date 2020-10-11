data class ApiReference<T: ApiObject>(
    val index: String,
    val name: String,
    val url: String
) : ApiObject()