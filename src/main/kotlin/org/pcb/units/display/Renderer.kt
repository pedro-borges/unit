package org.pcb.units.display

import org.pcb.units.unit.Unit

object Renderer
{
    var separator = "﹒"

    fun render(units: Map<Unit<*>, Int>) =
        units.asSequence()
            .sortedByDescending { it.value }
            .map { it.key.symbol + it.value.toPowerString() }
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