/**
 * Day 02
 *
 * https://adventofcode.com/2020/day/2
 */
fun main() {
    val regex = "([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)".toRegex()

    val passwords = readLinesFromResource("02.txt").map {
        val m = regex.matchEntire(it)!!
        val (_, a, b, char, password) = m.groupValues
        Policy(char[0], a.toInt(), b.toInt()) to password
    }

    println("Valid passwords (1st policy): ${passwords.count { (policy, password) -> policy.isValid1(password) }}")
    println("Valid passwords (2nd policy): ${passwords.count { (policy, password) -> policy.isValid2(password) }}")
}

data class Policy(val char: Char, val a: Int, val b: Int)

fun Policy.isValid1(pass: String): Boolean {
    val count = pass.toCharArray().count { it == char }
    return count in a..b
}

fun Policy.isValid2(pass: String): Boolean {
    val chars = pass.toCharArray()
    val aIsValid = chars[a-1] == char
    val bIsValid = chars[b-1] == char
    return aIsValid xor bIsValid
}
