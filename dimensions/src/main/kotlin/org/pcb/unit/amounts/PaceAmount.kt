package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Pace
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class PaceAmount<D : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<T, D>)
    : Amount<Pace>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Pace::class,
                       units = mapOf(unit.major to 1,
                                     unit.minor to -1)))
{
    val time = unit.major
    val distance = unit.minor

    // region -> Pace

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PaceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = time to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PaceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = time to distance)

    @JvmName("plusPace")
    operator fun <T : Unit<Time>, D : Unit<Distance>> plus(that: PaceAmount<D, T>) =
        PaceAmount(amount = amount + that.amount
                            * convert(that.time to time)
                            / convert(that.distance to distance),
                   unit = time to distance)

    operator fun plus(that: PaceAmount<D, T>) =
        PaceAmount(amount = amount + that.amount,
                   unit = time to distance)

    @JvmName("minusPace")
    operator fun <T : Unit<Time>, D : Unit<Distance>> minus(that: PaceAmount<D, T>) =
        PaceAmount(amount = amount - that.amount
                            * convert(that.time to time)
                            / convert(that.distance to distance),
                   unit = time to distance)

    operator fun minus(that: PaceAmount<D, T>) =
        PaceAmount(amount = amount - that.amount,
                   unit = time to distance)

    // endregion

    // region -> Numeric

    @JvmName("divPace")
    operator fun <T : Unit<Time>, D : Unit<Distance>> div(that: PaceAmount<D, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.time to time)
                         * convert(that.distance to distance))

    operator fun div(that: PaceAmount<D, T>) =
        Numeric(amount = amount / that.amount)

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        Numeric(amount = amount * that.amount
                         * convert(that.distance to distance)
                         / convert(that.time to time))

    operator fun times(that: SpeedAmount<D, T>) =
        Numeric(amount = amount * that.amount)

    // endregion

    // region -> Time

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        TimeAmount(amount = amount * that.amount * convert(that.distance to distance),
                   unit = time)

    operator fun times(that: DistanceAmount<D>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    // endregion
}