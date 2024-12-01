import java.util.*
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val left = PriorityQueue<Int>()
        val right = PriorityQueue<Int>()

        input.forEach { line ->
            val (a, b) = line.split("   ")
            left.add(a.toInt())
            right.add(b.toInt())
        }

        var totalDistance  = 0

        while (!left.isEmpty()) {
            totalDistance += abs(left.remove() - right.remove())
        }

        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableMapOf<Int, Int>()

        input.forEach { line ->
            val (a, rawB) = line.split("   ")
            left.add(a.toInt())
            val b = rawB.toInt()
            right[b] = right.getOrDefault(b, 0) + 1
        }

        return left.sumOf { number -> number * right.getOrDefault(number,0) }
    }

    val testInput = readInput("Day01_test")
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
