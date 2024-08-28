package org.pcb.units.amounts.generic

import org.pcb.units.domain.Currency
import org.pcb.units.domain.Rent
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class RentAmount<C : Unit<Currency>, T : Unit<Time>>(
        amount: Number,
        val currency: C,
        val time: T)
    : Amount<Rent>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       units = mapOf(currency to 1,
                                     time to -1),
                       domain = Rent::class))
{
    operator fun times(that: TimeAmount<*>) =
        CurrencyAmount(amount = amount / time.cannon * that.amount * that.unit.cannon,
                       currency = currency)

    @JvmName("timesT")
    operator fun times(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount,
                       currency = currency)
}