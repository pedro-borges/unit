package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount

open class CurrencyAmount<C : Unit<Currency>>(
        amount: Number,
        val currency: C)
    : Amount<Currency>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Currency::class,
                           units = listOf(currency to 1)))
{
    // region Currency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        CurrencyAmount(amount = amount * that.amount * that.unit.scale,
                       currency = currency)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        CurrencyAmount(amount = amount / that.amount / that.unit.scale,
                       currency = currency)

    operator fun <C : Unit<Currency>> plus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount + that.amount * convert(that.currency to currency),
                       currency = currency)

    @JvmName("plusCurrency")
    operator fun plus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount + that.amount,
                       currency = currency)

    operator fun <C : Unit<Currency>> minus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount - that.amount * convert(that.currency to currency),
                       currency = currency)

    @JvmName("minusCurrency")
    operator fun minus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount - that.amount,
                       currency = currency)

    // endregion

    // region Scalar

    operator fun <C : Unit<Currency>> div(that: CurrencyAmount<C>) =
        NaturalAmount(amount = amount / that.amount / convert(that.currency to currency))

    @JvmName("divCurrency")
    operator fun div(that: CurrencyAmount<C>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Rent

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        RentAmount(amount = amount / that.amount,
                   currency = currency,
                   time = that.time)

    // endregion

    // region Debt

    operator fun <U : Unit<Time>> times(that: TimeAmount<U>) =
        DebtAmount(amount = amount * that.amount,
                   currency = currency,
                   time = that.time)

    // endregion
}
