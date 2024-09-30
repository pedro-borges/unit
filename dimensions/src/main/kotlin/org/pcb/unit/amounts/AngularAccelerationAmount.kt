package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Radian.RADIAN
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.AngularAcceleration
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AngularAccelerationAmount<BAC : Unit<Angle>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<BAC, T>)
    : Amount<AngularAcceleration>(amount = amount.toBigDecimal(),
                                  unit = CompositeUnit(
                                      domain = AngularAcceleration::class,
                                      units = mapOf(unit.major to 1,
                                                    unit.minor to -2)))
{
    val angle = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<AngularAcceleration>) =
            AngularAccelerationAmount(amount = amount * unit.scale,
                                      unit = RADIAN to SECOND)
    }

    // region -> AngularAcceleration

    override operator fun <S : Unit<Scalar>> times(that: Number) =
        AngularAccelerationAmount(amount = amount * that.toBigDecimal(),
                                  unit = angle to time)

    override operator fun <S : Unit<Scalar>> div(that: Number) =
        AngularAccelerationAmount(amount = amount / that.toBigDecimal(),
                                  unit = angle to time)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AngularAccelerationAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = angle to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AngularAccelerationAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = angle to time)

    @JvmName("plusAngularAcceleration")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> plus(that: AngularAccelerationAmount<BAC, T>) =
        AngularAccelerationAmount(amount = amount + that.amount
                                           * convert(that.angle to angle)
                                           / convert(that.time to time,
                                                     that.time to time),
                                  unit = angle to time)

    operator fun plus(that: AngularAccelerationAmount<BAC, T>) =
        AngularAccelerationAmount(amount = amount + that.amount,
                                  unit = angle to time)

    @JvmName("minusAngularAcceleration")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> minus(that: AngularAccelerationAmount<BAC, T>) =
        AngularAccelerationAmount(amount = amount - that.amount
                                           * convert(that.angle to angle)
                                           / convert(that.time to time,
                                                     that.time to time),
                                  unit = angle to time)

    operator fun minus(that: AngularAccelerationAmount<BAC, T>) =
        AngularAccelerationAmount(amount = amount - that.amount,
                                  unit = angle to time)

    // endregion

    // region -> Scalar

    @JvmName("divAngularAcceleration")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> div(that: AngularAccelerationAmount<BAC, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.angle to angle)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: AngularAccelerationAmount<BAC, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AngularVelocity

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        AngularVelocityAmount(amount = amount / that.amount
                                       * convert(that.time to time),
                              unit = angle to time)

    operator fun div(that: FrequencyAmount<T>) =
        AngularVelocityAmount(amount = amount / that.amount,
                              unit = angle to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        AngularVelocityAmount(amount = amount * that.amount
                                       * convert(that.time to time),
                              unit = angle to time)

    operator fun times(that: TimeAmount<T>) =
        AngularVelocityAmount(amount = amount * that.amount,
                              unit = angle to time)

    // endregion

    // region -> Frequency

    @JvmName("divAngularVelocity")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> div(that: AngularVelocityAmount<BAC, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.angle to angle)
                                 * convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: AngularVelocityAmount<BAC, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion
}
