package org.pcb.unit.units.finance

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.CurrencyAmount
import org.pcb.unit.units.finance.Eur.EUR

@Suppress("unused")
class Eur(amount: Number)
    : CurrencyAmount<EUR>(amount.toBigBigDecimal(), EUR)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object EUR : Unit<Currency>(Currency::class)
    {
        override val symbol = "EUR"
        override val scale = CurrencyConverter[this]
    }
}