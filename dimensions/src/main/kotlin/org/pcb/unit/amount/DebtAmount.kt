package org.pcb.unit.amount

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.convert
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Debt
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal

open class DebtAmount<C : Unit<Currency>, T : Unit<Time>>(
        amount: Number,
        val currency: C,
        val time: T)
    : Amount<Debt>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Debt::class,
                       units = listOf(currency to 1,
                                      time to 1)))
{
    // region Debt

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        DebtAmount(amount = amount * that.amount * that.unit.scale,
                   currency = currency,
                   time = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        DebtAmount(amount = amount / that.amount / that.unit.scale,
                   currency = currency,
                   time = time)

    operator fun <C : Unit<Currency>, T : Unit<Time>> plus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount + that.amount * convert(that.currency to currency,
                                                           that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("plusDebt")
    operator fun plus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount + that.amount,
                   currency = currency,
                   time = time)

    operator fun <C : Unit<Currency>, T : Unit<Time>> minus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount - that.amount * convert(that.currency to currency,
                                                           that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("minusDebt")
    operator fun minus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount - that.amount,
                   currency = currency,
                   time = time)

    // endregion

    // region Scalar

    operator fun <C : Unit<Currency>, T : Unit<Time>> div(that: DebtAmount<C, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.currency to currency,
                                                              that.time to time))

    @JvmName("divDebt")
    operator fun div(that: DebtAmount<C, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Currency

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount / that.amount / convert(that.time to time),
                       currency = currency)

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount / that.amount,
                       currency = currency)

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        CurrencyAmount(amount = amount / that.amount / convert(that.time to time),
                       currency = currency)

    @JvmName("timesFrequency")
    operator fun times(that: FrequencyAmount<T>) =
        CurrencyAmount(amount = amount / that.amount,
                       currency = currency)

    // endregion
}