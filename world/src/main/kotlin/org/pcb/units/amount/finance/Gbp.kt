package org.pcb.units.amount.finance

import org.pcb.units.Unit
import org.pcb.units.amount.CurrencyAmount
import org.pcb.units.amount.finance.Gbp.GBP
import org.pcb.units.domain.Currency
import org.pcb.units.toBigBigDecimal

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