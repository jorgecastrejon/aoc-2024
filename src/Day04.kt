fun main() {

    fun check(array: Array<CharArray>, row: Int, column: Int, direction: Direction, vararg chars: Char): Int {
        for (i in chars.indices) {
            if (array.get(row + (i * direction.y), column + (i * direction.x)) != chars[i]) {
                return 0
            }
        }
        return 1
    }

    fun part1(input: List<String>): Int {
        val array: Array<CharArray> = input.map { line -> line.toCharArray() }.toTypedArray()
        var acc = 0

        for (row in array.indices) {
            for (column in array.first().indices) {

                if (array[row][column] == 'X') {
                    acc += Direction.entries.toTypedArray().sumOf { dir ->
                        check(array, row, column, dir, 'X', 'M', 'A', 'S')
                    }
                }

            }
        }

        return acc
    }

    fun part2(input: List<String>): Int {
        val array: Array<CharArray> = input.map { line -> line.toCharArray() }.toTypedArray()
        var acc = 0

        for (row in array.indices) {
            for (column in array.first().indices) {
                if (
                    check(array, row, column, Direction.DownRight, 'M', 'A', 'S') == 1
                    || check(array, row + 2, column + 2, Direction.UpLeft, 'M', 'A', 'S') == 1
                ) {
                    if (
                        check(array, row + 2, column, Direction.UpRight, 'M', 'A', 'S') == 1
                        || check(array, row, column + 2, Direction.DownLeft, 'M', 'A', 'S') == 1
                    ) {
                        acc += 1
                    }

                }
            }
        }

        return acc
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

private enum class Direction(val x: Int, val y: Int) {
    Up(0, -1), UpRight(1, -1), Right(1, 0), DownRight(1, 1),
    Down(0, 1), DownLeft(-1, 1), Left(-1, 0), UpLeft(-1, -1)
}
