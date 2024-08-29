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

    operator fun <T : Unit<Time>> plus(that: RentAmount<C, T>) =
        RentAmount(amount = amount + that.amount * convert(that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("plusRent")
    operator fun plus(that: RentAmount<C, T>) =
        RentAmount(amount = amount + that.amount,
                   currency = currency,
                   time = time)

    operator fun <T : Unit<Time>> minus(that: RentAmount<C, T>) =
        RentAmount(amount = amount - that.amount * convert(that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("minusRent")
    operator fun minus(that: RentAmount<C, T>) =
        RentAmount(amount = amount - that.amount,
                   currency = currency,
                   time = time)

    // endregion

    // region Scalar

    operator fun <T : Unit<Time>> div(that: RentAmount<C, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time))

    @JvmName("divRent")
    operator fun div(that: RentAmount<C, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Currency

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount * convert(that.time to time),
                       currency = currency)

    @JvmName("timesTime")
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