package classobject

class Person3(val firstName: String, val familyName: String) {
    val fullName = "$firstName $familyName"

    fun printFirstName() {
        println(firstName)
    }
}