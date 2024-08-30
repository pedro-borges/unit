package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Currency
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

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

    operator fun plus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount + that.amount,
                       currency = currency)

    operator fun minus(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = this.amount - that.amount,
                       currency = currency)

    // endregion

    // region Scalar

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
