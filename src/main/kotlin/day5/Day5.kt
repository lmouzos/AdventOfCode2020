package day5

import java.io.File

class Day5 {

    private val input = File("src/main/kotlin/day5/input.txt").readLines()

    fun puzzle1(): Int? {
        return existingSeats().maxOrNull()
    }

    fun puzzle2(): List<Int> {
        val exist = existingSeats()
        val excluded = allSeats().filterNot { exist.contains(it) }.sorted()
        return excluded
            .filterIndexed { index, l ->
                l - 1 != excluded.getOrElse(index - 1) { excluded[index] } &&
                l + 1 != excluded.getOrElse(index + 1) { excluded[index] }
            }
    }

    private fun allSeats(): List<Int> {
        val rows = IntArray(128) { it * 8 }
        val columns = IntArray(8) { it }
        val l = mutableListOf<Int>()
        rows.forEach { i ->
            columns.forEach { j ->
                l.add(i + j)
            }
        }
        return l.toList()
    }

    private fun existingSeats(): List<Int> {
        val rows = IntArray(128) { it }
        val columns = IntArray(8) { it }
        return input.map { line ->
            val rowI = recursive(line.subSequence(0..6), rows)
            val columnI = recursive(line.subSequence(7..9), columns)
            rowI * 8 + columnI
        }
    }

    private fun recursive(sequence: CharSequence, arr: IntArray): Int {
        val current = sequence[0]
        val rest = sequence.subSequence(1 until sequence.length)
        return if (current == 'B' || current == 'R') {
            if (arr.size <= 2) {
                return arr[1]
            }
            recursive(rest, arr.copyOfRange(arr.size / 2, arr.size))
        } else {
            if (arr.size <= 2) {
                return arr[0]
            }
            recursive(rest, arr.copyOfRange(0, arr.size / 2))
        }
    }
}