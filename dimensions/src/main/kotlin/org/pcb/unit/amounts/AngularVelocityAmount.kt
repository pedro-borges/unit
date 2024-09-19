package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AngularVelocityAmount<A : Unit<Angle>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<A, T>)
    : Amount<Angle>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Angle::class,
                        units = mapOf(unit.major to 1,
                                      unit.minor to -1)))
{
    val angle = unit.major
    val time = unit.second

    // region -> AngularVelocity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AngularVelocityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                              unit = angle to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AngularVelocityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                              unit = angle to time)

    @JvmName("plusAngularVelocity")
    operator fun <A : Unit<Angle>, T : Unit<Time>> plus(that: AngularVelocityAmount<A, T>) =
        AngularVelocityAmount(amount = amount + that.amount
                                       * convert(that.angle to angle)
                                       / convert(that.time to time),
                              unit = angle to time)

    operator fun plus(that: AngularVelocityAmount<A, T>) =
        AngularVelocityAmount(amount = amount + that.amount,
                              unit = angle to time)

    @JvmName("minusAngularVelocity")
    operator fun <A : Unit<Angle>, T : Unit<Time>> minus(that: AngularVelocityAmount<A, T>) =
        AngularVelocityAmount(amount = amount - that.amount
                                       * convert(that.angle to angle)
                                       / convert(that.time to time),
                              unit = angle to time)

    operator fun minus(that: AngularVelocityAmount<A, T>) =
        AngularVelocityAmount(amount = amount - that.amount,
                              unit = angle to time)

    // endregion

    // region -> Scalar

    @JvmName("divAngularVelocity")
    operator fun <A : Unit<Angle>, T : Unit<Time>> div(that: AngularVelocityAmount<A, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.angle to angle)
                         * convert(that.time to time))

    operator fun div(that: AngularVelocityAmount<A, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Angle

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        AngleAmount(amount = amount / that.amount
                             * convert(that.time to time),
                    unit = angle)

    operator fun div(that: FrequencyAmount<T>) =
        AngleAmount(amount = amount / that.amount,
                    unit = angle)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        AngleAmount(amount = amount * that.amount
                             * convert(that.time to time),
                    unit = angle)

    operator fun times(that: TimeAmount<T>) =
        AngleAmount(amount = amount * that.amount,
                    unit = angle)

    // endregion

    // region -> Frequency

    @JvmName("divAngle")
    operator fun <A : Unit<Angle>> div(that: AngleAmount<A>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.angle to angle),
                        unit = NUMERIC to time)

    operator fun div(that: AngleAmount<A>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion
}
