package fi.ajhaa.data

data class Race(
    val index: String,
    val name: String,
    val speed: Int,
    //@JsonProperty("ability_bonuses")
    //val abilityBonuses: List<Int>,
    val alignment: String,
    val age: String,
    val size: String
) : ApiObject()