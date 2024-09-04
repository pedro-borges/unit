package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Fraction
import org.pcb.unit.Tuple
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

open class AccelerationAmount<L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        unit: Fraction<L, Tuple<T, T>>)
    : Amount<Acceleration>(amount = amount.toBigBigDecimal() / convert(unit.minor.second to unit.minor.first),
                           unit = CompositeUnit(
                               domain = Acceleration::class,
                               units = listOf(unit.major to 1,
                                              unit.minor.first to -2)))
{
    val length = unit.major
    val time = unit.minor.first

    // region -> Acceleration

    override operator fun <U : Unit<Scalar>> times(that: ScalarAmount<U>) =
        AccelerationAmount(amount = amount * that.amount * that.unit.scale,
                           unit = length / (time * time))

    override operator fun <U : Unit<Scalar>> div(that: ScalarAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / that.unit.scale,
                           unit = length / (time * time))

    operator fun <L : Unit<Length>, T : Unit<Time>> plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount
                                    * convert(that.length to length)
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = length / (time * time))

    @JvmName("plusAcceleration")
    operator fun plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount,
                           unit = length / (time * time))

    operator fun <L : Unit<Length>, T : Unit<Time>> minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount
                                    * convert(that.length to length)
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = length / (time * time))

    @JvmName("minusAcceleration")
    operator fun minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount,
                           unit = length / (time * time))

    // endregion

    // region -> Natural

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        amount / that.amount / convert(that.length to length) * convert(that.time to time, that.time to time)

    @JvmName("divAcceleration")
    operator fun div(that: AccelerationAmount<L, T>) =
        amount / that.amount

    // endregion

    // region -> Speed

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount * convert(that.time to time),
                    unit = length / time)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = length / time)

    // endregion

    // region -> Frequency

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.length to length)
                                 * convert(that.time to time),
                        unit = 1 / time)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = 1 / time)

    // endregion

    // region -> Force

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        ForceAmount(amount = amount * that.amount,
                    unit = (that.mass * length) /
                           (time * time))

    // endregion
}