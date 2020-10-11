import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaTest {
    SRDApi api = new SRDApi("http://localhost:3000");
    Class wizard = api.getClasses().get("wizard");

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
}
