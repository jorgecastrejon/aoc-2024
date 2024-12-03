fun main() {

    fun solveMultiplications(input: String): Int =
        "mul\\((\\d{1,3}),\\s*(\\d{1,3})\\)".toRegex()
            .findAll(input)
            .fold(0) { acc, match ->
                acc + (match.groups[1]?.value?.toInt() ?: 0) * (match.groups[2]?.value?.toInt() ?: 0)
            }

    fun part1(input: List<String>): Int =
        solveMultiplications(input.joinToString(""))

    fun part2(input: List<String>): Int =
        "don't\\(\\)(.*?)do\\(\\)".toRegex()
            .findAll(input.joinToString("")).toMutableList().reversed()
            .fold(input.joinToString("")) { text, range ->
                text.removeRange(range.range)
            }.let(::solveMultiplications)

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
