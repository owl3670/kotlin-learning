import util.truncate
import Person.Companion.parsePerson

fun aggregate(numbers: IntArray, op: (Int, Int) -> Int): Int {
    var result = 0
    for (number in numbers) {
        result = op(result, number)
    }
    return result
}

fun sum(numbers: IntArray) = aggregate(numbers, { a, b -> a + b })
fun max(numbers: IntArray) = aggregate(numbers, { a, b -> if (a > b) a else b })

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

fun measureTime(action: (() -> Unit)?): Long {
    val start = System.currentTimeMillis()
    action?.invoke()
    return System.currentTimeMillis() - start
}

fun check(s: String, condition: (Char) -> Boolean): Boolean {
    for (c in s) {
        if (!condition(c)) return false
    }
    return true
}

fun isCapitalLetter(c: Char) = c.isUpperCase() && c.isLetter()

class Person(val name: String) {
    fun hasNameOf(name: String) = name.equals(this.name, ignoreCase = true)
}

class Person2(var firstName: String, var familyName: String)

inline fun indexOf(numbers: IntArray, confition: (Int) -> Boolean): Int {
    for (index in numbers.indices) {
        if (confition(numbers[index])) return index
    }
    return -1
}

//inline fun forEach(a: IntArray, action: ((Int) -> Unit)?){ // Error
//    if (action == null) return
//    for(n in a) action(n)
//}

class Person3(private val firstName: String, private val familyName: String) {
    inline fun sendMessage(message: () -> String) {
        println("$firstName $familyName:${message()}") // Error
    }
}

class Person4(var firstName: String, var familyName: String) {
    var fullName
        inline get() = "$firstName $familyName" // inline 게터
        set(value) {} // inline이 아닌 세터
}


//inline fun forEach(a: IntArray, noinline action: ((Int) -> Unit)?){
//    if (action == null) return
//    for(n in a) action(n)
//}
//inline fun forEach(a: IntArray, action: (Int) -> Unit){
//    for(n in a) action(n)
//}
//inline fun forEach(a: IntArray, action: (Int)->Unit) = object {
//    fun run() {
//        for (n in a) {
//            action(n) // Error
//        }
//    }
//}
inline fun forEach(
    a: IntArray, crossinline action: (Int) -> Unit
) = object {
    fun run() {
        for (n in a) {
            action(n)
        }
    }
}


fun String.truncate(maxLength: Int): String {
    return if (length <= maxLength) this else substring(0, maxLength)
}

class Person5(var name: String, private val age: Int) {
}

class Person6(val name: String, private val age: Int) {
    fun Person6.showInfo() = println("$name, $age")
}

class Person7(val firstName: String, val familyName: String) {
    fun fullName() = "$firstName $familyName"
}

interface Truncated {
    val truncated: String
    val original: String
}

private fun String.truncator(max: Int) = object : Truncated {
    override val truncated
        get() = if (length <= max) this@truncator else substring(0, max)

    override val original
        get() = this@truncator
}


val IntRange.leftHalf: IntRange
    get() = start..(start + endInclusive) / 2
val IntArray.midIndex
    get() = lastIndex / 2
var IntArray.midValue
    get() = this[midIndex]
    set(value) {
        this[midIndex] = value
    }

val String.message by lazy { "Hello" }

object Messages

val Messages.HELLO by lazy { "Hello" }


fun IntRange.Companion.singletonRange(n: Int) = n..n

fun aggregate(numbers: IntArray, op: Int.(Int) -> Int): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty array")

    for (i in 1..numbers.lastIndex) {
        result.op(numbers[i])
    }

    return result
}

fun aggregate(numbers: IntArray, op: Int.(Int) -> Int): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty array")

    for (i in 1..numbers.lastIndex) {
        result = op(result, numbers[i])
    }

    return result
}


fun aggregate(numbers: IntArray, op: Int.(Int) -> Int): Int {
    var result = numbers.firstOrNumm() ?: throw IllegalArgumentException("Empty array")

    for (i in 1..numbers.lastIndex) result = result.op(numbers[i])

    return result
}

//fun Int.max(other: Int) = if (this > other) this else other
fun Int.max(a: Int, b: Int) = if (a > b) a else b
fun aggregate(numbers: IntArray, op: (Int, Int) -> Int): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty array")

    for (i in 1..numbers.lastIndex) result = op(result, numbers[i])
}

return result
}


class Address {
    var zipCode: Int = 0
    var city: String = ""
    var street: String = ""
    var house: String = ""

    fun post(message: String): Boolean {
        "Message for {$zipCode, $city, $street, $house}: $message"
        return readLine() == "OK"
    }
}

class Address(val city: String, val street: String, val house: String) {
    fun asText() = "$city, $street, $house"
}

class Address(val city: String, val street: String, val house: String) {
    fun post(message: String) {}
}

fun readInt() = try {
    readLine()?.toInt()
} catch (e: NumberFormatException) {
    null
}


class Person(val firstName: String, val familyName: String) {
    fun Address.post(message: String) {
        // 암시적 this : extension receiver(Address)
        val city = city
        // 한정시키지 않은 this: extension receiver(Address)
        val street = this.city
        // 한정시킨 this: extension receiver(Address)
        val house = this@post.house
        // 임시적 this: dispatch receiver(Person)
        val firstName = firstName
        // 한정시킨 this: dispatch receiver(Person)
        val familyName = this@Person.familyName

        println("From $firstName $familyName to $city, $street, $house: $message")
    }

    fun test(address: Address) {
        // dispatch receiver : 암시적
        // extension receiver : 한정시키지 않음
        address.post("Hello")
    }
}

class Person(val firstName: String, val familyName: String) {
    fun Address.post(message: String) {}
    inner class Mailbox {
        fun Person.testExt(address: Address) {
            address.post("Hello")
        }
    }

    fun Person.testExt(address: Address) {
        address.post("Hello")
    }
}

class Address(val city: String, val street: String, val house: String) {
    fun test(person: Person) {
        person.post("Hello") // Error:method post() is not defined
    }
}

class Person(val firstName: String, val familyName: String) {
    fun Address.post(message: String) {}
}

class Address(val city: String, val street: String, val house: String) {
    fun test(person: Person) {
        with(person) {
            // 암시적 디스패치와 확장 수신 객체
            post("Hello")
        }
    }
}

class Person(val firstName: String, val familyName: String) {
    fun Address.post(message: String) {}
}

class Person(val firstName: String, val familyName: String) {
    // Person 클래스 밖에서는 쓸 수 없음
    private fun Address.post(message: String) {}
    fun test(address: Address) = address.post("Hello")
}

class Person(val firstName: String, val familyName: String) {
    companion object {
        fun String.parsePerson(): Person? {
            val names = split(" ")
            return if (names.size == 2) Person(names[0], names[1]) else null
        }
    }
}

fun main(args: Array<String>) {
    val numbers = intArrayOf(1, 2, 3, 4, 5)
    println("Sum: ${sum(numbers)}")  // 15
    println("Max: ${max(numbers)}")  // 5


    val isBig: (Int, Int) -> Boolean = { a, b -> a > b }
    isBig(10, 5)
    isBig.invoke(10, 5)


    val isEven = object : IntPredicate {
        override fun accept(i: Int) = i % 2 == 0
    }

    // val lessThan0 = { a, b -> a < b } // Error
    val lessThan0: (Int, Int) -> Boolean = { a, b -> a < b }
    val lessThan1 = { a: Int, b: Int -> a < b }


    val time = measureTime {
        val numbers = intArrayOf(1, 2, 3, 4, 5)
        println("Sum: ${sum(numbers)}")
        println("Max: ${max(numbers)}")
    }
    println("Time: $time")


    val shifter0: (Int) -> (Int) -> Int = { n -> { i -> i + n } }
    val shifter1: (Int) -> ((Int) -> Int) = { n -> { i -> i + n } }
    val shifter2 = { n: Int -> { i: Int -> i + n } }

    val inc1 = shifter0(1)
    val inc3 = shifter1(3)
    val inc5 = shifter2(5)

    println(inc1(5)) // 6
    println(inc3(5)) // 8
    println(inc5(5)) // 10


    val evalAtZero: ((Int) -> Int) -> Int = { f -> f(0) }

    println(evalAtZero { it + 1 }) // 1
    println(evalAtZero { it * 2 }) // 0


    fun sum(numbers: IntArray) = aggregate(numbers) { a, b -> a + b }
//    fun max(numbers: IntArray) = aggregate(numbers) { a, b -> if (a > b) a else b }
    val time2 = measureTime { 1 + 2 }
    print(check("Hello") { it.isLowerCase() })
    print(check("Hello") { _ -> true })
    fun sum2(numbers: IntArray) = aggregate(numbers, fun(result, op) = result + op)

    val numbers2: IntArray = intArrayOf(1, 2, 3, 4, 5)
    val sumResult = sum(numbers2)
    println(sumResult)


    println(check("Hello", ::isCapitalLetter))
    val isJohn = Person("John")::hasNameOf
    println(isJohn("Jake")) // false
    println(isJohn("john")) // true

//    fun max(a:Int, b: Int) = if (a> b) a else b
//    fun max(a:Double, b:Double) = if (a > b) a else b
//    val f:(Int, Int) -> Int = ::max // Ok
//    val g = ::max // Error

    fun max(a: Int, b: Int) = if (a > b) a else b
    println((::max)(1, 2))
    println(::max(1, 2)) // Error

    val person = Person2("John", "Doe")
    val readName = person::firstName.getter
    val writeFamily = person::familyName.setter
    println(readName())
    writeFamily("Smith")


    println(indexOf(intArrayOf(4, 3, 2, 1)) { it < 3 })


    forEach(intArrayOf(1, 2, 3, 4)) {
        // not inline 함수에서는 Error
        // inline 함수에서는 main 에서 반환되는 것으로 동작
        if (it < 2 || it > 3) return
        println(it)
    }
    forEach(intArrayOf(1, 2, 3, 4), fun(it: Int) {
        if (it < 2 || it > 3) return
        println(it)
    })
    forEach(intArrayOf(1, 2, 3, 4), fun(it: Int) {
        if (it < 2 || it > 3) return@forEach
        println(it)
    })
    val action: (Int) -> Unit = myFunc@{
        if (it < 2 || it > 3) return@myFunc
        println(it)
    }


    println("Hello".truncate(10))
    fun Person5.showInfo() = println("$name, $age") // Error
    val f = Person5("John", 30)::showInfo

    fun Person7.fullName() = "$familyName $firstName"
    println(Person7("John", "Doe").fullName()) // John Doe

    val truncator = "Hello".truncator(3)
    println(truncator.truncated) // Hel
    println(truncator.original) // Hello

    println("Hello".truncate(3))

    fun String?.truncate(maxLength: Int): String? {
        if (this == null) return null
        return if (length <= maxLength) this else substring(0, maxLength)
    }

    val s = readLine()
    println(s.truncate(3))


    println((1..3).leftHalf) // 1..2

    val numbers3 = intArrayOf(1, 2, 3, 4, 5)
    println(numbers3.midValue) // 3
    numbers3.midValue = 10
    println(numbers3.midValue) // 10

    println("Hello".message) // Hello
    println("Bye".message) // Hello
    println(Messages.HELLO) // Hello


    println(IntRange.singletonRange(5)) // 5..5
    println(IntRange.Companion.singletonRange(3)) // 3..3

    fun sum(numbers: IntArray) = aggregate(numbers) { op -> this + op }
    fun sum(numbers: IntArray) = aggregate(numbers, fun Int.(op: Int) = this + op)

    val numbers4 = intArrayOf(1, 2, 3, 4)
    println(aggregate(numbers, Int::plus)) // 10
    println(aggregate(numbers, Int::max)) // 4
    println(aggregate(numbers, ::max)) // 4


    val isReceived = Address().run {
        zipCode = 12345
        city = "Seoul"
        street = "Gangnam"
        house = "123-456"
        post("Hello") // 반환값
    }
    if (!isReceived) {
        println("Message is not delivered")
    }

    val message = with(Address("Seoul", "Gangnam", "123-456")) {
        "Address: $city, $street, $house"
    }
    println(message)

    val address = run {
        val city = readLine() ?: return
        val street = readLine() ?: return
        val house = readLine() ?: return
        Address(city, street, house)
    }
//    val address = {
//        val city = readLine() ?: return
//        val street = readLine() ?: return
//        val house = readLine() ?: return
//        Address(city, street, house)
//    }
    println(address.asText())

    Address("London", "Baker Street", "221B").let {
        println("To city: ${it.city}")
        it.post("Hello")
    }

    val index = readInt()
    val arg = index?.let { args.getOrNull(it) }
//    val arg = if (index != null) args.getOrNull(index) else null
//    if (arg != null) {
//        println(arg)
//    }

    val message = readLine() ?: return
    Address().apply {
        city = "London"
        street = "Baker Street"
        house = "221b"
    }.post(message)
    Address().also {
        it.city = "London"
        it.street = "Baker Street"
        it.house = "221b"
    }.post(message)


    with(Person("John", "Watson")) {
        Address("London", "Baker Street", "221b").post("Hello")
    }

    println("John Doe".parsePerson()?.firstName)
}