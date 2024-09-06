package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Acceleration
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class AccelerationAmount<L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<Acceleration>(amount = amount.toBigBigDecimal(),
                           unit = CompositeUnit(
                               domain = Acceleration::class,
                               units = listOf(unit.major to 1,
                                              unit.minor to -2)))
{
    val length = unit.major
    val time = unit.minor

    // region -> Acceleration

    override operator fun <U : Unit<Scalar>> times(that: ScalarAmount<U>) =
        AccelerationAmount(amount = amount * that.amount * that.unit.scale,
                           unit = length to time)

    override operator fun <U : Unit<Scalar>> div(that: ScalarAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / that.unit.scale,
                           unit = length to time)

    operator fun <L : Unit<Length>, T : Unit<Time>> plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount
                                    * convert(that.length to length)
                                    / (convert(that.time to time,
                                               that.time to time)),
                           unit = length to time)

    @JvmName("plusAcceleration")
    operator fun plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount,
                           unit = length to time)

    operator fun <L : Unit<Length>, T : Unit<Time>> minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount
                                    * convert(that.length to length)
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = length to time)

    @JvmName("minusAcceleration")
    operator fun minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount,
                           unit = length to time)

    // endregion

    // region -> Natural

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.length to length)
                              * convert(that.time to time, that.time to time),
                     unit = NUMERIC)

    @JvmName("divAcceleration")
    operator fun div(that: AccelerationAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Speed

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount * convert(that.time to time),
                    unit = length to time)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = length to time)

    // endregion

    // region -> Frequency

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.length to length)
                                 * convert(that.time to time),
                        unit = NUMERIC to time)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Force

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.mass, length) to time)

    // endregion
}