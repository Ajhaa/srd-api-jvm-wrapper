
fun main(args: Array<String>) {
    val api = SRDApi("http://localhost:3000")
    try {
        val c = api.classes.get("wizard")
        println(c.subclasses)
    } catch (e: Exception) {
        println(e)
    }
}