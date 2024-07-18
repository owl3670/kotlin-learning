package classobject

class LocalClass {
    fun localFunction(){
        var name = "Local Function"
        class LocalClass{
            fun printAndChangeName(){
                println(name)
                name = "Changed Name"
            }
        }
        val localClass = LocalClass()
        localClass.printAndChangeName()
        println(name)
    }
}