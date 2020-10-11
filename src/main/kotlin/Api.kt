import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


abstract class ApiObject {
    protected var api: SRDApi? = null
    // abstract val basePath: String

    fun initApi(api: SRDApi) {
        this.api = api
    }
}

class SRDApi(private val url: String) {
    private val client: HttpClient = HttpClient.newHttpClient()
    internal val jackson = JsonMapper.builder().addModule(KotlinModule()).build()

    fun request(path: String) : String {
        val req = HttpRequest.newBuilder()
            .uri(URI(url + path))
            .GET()
            .build()
        val response =  client.send(req, HttpResponse.BodyHandlers.ofString())
        return response.body()
    }

    val classes = Api("/api/classes/", Class::class.java, this)
    val races = Api("/api/races/", Race::class.java, this)
    val abilityScores = Api("/api/ability-scores/", AbilityScore::class.java, this)
    val proficiencies = Api("/api/proficiencies/", Proficiency::class.java, this)
    val subclasses = Api("/api/subclasses/", Subclass::class.java, this)
}

class Api<T: ApiObject>(private val basePath: String, private val targetClass: java.lang.Class<T>, private val api: SRDApi) {

    private fun fromJson(json: String) : T {
        return api.jackson.readValue(json, targetClass)
    }

    fun get(name: String) : T {
        val response = api.request(basePath + name)
        val result = fromJson(response)
        result.initApi(api)
        return result
    }
}

fun <T: ApiObject>fromApiReferenceList(refList: List<ApiReference<T>>, api: Api<T>) : List<T> {
    val list = mutableListOf<T>()
    for (ref in refList) {
        list.add(api.get(ref.index))
    }

    return list
}