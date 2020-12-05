import kotlin.math.pow

/**
 * Day 05
 *
 * https://adventofcode.com/2020/day/5
 */
fun main() {
    val seats = readLinesFromResource("05.txt").map { it.toSeat() }
    println("Max: ${seats.maxOf { it.id }}")
    println("Missing boarding pass: ${seats.findMissingSeatId()}")
}

data class Seat(val row: Int, val col: Int) {
    val id: Int
        get() = row * 8 + col
}

fun String.toSeat(): Seat {
    val chars = this.toCharArray()
    val row = chars.slice(0..6).map { it.BSPNode() }.toInt()
    val col = chars.slice(7..9).map { it.BSPNode() }.toInt()
    return Seat(row, col)
}

enum class BSPNode { A, B }

fun List<BSPNode>.toInt(): Int {
    var pSize = 2f.pow(this.size).toInt()
    var lower = 0

    for (node in this) {
        pSize /= 2
        if (node == BSPNode.B) lower += pSize
    }

    return lower
}

fun Char.BSPNode(): BSPNode {
    if (this == 'F' || this == 'L') return BSPNode.A
    return BSPNode.B
}

fun List<Seat>.findMissingSeatId(): Int {
    val sorted = this.sortedBy { it.id }
    var last = sorted.first().id
    for (seat in sorted.takeLast(sorted.size - 1)) {
        when (seat.id - last) {
            1 -> last = seat.id
            else -> break
        }
    }
    return last + 1
}
