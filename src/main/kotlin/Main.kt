fun main(args: Array<String>) {
    var x = 1

    val o = object {
        val a = x++
    }

    println(x)
    println(o.a)
}