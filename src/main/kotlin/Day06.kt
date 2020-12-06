/**
 * Day 06
 *
 * https://adventofcode.com/2020/day/6
 */
fun main() {
    val groups = readLinesFromResource("06.txt", skipBlankLines = false).partitionedBySeparator { it.isBlank() }
    println("Sum of answer set sizes: ${groups.map { it.toAnswerSet() }.sumOf { it.size }}")
    println("Sum of answer set intersect sizes: ${groups.map { it.toAnswerIntersect() }.sumOf { it.size }}")
}

private fun <T> List<T>.partitionedBySeparator(separator: (T) -> Boolean): List<List<T>> {
    var remaining = this
    val sequence = generateSequence {
        if (remaining.isEmpty()) return@generateSequence null
        val index = remaining.indexOfFirst(separator).let { if (it == -1) remaining.size else it }
        remaining.slice(0 until index).also { remaining = remaining.drop(index + 1) }
    }
    return sequence.toList()
}

private fun List<String>.toAnswerSet() = this.flatMap { answers -> answers.toList() }.toSet()

private fun List<String>.toAnswerIntersect(): Set<Char> {
    val answerSet = this.toAnswerSet()
    return map { a -> a.toList() }.fold(answerSet) { acc, entry -> acc.intersect(entry) }
}
