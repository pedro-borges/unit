package org.pcb.unit.config

import org.pcb.unit.Amount
import org.pcb.unit.Unit
import org.pcb.unit.config.Color.CYAN
import org.pcb.unit.config.Color.NONE
import org.pcb.unit.config.Color.RED
import org.pcb.unit.toPowerString

class FancyRenderer(
        var magnitudeSeparator: String = "",
        var amountSeparator: String = " ",
        var unitSeparator: String = "﹒",
        var scalePrefix: String = CYAN.iso,
        var unitPrefix: String = NONE.iso,
        var exponentPrefix: String = RED.iso)
    : Renderer
{

    override fun render(amount: Amount<*>) =
        amount.amount.stripTrailingZeros().toPlainString() + amountSeparator + render(amount.unit.units)

    override fun render(units: Map<Unit<*>, Int>) =
        units.asSequence()
            .sortedByDescending { it.value }
            .map { render(it.key) + exponentPrefix + it.value.toPowerString() + NONE.iso }
            .joinToString(separator = unitSeparator)

    override fun render(unit: Unit<*>): String
    {
        return scalePrefix + unit.magnitude.symbol + magnitudeSeparator + unitPrefix + unit.symbol
    }
}