package org.pcb.unit.units.finance

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.units.CurrencyAmount

class Eur(amount: Number)
    : CurrencyAmount<Unit<Currency>>(
    amount = amount,
    unit = EUR)
{
    object EUR : Unit<Currency>(Currency::class)
    {
        override val symbol = "EUR"
        override val scale = CurrencyConverter[this]
    }
}