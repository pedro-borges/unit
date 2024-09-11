package org.pcb.unit.units.finance

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.units.CurrencyAmount

class Gbp(amount: Number,
          magnitude: Magnitude = UNI)
    : CurrencyAmount<Unit<Currency>>(
    amount = amount,
    unit = GbpUnit(magnitude))
{
    open class GbpUnit(magnitude: Magnitude)
        : Unit<Currency>(Currency::class, magnitude.symbol + "£", magnitude.scale * CurrencyConverter[GBP])

    object GBP : GbpUnit(UNI)
}