package classobject

object Application {
    val name = "Kotlin"

    override fun toString(): String {
        return "Application(name='$name')"
    }

    fun exit() {}
}