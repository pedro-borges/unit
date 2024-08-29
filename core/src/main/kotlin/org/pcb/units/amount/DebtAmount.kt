package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.div
import org.pcb.units.domain.Currency
import org.pcb.units.domain.Debt
import org.pcb.units.domain.Time
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

    operator fun plus(that: DebtAmount<C, *>) =
        DebtAmount(amount = amount + that.amount * convert(that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("plusCT")
    operator fun plus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount + that.amount,
                   currency = currency,
                   time = time)

    operator fun minus(that: DebtAmount<C, *>) =
        DebtAmount(amount = amount - that.amount * convert(that.time to time),
                   currency = currency,
                   time = time)

    @JvmName("minusCT")
    operator fun minus(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount - that.amount,
                   currency = currency,
                   time = time)

    // endregion

    // region Scalar

    operator fun div(that: DebtAmount<C, *>) =
        ScalarAmount(amount = amount / that.amount / convert(that.time to time))

    @JvmName("divCT")
    operator fun div(that: DebtAmount<C, T>) =
        ScalarAmount(amount = amount / that.amount)

    // endregion

    // region Currency

    operator fun div(that: TimeAmount<*>) =
        CurrencyAmount(amount = amount * that.amount * convert(that.time to time),
                       currency = currency)

    @JvmName("divT")
    operator fun div(that: TimeAmount<T>) =
        CurrencyAmount(amount = amount * that.amount,
                       currency = currency)

    // endregion
}