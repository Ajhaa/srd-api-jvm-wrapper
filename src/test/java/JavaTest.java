import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaTest {
    SRDApi api = new SRDApi("http://localhost:3000");

    @Test
    public void apiWorksInJava() {
        Class bard = api.getClasses().get("bard");
        assertEquals("Bard", bard.getName());
    }

    @Test
    public void lazyAbilityScoresWork() {
        Class wizard = api.getClasses().get("wizard");
        List<AbilityScore> saves = wizard.getSavingThrows();
        assertEquals("INT", saves.get(0).getName());
    }

    @Test
    public void lazyProficienciesWork() {
        Class wizard = api.getClasses().get("wizard");
        List<Proficiency> profs = wizard.getProficiencies();
        assertEquals("daggers", profs.get(0).getIndex());
    }

    @Test
    public void lazySubclassesWork() {
        Class wizard = api.getClasses().get("wizard");
        List<Subclass> sc = wizard.getSubclasses();
        assertEquals("evocation", sc.get(0).getIndex());
    }
}
