package day4

import java.io.File

class Day4 {

    private val input = File("src/main/kotlin/day4/input.txt").readLines()

    fun puzzle1(): Int {
        return getPassports().count {
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
        return getPassports().filter {
            it.contains("byr")
                .and(it.contains("iyr"))
                .and(it.contains("eyr"))
                .and(it.contains("hgt"))
                .and(it.contains("hcl"))
                .and(it.contains("ecl"))
                .and(it.contains("pid"))
        }.map { it ->
            it.split(' ')
                .map { item ->
                    val i = item.split(':')
                    when (i[0]) {
                        "byr" -> try {
                            i[1].toInt() in 1920..2002
                        } catch (e: NumberFormatException) {
                            false
                        }
                        "iyr" -> try {
                            i[1].toInt() in 2010..2020
                        } catch (e: NumberFormatException) {
                            false
                        }
                        "eyr" -> try {
                            i[1].toInt() in 2020..2030
                        } catch (e: NumberFormatException) {
                            false
                        }
                        "hgt" -> "^(1[5-9][0-9]cm|[5-7][0-9]in)$".toRegex().matches(i[1])
//                        {when {
//                                i[1].endsWith("cm") -> {
//                                    try {
//                                        i[1].trimEnd('c', 'm').toInt() in 150..193
//                                    } catch (e: NumberFormatException) {
//                                        false
//                                    }
//                                }
//                                i[1].endsWith("in") -> {
//                                    try {
//                                        i[1].trimEnd('i', 'n').toInt() in 59..76
//                                    } catch (e: NumberFormatException) {
//                                        false
//                                    }
//                                }
//                                else -> false
//                            }}
                        "hcl" -> "^#([a-f0-9]{6})$".toRegex().matches(i[1]) // hex color regex
                        "ecl" -> "^(amb|blu|brn|gry|grn|hzl|oth)$".toRegex().matches(i[1])
                        "pid" -> "^[0-9]{9}$".toRegex().matches(i[1]) //i[1].length == 9 && i[1].filter { it.isDigit() }.count() == 9 // all 9 chars are digits
                        else -> false
                    }
                }.count { it }
        }.count { it == 7 }
    }

    private fun getPassports(): List<String> {
        val passports = mutableMapOf<Int, String>()
        var ir = 0
        input.forEach { s ->
            if (s.isEmpty()) {
                ir++
            }
            passports[ir] = passports[ir]?.plus(" $s") ?: s
        }
        return passports.values.toList()
    }
}