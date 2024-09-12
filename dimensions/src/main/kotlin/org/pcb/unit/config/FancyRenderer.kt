package org.pcb.unit.config

import org.pcb.unit.Amount
import org.pcb.unit.Unit
import org.pcb.unit.config.Color.CYAN
import org.pcb.unit.config.Color.NONE
import org.pcb.unit.config.Color.RED

object FancyRenderer : Renderer
{
    var separator = "﹒"
    var scaleColor = CYAN.iso
    var unitColor = NONE.iso
    var exponentColor = RED.iso
    var magnitudeScale = 2

    override fun render(amount: Amount<*>) =
        amount.amount.stripTrailingZeros().toPlainString() + render(amount.unit.units)

    override fun render(units: Map<Unit<*>, Int>) =
        units.asSequence()
            .sortedByDescending { it.value }
            .map { it.key.toString() + exponentColor + it.value.toPowerString() + NONE.iso }
            .joinToString(separator = separator)

    override fun render(unit: Unit<*>): String
    {
        return scaleColor + unit.magnitude.symbol + unitColor + unit.symbol
    }

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