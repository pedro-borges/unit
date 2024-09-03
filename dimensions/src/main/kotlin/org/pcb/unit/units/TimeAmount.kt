package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount

open class TimeAmount<T : Unit<Time>>(
        amount: Number,
        unit: T)
    : Amount<Time>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Time::class,
                       units = listOf(unit to 1)))
{
    val time = unit

    // region -> Time

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        TimeAmount(amount = amount * that.amount * that.unit.scale,
                   unit = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        TimeAmount(amount = amount / that.amount / that.unit.scale,
                   unit = time)

    operator fun <T : Unit<Time>> plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount * convert(that.time to time),
                   unit = time)

    @JvmName("plusTime")
    operator fun plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount,
                   unit = time)

    operator fun <T : Unit<Time>> minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount * convert(that.time to time),
                   unit = time)

    @JvmName("minusTime")
    operator fun minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount,
                   unit = time)

    // endregion

    // region -> Scalar

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time))

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region -> Pace

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time / that.length)

    // endregion
}
