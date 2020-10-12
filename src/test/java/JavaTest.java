import fi.ajhaa.data.*;
import fi.ajhaa.api.SRDApi;
import fi.ajhaa.data.Class;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaTest {
    SRDApi api;
    Class wizard;
    Race halfElf;
    public JavaTest() {
        String apiUrl = System.getenv("API_URL");
        if (apiUrl == null) apiUrl = "http://localhost:3000";
        api = new SRDApi(apiUrl);
        wizard = api.getClasses().get("wizard");
        halfElf = api.getRaces().get("half-elf");
    }

    @Test
    public void testRacesRoot() {
        List<Race> races = api.getRaces().list();
        assertEquals("dragonborn", races.get(0).getIndex());
    }

    @Test
    public void testGetClass() {
        Class bard = api.getClasses().get("bard");
        assertEquals("Bard", bard.getName());
    }

    @Test
    public void testSavingThrows() {
        List<AbilityScore> saves = wizard.getSavingThrows();
        assertEquals("INT", saves.get(0).getName());
    }

    @Test
    public void testProficiencies() {
        List<Proficiency> profs = wizard.getProficiencies();
        assertEquals("daggers", profs.get(0).getIndex());
    }

    @Test
    public void testSubclasses() {
        List<Subclass> sc = wizard.getSubclasses();
        assertEquals("evocation", sc.get(0).getIndex());
    }

    @Test
    public void testProficiencyChoices() {
        Choice<Proficiency> choice = wizard.getProficiencyChoices().get(0);

        assertEquals(2, choice.getChoose());
        assertEquals("skill-arcana", choice.getFrom().get(0).getIndex());
    }

    @Test
    public void testAbilityBonuses() {
        AbilityBonus bonus = halfElf.getAbilityBonuses().get(0);
        assertEquals("CHA", bonus.getName());
        assertEquals("Charisma", bonus.getAbilityScore().getFullName());
    }

    @Test
    public void testAbilityBonusOptions() {
        Choice bonusChoice = halfElf.getAbilityBonusOptions();
        assertEquals(2, bonusChoice.getChoose());
    }

    @Test
    public void testSkillsFromAbility() {
        AbilityBonus bonus = halfElf.getAbilityBonuses().get(0);

        Skill deception = bonus.getAbilityScore().getSkills().get(0);
        assertEquals("Deception", deception.getName());
    }
}
