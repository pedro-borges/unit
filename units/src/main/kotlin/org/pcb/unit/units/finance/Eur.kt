package org.pcb.unit.units.finance

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.times
import org.pcb.unit.units.CurrencyAmount

class Eur(amount: Number,
          magnitude: Magnitude = UNI)
    : CurrencyAmount<Unit<Currency>>(
    amount = amount * magnitude.scale,
    unit = EUR)
{
    open class EurUnit(magnitude: Magnitude)
        : Unit<Currency>(Currency::class, "€", magnitude, CurrencyConverter[EUR])

    object EUR : EurUnit(UNI)
}