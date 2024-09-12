package org.pcb.unit.config

import org.pcb.unit.Amount
import org.pcb.unit.Unit

object PlainRenderer : Renderer
{
    override fun render(amount: Amount<*>) =
        amount.amount.stripTrailingZeros().toPlainString() + render(amount.unit.units)

    override fun render(units: Map<Unit<*>, Int>) =
        units.asSequence()
            .sortedByDescending { it.value }
            .map { it.key.toString() + it.value }
            .joinToString()

    override fun render(unit: Unit<*>): String
    {
        return unit.magnitude.symbol + unit.symbol
    }
}