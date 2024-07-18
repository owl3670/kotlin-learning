package function

class Conditional {
    fun hexDigit(n: Int): Char {
        when {
            n in 0..9 -> return '0' + n
            n in 10..15 -> return 'A' + n - 10
            else -> return '?'
        }
    }

    fun hexDigit2(n: Int): Char = when (n) {
        in 0..9 -> '0' + n
        in 10..15 -> 'A' + n - 10
        else -> '?'
    }

    fun isPrimeBelow10(n: Int): Boolean = when(n) {
        2, 3, 5, 7 -> true
        else -> false
    }
}