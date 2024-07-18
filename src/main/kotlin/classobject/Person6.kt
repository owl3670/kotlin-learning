package classobject

class Person6 {
    val fullName: String

    constructor(firstName: String, familyName: String) : this("$firstName $familyName")

    constructor(fullName: String) {
        this.fullName = fullName
    }
}