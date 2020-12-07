/**
 * Day 07
 *
 * https://adventofcode.com/2020/day/7
 */
fun main() {
    val rules = readLinesFromResource("07.txt").parseLuggageRules()
    val candidate = "shiny gold"
    println("bags that can contain $candidate bags: ${rules.filterByCanHoldColor(candidate).size}")
    println("total bags that a $candidate bag holds: ${rules.bagSum(candidate)}")
}

typealias Color = String
typealias BagRules = Map<String, Int>

private fun List<String>.parseLuggageRules(): Map<Color, BagRules> = this.map { line ->
    val (color, childRules) = Regex("""(.+) bags contain (.+)\.$""").matchEntire(line)!!.destructured
    val children = when (childRules) {
        "no other bags" -> emptyMap()
        else -> childRules.split(',').map { child ->
            val (count, childColor) = Regex("""\s?(\d+) (.+) bags?""").matchEntire(child.trim())!!.destructured
            childColor to count.toInt()
        }.toMap()
    }
    color to children
}.toMap()

private fun Map<Color, BagRules>.filterByCanHoldColor(candidate: Color): List<Color> {
    fun BagRules.canHold(candidate: String): Boolean = when {
        this.containsKey(candidate) -> true
        else -> this.keys.any { this@filterByCanHoldColor[it]?.canHold(candidate) ?: false }
    }
    return this.filter { it.value.canHold(candidate) }.map { it.key }
}

private fun Map<Color, BagRules>.bagSum(candidate: Color): Int {
    fun BagRules.innerBagSum(): Int = this.entries.sumBy {
        val inner = this@bagSum[it.key]?.innerBagSum() ?: 0
        inner * it.value + it.value
    }
    return this[candidate]?.innerBagSum() ?: throw NoSuchElementException(candidate)
}

