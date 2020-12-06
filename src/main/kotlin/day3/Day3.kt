package day3

import java.io.File

class Day3 {

    private val input = File("src/main/kotlin/day3/input.txt").readLines()

    fun puzzle1(): Int {
        return input.mapIndexed { index, s ->
            if (index == 0) {
                false
            } else {
                var rightPos = index * 3
                while (rightPos > s.length - 1) {
                    rightPos -= s.length
                }
                println("$rightPos $index")
                s[rightPos] == '#'
            }
        }.count { it }
    }

    fun puzzle2(): Long {
        val slopeRight = listOf(1, 3, 5, 7, 1)
        val slopeDown = listOf(1, 1, 1, 1, 2)
        return slopeRight.mapIndexed { indexE, right ->
            val down = slopeDown[indexE]
            input.mapIndexed { index, s ->
                if (index == 0) {
                    false
                } else {
                    if (down == 2) {
                        if (index % down != 0) {
                            false
                        } else {
                            var rightPos = index / 2
                            while (rightPos > s.length - 1) {
                                rightPos -= s.length
                            }
                            s[rightPos] == '#'
                        }
                    } else {
                        var rightPos = index * right
                        while (rightPos > s.length - 1) {
                            rightPos -= s.length
                        }
                        s[rightPos] == '#'
                    }
                }
            }.count { it }.toLong()
        }.reduce { acc, i -> acc * i }
    }
}