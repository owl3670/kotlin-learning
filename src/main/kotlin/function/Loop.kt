package function

class Loop {
    fun whileLoop() {
        var x = 5
        while (x > 0) {
            println(x)
            x--
        }
    }

    fun forEachLoop() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }
    }

    fun forLoop() {
        for (i in 1..3) {
            println(i)
        }
    }

    fun forIndicesLoop() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }
    }

    fun labelLoop() {
        loop@ for (i in 1..10) {
            for (j in 1..10) {
                if (i + j == 12) {
                    println("i: $i, j: $j")
                    break@loop
                }
            }
        }
    }

    tailrec fun binIndexOf(
        x: Int,
        array: IntArray,
        from: Int = 0,
        to: Int = array.size
    ): Int {
        if (from == to) return -1
        val midIndex = (from + to - 1) / 2
        val mid = array[midIndex]
        return when {
            mid < x -> binIndexOf(x, array, midIndex + 1, to)
            mid > x -> binIndexOf(x, array, from, midIndex)
            else -> midIndex
        }
    }
}