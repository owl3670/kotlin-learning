package classobject

class Person2(firstName: String, familyName: String) {
    val fullName = "$firstName $familyName"
    val age: Int

    init {
        println("Created new Person : $fullName")
        age = 2
    }
}