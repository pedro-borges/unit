package org.pcb.unit.amount.finance

import org.pcb.unit.Unit
import org.pcb.unit.amount.CurrencyAmount
import org.pcb.unit.amount.finance.Eur.EUR
import org.pcb.unit.dimension.Currency
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Eur(amount: Number)
    : CurrencyAmount<EUR>(amount.toBigBigDecimal(), EUR)
{
    object EUR : Unit<Currency>(Currency::class)
    {
        override val symbol = "EUR"
        override val scale = CurrencyConverter[this]
    }
}