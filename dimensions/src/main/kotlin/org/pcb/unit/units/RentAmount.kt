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
import org.pcb.unit.units.scalar.NaturalAmount.ONE

open class RentAmount<C : Unit<Currency>, T : Unit<Time>>(
        amount: Number,
        major: C,
        minor: T,
        unit: Pair<C, T>)
    : Amount<Rent>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Rent::class,
                       units = listOf(unit.first to 1,
                                      unit.second to -1)))
{
    val currency = unit.first
    val time = unit.second

    // region Rent

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        RentAmount(amount = amount * that.amount * that.unit.scale,
                   major = currency,
                   minor = time,
                   unit = currency / time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        RentAmount(amount = amount / that.amount / that.unit.scale,
                   major = currency,
                   minor = time,
                   unit = currency / time)

    operator fun <C : Unit<Currency>, T : Unit<Time>> plus(that: RentAmount<C, T>) =
        RentAmount(amount = amount + that.amount
                            * convert(that.currency to currency)
                            / convert(that.time to time),
                   major = currency,
                   minor = time,
                   unit = currency / time)

    @JvmName("plusRent")
    operator fun plus(that: RentAmount<C, T>) =
        RentAmount(amount = amount + that.amount,
                   major = currency,
                   minor = time,
                   unit = currency / time)

    operator fun <C : Unit<Currency>, T : Unit<Time>> minus(that: RentAmount<C, T>) =
        RentAmount(amount = amount - that.amount
                            * convert(that.currency to currency)
                            / convert(that.time to time),
                   major = currency,
                   minor = time,
                   unit = currency / time)

    @JvmName("minusRent")
    operator fun minus(that: RentAmount<C, T>) =
        RentAmount(amount = amount - that.amount,
                   major = currency,
                   minor = time,
                   unit = currency / time)

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
                       unit = currency)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount,
                       unit = currency)

    // endregion

    // region Frequency

    operator fun <C : Unit<Currency>> div(that: CurrencyAmount<C>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.currency to currency),
                        unit = ONE / time)

    @JvmName("divCurrency")
    operator fun div(that: CurrencyAmount<C>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = ONE / time)

    // endregion
}