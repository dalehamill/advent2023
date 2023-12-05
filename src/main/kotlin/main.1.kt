import java.io.File

fun main(args: Array<String>) {
    var sumCalibrations = 0
    var sumCalibrations2 = 0
    File("src/main/resources/data.1.txt").forEachLine {
        sumCalibrations += findCalibrationValue(it)
        sumCalibrations2 += findCalibrationValue2(it)
    }

    println(sumCalibrations)
    println(sumCalibrations2)
}

fun findCalibrationValue(str: String): Int {
    var ret = 0
    ret += (str.firstOrNull { it.isDigit() }?.digitToInt() ?: 0) * 10
    ret += str.lastOrNull {it.isDigit() }?.digitToInt() ?: 0
    return ret
}

fun findCalibrationValue2(str: String): Int {
    val arr = arrayListOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    var arrReversed = ArrayList(arr.map { it.reversed() })
    var ret = 0

    ret += searchString(str, arr) * 10
    ret += searchString(str.reversed(), arrReversed)

    return ret
}

private fun searchString(str: String, arr: ArrayList<String>): Int {
    str.forEachIndexed { index, c ->
        if (c.isDigit()) return c.digitToInt()
        arr.forEachIndexed { arr_index, arr_str ->
            if (arr_str.length + index <= str.length && str.substring(index, index + arr_str.length) == arr_str) return (arr_index + 1)
        }
    }
    return 0
}