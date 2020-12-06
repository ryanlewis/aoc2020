class Utils

/**
 * Read lines from resource and return as a list of strings
 */
fun readLinesFromResource(name: String, skipBlankLines: Boolean = true): List<String> =
    Utils::class.java.getResource(name).openStream().bufferedReader().readLines().filter {
        if (skipBlankLines) it.isNotBlank() else true
    }
