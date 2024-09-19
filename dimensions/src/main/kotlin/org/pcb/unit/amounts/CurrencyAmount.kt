package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Currency
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class CurrencyAmount<C : Unit<Currency>>(
        amount: Number,
        unit: C)
    : Amount<Currency>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Currency::class,
                           units = mapOf(unit to 1)))
{
    val currency = unit

    // region -> Currency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        CurrencyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                       unit = currency)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        CurrencyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = currency)

    @JvmName("plusCurrency")
    operator fun <C : Unit<Currency>> plus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = amount + that.amount
                                * convert(that.currency to currency),
                       unit = currency)

    operator fun plus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = amount + that.amount,
                       unit = currency)

    @JvmName("minusCurrency")
    operator fun <C : Unit<Currency>> minus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = amount - that.amount
                                * convert(that.currency to currency),
                       unit = currency)

    operator fun minus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = amount - that.amount,
                       unit = currency)

    // endregion

    // region -> Scalar

    @JvmName("divCurrency")
    operator fun <C : Unit<Currency>> div(that: CurrencyAmount<C>) =
        Numeric(amount = amount / that.amount
                         / convert(that.currency to currency))

    operator fun div(that: CurrencyAmount<C>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Debt

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        DebtAmount(amount = amount / that.amount,
                   unit = U2(currency, that.time))

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        DebtAmount(amount = amount * that.amount,
                   unit = U2(currency, that.time))

    // endregion

    // region -> Frequency

    @JvmName("divDebt")
    operator fun <C : Unit<Currency>, T : Unit<Time>> div(that: DebtAmount<C, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.currency to currency),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> div(that: DebtAmount<C, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> Rent

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        RentAmount(amount = amount * that.amount,
                   unit = currency to that.time)

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        RentAmount(amount = amount / that.amount,
                   unit = currency to that.time)

    // endregion

    // region -> Time

    @JvmName("divRent")
    operator fun <C : Unit<Currency>, T : Unit<Time>> div(that: RentAmount<C, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.currency to currency),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: RentAmount<C, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion
}
