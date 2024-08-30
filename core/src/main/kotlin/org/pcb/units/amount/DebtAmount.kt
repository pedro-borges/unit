package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.div
import org.pcb.units.domain.Currency
import org.pcb.units.domain.Debt
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

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

    operator fun <T : Unit<Time>> plus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount + that.amount * convert(that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("plusDebt")
    operator fun plus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount + that.amount,
                   currency = currency,
                   time = time)

    operator fun <T : Unit<Time>> minus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount - that.amount * convert(that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("minusDebt")
    operator fun minus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount - that.amount,
                   currency = currency,
                   time = time)

    // endregion

    // region Scalar

    operator fun <T : Unit<Time>> div(that: DebtAmount<C, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time))

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