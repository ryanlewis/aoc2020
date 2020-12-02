/**
 * Day 01
 *
 * https://adventofcode.com/2020/day/1
 */
fun main() {
    val expenses = readLinesFromResource("01.txt").map { it.toInt() }

    for (i in expenses.indices) {
        val c1 = expenses[i]

        for (j in i+1 until expenses.size) {
            val c2 = expenses[j]
            if (c1 + c2 == 2020) {
                println("with 2 expenses: ${c1 * c2}")
            }

            for (k in j+1 until expenses.size) {
                val c3 = expenses[k]
                if (c1 + c2 + c3 == 2020) println("with 3 expenses: ${c1 * c2 * c3}")
            }
        }
    }
}
