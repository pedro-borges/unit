package org.pcb.unit.amount.finance

import org.pcb.unit.Unit
import org.pcb.unit.amount.CurrencyAmount
import org.pcb.unit.amount.finance.Gbp.GBP
import org.pcb.unit.dimension.Currency
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Gbp(amount: Number)
    : CurrencyAmount<GBP>(amount.toBigBigDecimal(), GBP)
{
    object GBP : Unit<Currency>(Currency::class)
    {
        override val symbol = "GBP"
        override val scale = 1.toBigBigDecimal()
    }
}