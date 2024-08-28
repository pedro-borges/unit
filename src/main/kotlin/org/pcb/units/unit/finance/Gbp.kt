package org.pcb.units.unit.finance

import org.pcb.units.domain.Currency
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.Unit

class Gbp : Unit<Currency>(Currency::class)
{
    override val symbol = "GBP"
    override val cannon = 1.0.toBigDecimal()

    companion object
    {
        val GBP = Gbp()
    }
}