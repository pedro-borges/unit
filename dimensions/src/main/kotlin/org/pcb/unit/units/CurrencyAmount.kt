package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.T2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.times
import org.pcb.unit.to
import org.pcb.unit.toBigBigDecimal

open class CurrencyAmount<C : Unit<Currency>>(
        amount: Number,
        unit: C)
    : Amount<Currency>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Currency::class,
                           units = listOf(unit to 1)))
{
    val currency = unit

    // region -> Currency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        CurrencyAmount(amount = amount * that.amount * that.unit.scale,
                       unit = currency)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        CurrencyAmount(amount = amount.to(that.amount).to(that.unit.scale),
                       unit = currency)

    operator fun <C : Unit<Currency>> plus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount + that.amount * convert(that.currency to currency),
                       unit = currency)

    @JvmName("plusCurrency")
    operator fun plus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount + that.amount,
                       unit = currency)

    operator fun <C : Unit<Currency>> minus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount - that.amount * convert(that.currency to currency),
                       unit = currency)

    @JvmName("minusCurrency")
    operator fun minus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount - that.amount,
                       unit = currency)

    // endregion

    // region -> Natural

    operator fun <C : Unit<Currency>> div(that: CurrencyAmount<C>) =
        amount.to(that.amount).to(convert(that.currency to currency))

    @JvmName("divCurrency")
    operator fun div(that: CurrencyAmount<C>) =
        amount.to(that.amount)

    // endregion

    // region -> Rent

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        RentAmount(amount = amount.to(that.amount),
                   unit = Pair(currency, that.time))

    // endregion

    // region -> Debt

    operator fun <U : Unit<Time>> times(that: TimeAmount<U>) =
        DebtAmount(amount = amount * that.amount,
                   unit = T2(currency, that.time))

    // endregion
}
