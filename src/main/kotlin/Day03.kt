/**
 * Day 03
 *
 * https://adventofcode.com/2020/day/3
 */
fun main() {
    val map: List<List<Boolean>> = readLinesFromResource("03.txt").parse()
    println("Encountered trees: ${map.traverse(3, 1)}")

    val slopeCounts = listOf(
        map.traverse(1, 1),
        map.traverse(3, 1),
        map.traverse(5, 1),
        map.traverse(7, 1),
        map.traverse(1, 2)
    )
    println("Product of all slope counts: ${slopeCounts.reduce { acc, i -> acc * i } }")
}

fun List<String>.parse(): List<List<Boolean>> = this.map { it.toCharArray().map { c -> c == '#' } }

fun List<List<Boolean>>.traverse(rows: Int, cols: Int): Int {
    val rowSize = this[0].size
    var cx = 0
    var cy = 0
    var count = 0

    while ((cy + cols) < this.size) {
        cx += rows
        cy += cols

        // need to wrap?
        if (cx > rowSize-1) cx -= rowSize

        if (this[cy][cx]) count += 1
    }

    return count
}
