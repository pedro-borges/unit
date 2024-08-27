package org.pcb.units.display

import org.pcb.units.unit.Unit

object Renderer
{
    var superscriptPowers = true

    fun render(units: Map<Unit<*>, Int>) =
        units.asSequence()
            .sortedByDescending { it.value }
            .fold("") { accumulator, domainPower ->
                accumulator + "﹒" + domainPower.key.symbol + domainPower.value.toPowerString()
            }

    private fun Int.toPowerString() =
        when
        {
            this == 1 -> ""
            this == 0 -> throw IllegalStateException("Unexpected power of 0")
            else      -> if (superscriptPowers) toSuperscript() else this
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