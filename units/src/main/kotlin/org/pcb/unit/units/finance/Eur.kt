package org.pcb.unit.units.finance

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.CurrencyAmount

class Eur(amount: Number,
          magnitude: Magnitude = UNI)
    : CurrencyAmount<Unit<Currency>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = EUR)
{
    open class EurUnit(magnitude: Magnitude)
        : Unit<Currency>(Currency::class, "€", CurrencyConverter[EUR], magnitude)

    object EUR : EurUnit(UNI)
}
