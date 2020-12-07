package day7

import java.io.File

class Day7 {

    private val input = File("src/main/kotlin/day7/input.txt").readLines()

    fun puzzle1(): Int {
        val map = input.map { ln ->
            val line = ln.split(" contain ")
            line[0].trimEnd('s') to line[1].trimEnd('.').split(", ")
                .map { it.replace("\\d\\s+".toRegex(), "").trimEnd('s') }.toSet()
        }.toMap()
        return map.count { (_, value) -> hasShinyGoldBag(map, value) }
    }

    private fun hasShinyGoldBag(bags: Map<String, Set<String>>, value: Set<String>): Boolean {
        if (value.any { it == "shiny gold bag" }) {
            return true
        }
        if (value.all { it == "no other bag" }) {
            return false
        }
        return value.any { hasShinyGoldBag(bags, bags.getValue(it)) }
    }

    class Bag(
        val num: Int,
        val name: String
    )

    fun puzzle2(): Int {
        val bags = input.map { ln ->
            val line = ln.split(" contain ")
            line[0].trimEnd('s') to
                    line[1].trimEnd('.').split(", ")
                        .map {
                            val name = it.replace("\\d\\s+".toRegex(), "").trimEnd('s')
                            val num = if (name == "no other bag") 0 else Character.getNumericValue(it[0])
                            Bag(num, name)
                        }.filterNot { it.name == "no other bag" }.toSet()
        }.toMap()
        return bags.getValue("shiny gold bag").map { counter(it.num, bags.getValue(it.name), bags) }.sum()
    }

    private fun counter(num: Int, subset: Set<Bag>, bags: Map<String, Set<Bag>>): Int {
        if (subset.isEmpty()) {
            return num
        }
        return num + subset.map {
            num * counter(it.num, bags.getValue(it.name), bags)
        }.sum()
    }
}