package org.pcb.unit.config

@Suppress("unused")
enum class Color(code: Int)
{
    NONE(0),
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    MAGENTA(35),
    CYAN(36),
    WHITE(37);

    val iso = "\u001B[${code}m"
}