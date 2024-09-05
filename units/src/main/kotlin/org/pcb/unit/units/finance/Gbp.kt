package org.pcb.unit.units.finance

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.CurrencyAmount

class Gbp(amount: Number)
    : CurrencyAmount<Unit<Currency>>(
    amount = amount,
    unit = GBP)
{
    object GBP : Unit<Currency>(Currency::class)
    {
        override val symbol = "GBP"
        override val scale = 1.toBigBigDecimal()
    }
}