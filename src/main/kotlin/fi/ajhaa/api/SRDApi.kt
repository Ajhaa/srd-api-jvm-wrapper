package fi.ajhaa.api

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import fi.ajhaa.data.*
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class SRDApi(private val url: String, val cache: Boolean = true) {
    private val client: HttpClient = HttpClient.newHttpClient()

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
    val skills = Api("/api/skills/", Skill::class.java, this)
    val languages = Api("/api/languages/", Language::class.java, this)
    val traits = Api("/api/traits/", Trait::class.java, this)
    val subraces = Api("/api/subraces/", Subrace::class.java, this)

}
