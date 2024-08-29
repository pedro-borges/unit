package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class TimeAmount<T : Unit<Time>>(
        amount: Number,
        val time: T)
    : Amount<Time>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Time::class,
                       units = listOf(time to 1)))
{
    // region Time

    operator fun plus(that: TimeAmount<*>) =
        TimeAmount(amount = amount + that.amount * convert(that.time to time),
                   time = time)

    @JvmName("plusTime")
    operator fun plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount,
                   time = time)

    operator fun minus(that: TimeAmount<*>) =
        TimeAmount(amount = amount - that.amount * convert(that.time to time),
                   time = time)

    @JvmName("minusTime")
    operator fun minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount,
                   time = time)

    // endregion

    // region Scalar

    operator fun div(that: TimeAmount<*>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time))

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Pace

    operator fun <U : Unit<Space>> div(that: SpaceAmount<U>) =
        PaceAmount(amount = amount / that.amount,
                   time = time,
                   space = that.space)

    // endregion
}
