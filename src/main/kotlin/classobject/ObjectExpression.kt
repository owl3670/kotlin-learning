package classobject

class ObjectExpression {
    fun createApplication() {
        fun testApp(name: String) = object {
            val name = name
            fun exit() {
                println("Exiting $name")
            }
        }
        val app = testApp("Kotlin")
        val app2 = testApp("Java")
        println(app.name)
        app.exit()
    }

    fun createApplication2() {
        val app = object {
            val name = "Kotlin"
            fun exit() {
                println("Exiting $name")
            }
        }
        println(app.name)
        app.exit()
    }
}