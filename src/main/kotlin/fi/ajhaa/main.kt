package fi.ajhaa

import fi.ajhaa.api.SRDApi

fun main() {
    val api = SRDApi("http://localhost:3000", cache = true)
    try {
        val category = api.equipmentCategories.get("ammunition").equipment
        //val arrow = category.equipment[0]
        //println(arrow.name)
    } catch (e: Exception) {
        println(e)
    }
}
