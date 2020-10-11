package fi.ajhaa.api

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import fi.ajhaa.data.AbilityScore
import fi.ajhaa.data.Class
import fi.ajhaa.data.Proficiency
import fi.ajhaa.data.Race
import fi.ajhaa.data.Subclass
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

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