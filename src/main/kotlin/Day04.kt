/**
 * Day 04
 *
 * https://adventofcode.com/2020/day/4
 */
fun main() {
    val passports = readPassports()
    println("Valid passports with correct fields: ${passports.count { it.hasRequiredFields() }}")
    println("Valid passports with valid fields: ${passports.count { it.hasRequiredFields() && it.hasValidFields() }}")
}

fun readPassports(name: String = "04.txt"): List<Map<String, String>> {
    val contents = Utils::class.java.getResource(name).openStream().bufferedReader().readText().trim()
    val chunks: List<String> = contents.split("\n\n")
    return chunks.map { it.toPassport() }
}

fun String.toPassport(): Map<String, String> {
    val chunks = this.replace('\n', ' ').split(' ')
    return chunks.map { it.split(':').let { (key, value) -> key to value } }.toMap()
}

fun Map<String, String>.hasRequiredFields() = when (keys.size) {
    8 -> true
    7 -> !keys.contains("cid")
    else -> false
}

val validHairColor = "#([a-f0-9]{6})".toRegex()
val validPid = "[0-9]{9}".toRegex()
val validEyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

fun Map<String, String>.hasValidFields() = this.all { (key, value) ->
    when (key) {
        "byr" -> value.toInt() in 1920..2002
        "iyr" -> value.toInt() in 2010..2020
        "eyr" -> value.toInt() in 2020..2030
        "hgt" -> {
            val unit = value.substring(value.length - 2, value.length)
            val num = value.replace(unit, "").toIntOrNull() ?: -1
            when (unit) {
                "cm" -> num in 150..193
                "in" -> num in 59..76
                else -> false
            }
        }
        "hcl" -> validHairColor.matches(value)
        "ecl" -> validEyeColors.contains(value)
        "pid" -> validPid.matches(value)
        "cid" -> true
        else -> false
    }
}
