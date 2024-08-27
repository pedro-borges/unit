package org.pcb.units.amounts.generic

import org.pcb.units.domain.Currency
import org.pcb.units.domain.Rent
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class RentAmount(amount: Number,
                      val currency: Unit<Currency>,
                      val time: Unit<Time>)
    : Amount<Rent>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       units = mapOf(currency to 1,
                                     time to -1),
                       domain = Rent::class))
{
    operator fun times(that: TimeAmount) =
        when
        {
            time.cannon == that.unit.cannon ->
                CurrencyAmount(amount = amount * that.amount,
                               currency = currency)
            else                            ->
                CurrencyAmount(amount = amount / time.cannon * that.amount * that.unit.cannon,
                               currency = currency)
        }
}