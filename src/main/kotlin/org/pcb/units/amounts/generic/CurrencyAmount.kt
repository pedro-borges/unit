package org.pcb.units.amounts.generic

import org.pcb.units.domain.Currency
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class CurrencyAmount(amount: Number,
                          val currency: Unit<Currency>)
    : Amount<Currency>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           unit = currency,
                           domain = Currency::class))
{
        operator fun div(that: TimeAmount) =
            RentAmount(amount = amount / that.amount,
                       currency = currency,
                       time = that.unit)

        operator fun times(that: TimeAmount) =
            DebtAmount(amount = amount * that.amount,
                       currency = currency,
                       time = that.unit)
}
