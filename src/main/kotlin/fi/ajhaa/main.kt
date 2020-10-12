package fi.ajhaa

import fi.ajhaa.api.Api
import fi.ajhaa.api.SRDApi
import fi.ajhaa.data.ApiObject
import fi.ajhaa.data.Race
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

fun main() {
    val api = SRDApi("http://localhost:3000")
    try {
        api.traits.list()
    } catch (e: Exception) {
        println(e)
    }
}
