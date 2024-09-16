package org.pcb.unit.amounts.finance

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.CurrencyAmount
import org.pcb.unit.dimensions.Currency
import org.pcb.unit.toBigDecimal

class Gbp(amount: Number,
          magnitude: Magnitude = UNI)
    : CurrencyAmount<Unit<Currency>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = GBP)
{
    open class GbpUnit(magnitude: Magnitude)
        : Unit<Currency>(Currency::class, "£", CurrencyConverter[GBP], magnitude)

    object GBP : GbpUnit(UNI)
}
