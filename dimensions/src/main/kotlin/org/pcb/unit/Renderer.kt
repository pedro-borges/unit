package org.pcb.unit

import org.pcb.unit.Renderer.Color.CYAN
import org.pcb.unit.Renderer.Color.NONE
import org.pcb.unit.Renderer.Color.RED

object Renderer
{
    var separator = "﹒"
    var scaleColor = CYAN
    var unitColor = NONE
    var exponentColor = RED

    enum class Color(val iso: String)
    {
        NONE("\u001b[0m"),
        BLACK("\u001b[30m"),
        RED("\u001b[31m"),
        GREEN("\u001b[32m"),
        YELLOW("\u001b[33m"),
        BLUE("\u001b[34m"),
        MAGENTA("\u001b[35m"),
        CYAN("\u001b[36m"),
        WHITE("\u001b[37m"),
    }

    fun render(units: Map<Unit<*>, Int>) =
        units.asSequence()
            .sortedByDescending { it.value }
            .map { scaleColor.iso + it.key.magnitude.symbol + unitColor.iso + it.key.symbol + exponentColor.iso + it.value.toPowerString() + NONE.iso }
            .joinToString(separator = separator)

    private fun Int.toPowerString() =
        when
        {
            this == 1 -> ""
            this == 0 -> throw IllegalStateException("Unexpected power of 0")
            else      -> toSuperscript()
        }

    private fun Int.toSuperscript() =
        toString()
            .replace('-', '⁻')
            .replace('0', '⁰')
            .replace('1', '¹')
            .replace('2', '²')
            .replace('3', '³')
            .replace('4', '⁴')
            .replace('5', '⁵')
            .replace('6', '⁶')
            .replace('7', '⁷')
            .replace('8', '⁸')
            .replace('9', '⁹')
}