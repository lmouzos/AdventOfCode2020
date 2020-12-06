package day2

import java.io.File

class Day2 {
    private val input = File("src/main/kotlin/day2/input.txt").readLines()

    fun puzzle1(): Int {
        return input.map { line ->
            val parts = line.split(" ")
            val limits = parts[0].split('-').map { it.toInt() } // 11, 15
            val symbol = parts[1].trim(':')[0] // a
            val pass = parts[2] // aaaaaaaaaasdasda
            val times = pass.count { it == symbol }
            times >= limits[0] && times <= limits[1]
        }.count { it }
    }

    fun puzzle2(): Int {
        return input.map { line ->
            val parts = line.split(" ")
            val positions = parts[0].split('-').map { it.toInt() }.map { it - 1 } // 11, 15
            val symbol = parts[1].trim(':')[0] // a
            val pass = parts[2] // aaaaaaaaaasdasda
            (pass[positions[0]] == symbol && pass[positions[1]] != symbol)
                    || (pass[positions[0]] != symbol && pass[positions[1]] == symbol)
        }.count { it }
    }
}