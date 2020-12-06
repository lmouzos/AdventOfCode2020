package day6

import java.io.File

class Day6 {

    private val input = File("src/main/kotlin/day6/input.txt").readText().split("\n\n")

    fun puzzle1(): Int {
        return input.map { it.toSet().count { ch -> ch != '\n' } }.sum()
    }

    fun puzzle2(): Int {
        return input.map {
            it.split('\n')
                .map { chr -> chr.toSet() }
                .reduce { acc, chr -> acc.intersect(chr) }
                .count()
        }.sum()
    }
}