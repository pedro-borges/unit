package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.dimension.Length
import org.pcb.units.dimension.Scalar
import org.pcb.units.dimension.Time
import org.pcb.units.div
import org.pcb.units.times
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

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        TimeAmount(amount = amount * that.amount * that.unit.scale,
                   time = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        TimeAmount(amount = amount / that.amount / that.unit.scale,
                   time = time)

    operator fun <T : Unit<Time>> plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount * convert(that.time to time),
                   time = time)

    @JvmName("plusTime")
    operator fun plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount,
                   time = time)

    operator fun <T : Unit<Time>> minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount * convert(that.time to time),
                   time = time)

    @JvmName("minusTime")
    operator fun minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount,
                   time = time)

    // endregion

    // region Scalar

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time))

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Pace

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        PaceAmount(amount = amount / that.amount,
                   time = time,
                   length = that.length)

    // endregion
}
