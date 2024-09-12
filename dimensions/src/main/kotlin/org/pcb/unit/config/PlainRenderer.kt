package org.pcb.unit.config

import org.pcb.unit.Amount
import org.pcb.unit.Unit
import org.pcb.unit.toSuperscript

class PlainRenderer : Renderer
{
    override fun render(amount: Amount<*>) =
        amount.amount.stripTrailingZeros().toPlainString() + " " + render(amount.unit.units)

    override fun render(units: Map<Unit<*>, Int>) =
        units.asSequence()
            .sortedByDescending { it.value }
            .map { render(it.key) + it.value.toSuperscript() }
            .joinToString(separator = "")

    override fun render(unit: Unit<*>): String
    {
        return unit.magnitude.symbol + unit.symbol
    }
}