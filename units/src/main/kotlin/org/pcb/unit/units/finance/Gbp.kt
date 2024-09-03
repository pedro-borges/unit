package org.pcb.unit.units.finance

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.CurrencyAmount
import org.pcb.unit.units.finance.Gbp.GBP

@Suppress("unused")
class Gbp(amount: Number)
    : CurrencyAmount<GBP>(amount.toBigBigDecimal(), GBP)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object GBP : Unit<Currency>(Currency::class)
    {
        override val symbol = "GBP"
        override val scale = 1.toBigBigDecimal()
    }
}