package day1

import java.io.File

class Day1 {

    private val input = File("src/main/kotlin/day1/input.txt").readLines().map { it.toInt() }.sorted()

    fun puzzle1(): Int {
        return input.mapNotNull { i ->
            input.filter { j -> (i + j == 2020) }.map { j -> i * j }.distinct().getOrElse(0) { null }
        }[0]
    }

    fun puzzle2(): Int {
        return input.map { i ->
           input.mapNotNull { j ->
                input.filter { k -> i + j + k == 2020 }.map { k -> i * j * k }.distinct().getOrElse(0) { null }
           }.distinct()
        }[0][0]
    }
}