package org.pcb.units.amount.finance

import org.pcb.units.Unit
import org.pcb.units.amount.CurrencyAmount
import org.pcb.units.amount.finance.Eur.EUR
import org.pcb.units.domain.Currency
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Eur(amount: Number)
    : CurrencyAmount<EUR>(amount.toBigBigDecimal(), EUR)
{
    object EUR : Unit<Currency>(Currency::class)
    {
        override val symbol = "EUR"
        override val scale = 1.toBigBigDecimal()
    }
}