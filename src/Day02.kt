fun main() {
    fun resolve(levels: List<Int>): Boolean =
        levels.zipWithNext { a, b -> a - b in 1 until 4  }.all { it }
                || levels.zipWithNext { a, b -> a - b in -1 downTo  -3  }.all { it }

    fun part1(input: List<String>): Int =
        input.map { line -> resolve(line.split(" ").map(String::toInt)) }.count { it }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            val levels = line.split(" ").map(String::toInt)
            var isSafe = false
            var index = 0

            while (index < levels.size && !isSafe) {
                val newLevel = levels.toMutableList()
                newLevel.removeAt(index++)
                isSafe = resolve(newLevel)
            }
            isSafe
        }.count { it }
    }

    val testInput = readInput("Day02_test")
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
