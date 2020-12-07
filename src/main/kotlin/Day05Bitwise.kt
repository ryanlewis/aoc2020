fun main() {
    val seats: List<Int> = readLinesFromResource("05.txt").map { it.toSeatNumber() }
    println("Max: ${seats.maxOrNull()}")
    println("Missing boarding pass: ${seats.sorted().zipWithNext().first { (a, b) -> b - a == 2 }.first+1}")
}

private fun String.toSeatNumber() = this.replace(Regex("[FL]"), "0").replace(Regex("[BR]"), "1").toInt(2)
