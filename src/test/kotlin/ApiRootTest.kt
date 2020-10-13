import fi.ajhaa.api.Api
import fi.ajhaa.api.SRDApi
import fi.ajhaa.data.ApiObject
import org.junit.Test
import org.junit.jupiter.api.Assertions
import kotlin.test.assertEquals

class ApiRootTest {
    var api: SRDApi

    @Test
    fun testRacesRoot() {
        val races = api.races.list()
        assertEquals("dragonborn", races[0].index)
    }

    @Test
    fun testClassesRoot() {
        val classes = api.classes.list()
        assertEquals("barbarian", classes[0].index)
    }

    @Test
    fun testTraitsRoot() {
        val traits = api.traits.list()
        assertEquals("artificers-lore", traits[0].index)
    }

    @Test
    fun testSkillsRoot() {
        assertEquals("acrobatics", api.skills.list()[0].index)
    }

    @Test
    fun testLanguagesRoot() {
        assertEquals("abyssal", api.languages.list()[0].index)
    }

    @Test
    fun testAbilityScoresRoot() {
        assertEquals("cha", api.abilityScores.list()[0].index)
    }

    @Test
    fun testSubracesRoot() {
        assertEquals("high-elf", api.subraces.list()[0].index)
    }

    @Test
    fun testSubclassesRoot() {
        assertEquals("berserker", api.subclasses.list()[0].index)
    }

    @Test
    fun testProficienciesRoot() {
        assertEquals("alchemists-supplies", api.proficiencies.list()[0].index)
    }

    @Test
    fun testEquipmentRoot() {
        assertEquals("abacus", api.equipment.list()[0].index)
    }

    @Test
    fun testEquipmentCategoriesRoot() {
        assertEquals("adventuring-gear", api.equipmentCategories.list()[0].index)
    }

    init {
        var apiUrl = System.getenv("API_URL")
        if (apiUrl == null) apiUrl = "http://localhost:3000"
        api = SRDApi(apiUrl)
    }
}