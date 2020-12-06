/**
 * Day 06
 *
 * https://adventofcode.com/2020/day/6
 */
fun main() {
    val groups = readFromResource("06.txt").split("\n\n").map { g -> g.split('\n').map { it.toSet() } }
    println("Sum of answer set sizes: ${groups.map { it.answersUnion() }.sumOf { it.size }}")
    println("Sum of answer set intersect sizes: ${groups.map { it.answersIntersect() }.sumOf { it.size }}")
}

private fun Iterable<Set<Char>>.answersUnion() = this.reduce { a, b -> a.union(b) }
private fun Iterable<Set<Char>>.answersIntersect() = this.reduce { a, b -> a.intersect(b) }
