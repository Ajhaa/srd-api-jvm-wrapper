package fi.ajhaa

import fi.ajhaa.api.SRDApi

fun main() {
    val api = SRDApi("http://localhost:3000")
    try {
        val r = api.races.get("half-elf")
        println(r.abilityBonuses[0].abilityScore)
    } catch (e: Exception) {
        println(e)
    }
}