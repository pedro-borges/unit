package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Currency
import org.pcb.units.domain.Rent
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class RentAmount<C : Unit<Currency>, T : Unit<Time>>(
        amount: Number,
        val currency: C,
        val time: T)
    : Amount<Rent>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Rent::class,
                       units = listOf(currency to 1,
                                      time to -1)))
{
    // region Rent    

    operator fun plus(that: RentAmount<C, *>) =
        RentAmount(amount = amount + that.amount * convert(that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("plusCT")
    operator fun plus(that: RentAmount<C, T>) =
        RentAmount(amount = amount + that.amount,
                   currency = currency,
                   time = time)

    operator fun minus(that: RentAmount<C, *>) =
        RentAmount(amount = amount - that.amount * convert(that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("minusCT")
    operator fun minus(that: RentAmount<C, T>) =
        RentAmount(amount = amount - that.amount,
                   currency = currency,
                   time = time)

    // endregion

    // region Scalar

    operator fun div(that: RentAmount<C, *>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time))

    @JvmName("divCT")
    operator fun div(that: RentAmount<C, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Currency

    operator fun times(that: TimeAmount<*>) =
        CurrencyAmount(amount = amount * that.amount * convert(that.time to time),
                       currency = currency)

    @JvmName("timesT")
    operator fun times(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount,
                       currency = currency)

    // endregion

    // region Frequency

    operator fun div(that: CurrencyAmount<C>) =
        FrequencyAmount(amount = amount / that.amount,
                        time = time)

    // endregion
}