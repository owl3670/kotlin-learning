package function

import java.lang.NumberFormatException

class Exception {
    fun parseIntNumber(s: String): Int {
        var num = 0

        if (s.length !in 1..31) throw NumberFormatException("Not a number: $s")

        for (c in s) {
            if (c !in '0'..'1') throw NumberFormatException("Not a number:$s")
            num = num * 2 + (c - '0')
        }

        return num
    }

    fun readInt(default: Int): Int {
        try {
            return readLine()!!.toInt()
        } catch (e: NumberFormatException) {
            return default
        }
    }

    fun readlnt2(default: Int) = try {
        readLine()!!.toInt()
    } catch (e: NumberFormatException) {
        default
    }

    fun readInt3(default: Int) = try {
        readLine()!!.toInt()
    } finally {
        println("Error")
    }
}