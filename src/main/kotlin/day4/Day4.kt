package day4

import java.io.File

class Day4 {

    private val input = File("src/main/kotlin/day4/input.txt").readText().split("\n\n")

    fun puzzle1(): Int {
        return input.count {
            it.contains("byr")
                .and(it.contains("iyr"))
                .and(it.contains("eyr"))
                .and(it.contains("hgt"))
                .and(it.contains("hcl"))
                .and(it.contains("ecl"))
                .and(it.contains("pid"))
        }
    }

    fun puzzle2(): Int {
        return input.count {
            it.contains("byr:(19[2-9][0-9]|200[0-2])".toRegex())
                .and(it.contains("iyr:(201[0-9]|2020)".toRegex()))
                .and(it.contains("eyr:(202[0-9]|2030)".toRegex()))
                .and(it.contains("hgt:(1[5-8][0-9]cm|19[0-3]cm|59in|6[0-9]in|7[0-6]in)".toRegex()))
                .and(it.contains("hcl:#[a-f0-9]{6}".toRegex()))
                .and(it.contains("ecl:(amb|blu|brn|gry|grn|hzl|oth)".toRegex()))
                .and(it.contains("pid:[0-9]{9}(\\D|$)".toRegex()))
        }
    }
}