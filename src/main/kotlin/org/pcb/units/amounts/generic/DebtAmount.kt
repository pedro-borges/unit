package org.pcb.units.amounts.generic

import org.pcb.units.domain.Currency
import org.pcb.units.domain.Debt
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class DebtAmount<C : Unit<Currency>, T : Unit<Time>>(
        amount: Number,
        val currency: C,
        val time: T)
    : Amount<Debt>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       units = mapOf(currency to 1,
                                     time to -1),
                       domain = Debt::class))
{
    operator fun div(that: TimeAmount<*>) =
        CurrencyAmount(amount = amount / time.cannon * that.amount * that.unit.cannon,
                       currency = currency)

    @JvmName("divT")
    operator fun div(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount,
                       currency = currency)
}