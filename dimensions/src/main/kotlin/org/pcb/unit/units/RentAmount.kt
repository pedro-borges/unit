package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Rent
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount

open class RentAmount<C : Unit<Currency>, T : Unit<Time>>(
        amount: Number,
        val major: C,
        val minor: T)
    : Amount<Rent>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Rent::class,
                       units = listOf(major to 1,
                                      minor to -1)))
{
    val currency = major
    val time = minor

    // region Rent

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        RentAmount(amount = amount * that.amount * that.unit.scale,
                   major = currency,
                   minor = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        RentAmount(amount = amount / that.amount / that.unit.scale,
                   major = currency,
                   minor = time)

    operator fun <C : Unit<Currency>, T : Unit<Time>> plus(that: RentAmount<C, T>) =
        RentAmount(amount = amount + that.amount
                            * convert(that.currency to currency)
                            / convert(that.time to time),
                   major = currency,
                   minor = time)

    @JvmName("plusRent")
    operator fun plus(that: RentAmount<C, T>) =
        RentAmount(amount = amount + that.amount,
                   major = currency,
                   minor = time)

    operator fun <C : Unit<Currency>, T : Unit<Time>> minus(that: RentAmount<C, T>) =
        RentAmount(amount = amount - that.amount
                            * convert(that.currency to currency)
                            / convert(that.time to time),
                   major = currency,
                   minor = time)

    @JvmName("minusRent")
    operator fun minus(that: RentAmount<C, T>) =
        RentAmount(amount = amount - that.amount,
                   major = currency,
                   minor = time)

    // endregion

    // region Scalar

    operator fun <C : Unit<Currency>, T : Unit<Time>> div(that: RentAmount<C, T>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.currency to currency)
                               * convert(that.time to time))

    @JvmName("divRent")
    operator fun div(that: RentAmount<C, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Currency

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount * convert(that.time to time),
                       major = currency)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount,
                       major = currency)

    // endregion

    // region Frequency

    operator fun <C : Unit<Currency>> div(that: CurrencyAmount<C>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.currency to currency),
                        minor = time)

    @JvmName("divCurrency")
    operator fun div(that: CurrencyAmount<C>) =
        FrequencyAmount(amount = amount / that.amount,
                        minor = time)

    // endregion
}