# 5e SRD Api wrapper

A JVM wrapper for https://github.com/bagelbits/5e-srd-api written in Kotlin.

Kotlin example:
```
val api = SRDApi("http://localhost:3000")

val bard: Class = api.classes.get("bard")
val lightArmor: Proficiency = bard.proficiencies[0]
val classesWithLightArmor: List<Class> = lightArmor.classes

val allRaces: List<Race> = api.races.list()
```

Java example:
```
SRDApi api = new SRDApi("http://localhost:3000", true);

Class bard = api.getClasses().get("bard");
Proficiency lightArmor = bard.getProficiencies().get(0);
List<Class> classesWithLightArmor = lightArmor.getClasses();

List<Race> allRaces = api.getRaces().list();
```
