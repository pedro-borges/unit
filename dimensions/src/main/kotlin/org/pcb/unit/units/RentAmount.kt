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
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.base.Numeric
import org.pcb.unit.units.base.Numeric.NUMERIC

open class RentAmount<C : Unit<Currency>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<C, T>)
    : Amount<Rent>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Rent::class,
                       units = mapOf(unit.major to 1,
                                     unit.minor to -1)))
{
    val currency = unit.major
    val time = unit.minor

    // region -> Rent

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        RentAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = currency to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        RentAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = currency to time)

    @JvmName("plusRent")
    operator fun <C : Unit<Currency>, T : Unit<Time>> plus(that: RentAmount<C, T>) =
        RentAmount(amount = amount + that.amount
                            * convert(that.currency to currency)
                            / (convert(that.time to time)),
                   unit = currency to time)

    operator fun plus(that: RentAmount<C, T>) =
        RentAmount(amount = amount + that.amount,
                   unit = currency to time)

    @JvmName("minusRent")
    operator fun <C : Unit<Currency>, T : Unit<Time>> minus(that: RentAmount<C, T>) =
        RentAmount(amount = amount - that.amount
                            * convert(that.currency to currency)
                            / (convert(that.time to time)),
                   unit = currency to time)

    operator fun minus(that: RentAmount<C, T>) =
        RentAmount(amount = amount - that.amount,
                   unit = currency to time)

    // endregion

    // region -> Natural

    @JvmName("divRent")
    operator fun <C : Unit<Currency>, T : Unit<Time>> div(that: RentAmount<C, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.currency to currency)
                         * convert(that.time to time))

    operator fun div(that: RentAmount<C, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Currency

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount * convert(that.time to time),
                       unit = currency)

    operator fun times(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount,
                       unit = currency)

    // endregion

    // region -> Frequency

    @JvmName("divCurrency")
    operator fun <C : Unit<Currency>> div(that: CurrencyAmount<C>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.currency to currency),
                        unit = NUMERIC to time)

    operator fun div(that: CurrencyAmount<C>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion
}