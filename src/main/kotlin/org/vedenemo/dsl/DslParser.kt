package org.vedenemo.dsl

fun parseLine(line: String): ModelCommand? {
    val trimmed = line.trim()
    if (trimmed.startsWith("#") || trimmed.isBlank()) return null
    return when {
        trimmed.startsWith("entity") -> {
            val parts = Regex("entity \"(.+)\" named \"(.+)\"").find(trimmed)?.groupValues
            parts?.let {
                AddEntityCommand(Entity(it[1], it[2]))
            }
        }
        trimmed.contains("addAttribute") -> {
            val parts = Regex("\"(.+)\" addAttribute \"(.+)\" ofType (\\w+) since (\\d+\\.\\d+)").find(trimmed)?.groupValues
            parts?.let {
                AddAttributeCommand(it[1], Attribute(it[2], it[2], DataType.valueOf(it[3]), Version.parse(it[4])))
            }
        }
        else -> null
    }
}

fun loadScript(lines: List<String>): CommandGroup {
    val commands = lines.mapNotNull { parseLine(it) }
    return CommandGroup("Loaded script", commands)
}