package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Debt
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class DebtAmount<C : Unit<Currency>, T : Unit<Time>>(
        amount: Number,
        unit: U2<C, T>)
    : Amount<Debt>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Debt::class,
                       units = mapOf(unit.first to 1,
                                     unit.second to 1)))
{
    val currency = unit.first
    val time = unit.second

    // region -> Debt

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        DebtAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = U2(currency, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        DebtAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = U2(currency, time))

    @JvmName("plusDebt")
    operator fun <C : Unit<Currency>, T : Unit<Time>> plus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount + that.amount * convert(that.currency to currency,
                                                           that.time to time),
                   unit = U2(currency, time))

    operator fun plus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount + that.amount,
                   unit = U2(currency, time))

    @JvmName("minusDebt")
    operator fun <C : Unit<Currency>, T : Unit<Time>> minus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount - that.amount * convert(that.currency to currency,
                                                           that.time to time),
                   unit = U2(currency, time))

    operator fun minus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount - that.amount,
                   unit = U2(currency, time))

    // endregion

    // region -> Natural

    @JvmName("divDebt")
    operator fun <C : Unit<Currency>, T : Unit<Time>> div(that: DebtAmount<C, T>) =
        Numeric(amount = amount / that.amount / convert(that.currency to currency, that.time to time))

    operator fun div(that: DebtAmount<C, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Currency

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount / that.amount / convert(that.time to time),
                       unit = currency)

    operator fun div(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount / that.amount,
                       unit = currency)

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        CurrencyAmount(amount = amount / that.amount / convert(that.time to time),
                       unit = currency)

    operator fun times(that: FrequencyAmount<T>) =
        CurrencyAmount(amount = amount / that.amount,
                       unit = currency)

    // endregion
}