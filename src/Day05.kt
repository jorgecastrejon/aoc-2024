fun main() {

    fun parseInput(input: List<String>): Pair<HashMap<String, MutableSet<String>>, List<String>> {
        val (rules, update) = input.partition { it.contains("|") }

        return rules.fold(HashMap<String, MutableSet<String>>()) { hash, rule ->
            val (prev, next) = rule.split("|")
            hash.getOrPut(prev) { mutableSetOf() }.add(next)

            hash
        } to update.drop(1)
    }

    fun resolve(
        hash: HashMap<String, MutableSet<String>>,
        updates: List<String>,
        op: (Boolean, List<String>) -> String?
    ): Int =
        updates.sumOf { line ->
            val numbers = line.split(",").let { numbers ->
                val allCorrect = numbers.zipWithNext { a, b -> hash[a]?.contains(b) == true }.all { it }

                op(allCorrect, numbers)
            }
            numbers?.toInt() ?: 0
        }

    fun part1(input: List<String>): Int {
        val (hash, update) = parseInput(input)

        return resolve(hash, update) { allCorrect, numbers ->
            if (allCorrect) numbers[numbers.size / 2] else null
        }
    }

    fun part2(input: List<String>): Int {
        val (hash, update) = parseInput(input)

        return resolve(hash, update) { allCorrect, numbers ->
            if (!allCorrect) {
                val newNumbers = numbers.sortedWith { p0, p1 ->
                    when {
                        hash[p0]?.contains(p1) == true -> 1
                        hash[p1]?.contains(p0) == true -> -1
                        else -> 0
                    }
                }
                newNumbers[newNumbers.size / 2]
            } else {
                null
            }
        }
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
