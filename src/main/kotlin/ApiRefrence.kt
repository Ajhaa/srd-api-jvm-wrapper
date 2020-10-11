data class ApiReference<T>(
    val index: String,
    val name: String,
    private val url: String
) : ApiObject()