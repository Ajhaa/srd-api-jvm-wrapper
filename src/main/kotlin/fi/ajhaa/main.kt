package fi.ajhaa

import fi.ajhaa.api.SRDApi
import kotlin.system.measureNanoTime

fun main() {
    val api = SRDApi("http://localhost:3000", cache = true)
    try {
        Thread.sleep(1000)
        val dur1 = measureNanoTime {
            api.races.list()
        } / 1000_000

        val dur2 = measureNanoTime {
            api.proficiencies.list()
        } / 1000_000

        val dur3 = measureNanoTime {
            api.traits.list()
        } / 1000_000

        println("$dur1, $dur2, $dur3")
    } catch (e: Exception) {
        println(e)
    }
}
