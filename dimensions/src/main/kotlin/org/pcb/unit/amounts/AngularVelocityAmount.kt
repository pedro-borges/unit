package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.AngularVelocity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AngularVelocityAmount<BAC : Unit<Angle>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<BAC, T>)
    : Amount<AngularVelocity>(amount = amount.toBigDecimal(),
                              unit = CompositeUnit(
                                  domain = AngularVelocity::class,
                                  units = mapOf(unit.major to 1,
                                                unit.minor to -1)))
{
    val angle = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<AngularVelocity>) =
            AngularVelocityAmount(amount = amount * unit.scale,
                                  unit = RADIAN to SECOND)
    }

    // region -> AngularVelocity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AngularVelocityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                              unit = angle to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AngularVelocityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                              unit = angle to time)

    @JvmName("plusAngularVelocity")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> plus(that: AngularVelocityAmount<BAC, T>) =
        AngularVelocityAmount(amount = amount + that.amount
                                       * convert(that.angle to angle)
                                       / convert(that.time to time),
                              unit = angle to time)

    operator fun plus(that: AngularVelocityAmount<BAC, T>) =
        AngularVelocityAmount(amount = amount + that.amount,
                              unit = angle to time)

    @JvmName("minusAngularVelocity")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> minus(that: AngularVelocityAmount<BAC, T>) =
        AngularVelocityAmount(amount = amount - that.amount
                                       * convert(that.angle to angle)
                                       / convert(that.time to time),
                              unit = angle to time)

    operator fun minus(that: AngularVelocityAmount<BAC, T>) =
        AngularVelocityAmount(amount = amount - that.amount,
                              unit = angle to time)

    // endregion

    // region -> Scalar

    @JvmName("divAngularVelocity")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> div(that: AngularVelocityAmount<BAC, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.angle to angle)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: AngularVelocityAmount<BAC, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

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

    // region -> AngularAcceleration

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        AngularAccelerationAmount(amount = amount * that.amount
                                           / convert(that.time to time),
                                  unit = angle to time)

    operator fun times(that: FrequencyAmount<T>) =
        AngularAccelerationAmount(amount = amount * that.amount,
                                  unit = angle to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        AngularAccelerationAmount(amount = amount / that.amount
                                           / convert(that.time to time),
                                  unit = angle to time)

    operator fun div(that: TimeAmount<T>) =
        AngularAccelerationAmount(amount = amount / that.amount,
                                  unit = angle to time)

    // endregion

    // region -> Frequency

    @JvmName("divAngle")
    operator fun <BAC : Unit<Angle>> div(that: AngleAmount<BAC>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.angle to angle),
                        unit = NUMERIC to time)

    operator fun div(that: AngleAmount<BAC>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Time

    @JvmName("divAngularAcceleration")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> div(that: AngularAccelerationAmount<BAC, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.angle to angle)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: AngularAccelerationAmount<BAC, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
