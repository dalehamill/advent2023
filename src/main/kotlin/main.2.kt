import java.io.File

fun main(args: Array<String>) {
    var sumPossible = 0
    var sumPowers = 0L

    File("src/main/resources/data.2.txt").forEachLine {
        val gameId = it.substring("Game ".length, it.indexOf(":")).toInt()
        val gameInputs = it.substring(it.indexOf(":") + 1).split(";")
        if (isGamePossible(gameId, gameInputs)) sumPossible += gameId
        sumPowers += getPower(gameInputs)
    }

    println(sumPossible)
    println(sumPowers)
}

private fun getPower(inputs: List<String>): Long {
    var maxRed = 0
    var maxGreen = 0
    var maxBlue = 0

    inputs.forEach { it.split(",").forEach {
            val parts = it.trim().split(" ")
            val cnt = parts[0].trim().toInt()
            val colour = parts[1].trim()

            if (colour == "red" && cnt > maxRed) maxRed = cnt
            if (colour == "blue" && cnt > maxBlue) maxBlue = cnt
            if (colour == "green" && cnt > maxGreen) maxGreen = cnt
        }
    }

    return maxRed.toLong() * maxGreen * maxBlue
}

private fun isGamePossible(gameId: Int, inputs: List<String>): Boolean {
    inputs.forEach { if (!isGamePossible(it)) return false }
    return true
}

private fun isGamePossible(input: String): Boolean {
    val maxRed = 12
    val maxGreen = 13
    val maxBlue = 14

    input.split(",").forEach {
        val parts = it.trim().split(" ")
        val cnt = parts[0].trim().toInt()
        val colour = parts[1].trim()

        if (colour == "red" && cnt > maxRed) return false
        if (colour == "blue" && cnt > maxBlue) return false
        if (colour == "green" && cnt > maxGreen) return false
    }

    return true
}