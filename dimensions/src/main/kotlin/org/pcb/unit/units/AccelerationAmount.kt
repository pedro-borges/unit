package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Acceleration
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount

open class AccelerationAmount<L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        major: L,
        minor: Tuple<T, T>)
    : Amount<Acceleration>(amount = amount.toBigBigDecimal() / convert(minor.second to minor.first),
                           unit = CompositeUnit(
                               domain = Acceleration::class,
                               units = listOf(major to 1,
                                              minor.first to -2)))
{
    val length = major
    val time = minor.first

    // region Acceleration

    override operator fun <U : Unit<Scalar>> times(that: ScalarAmount<U>) =
        AccelerationAmount(amount = amount * that.amount * that.unit.scale,
                           major = length,
                           minor = time * time)

    override operator fun <U : Unit<Scalar>> div(that: ScalarAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / that.unit.scale,
                           major = length,
                           minor = time * time)

    operator fun <L : Unit<Length>, T : Unit<Time>> plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount
                                    * convert(that.length to length)
                                    / convert(that.time to time,
                                              that.time to time),
                           major = length,
                           minor = time * time)

    @JvmName("plusAcceleration")
    operator fun plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount,
                           major = length,
                           minor = time * time)

    operator fun <L : Unit<Length>, T : Unit<Time>> minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount
                                    * convert(that.length to length)
                                    / convert(that.time to time,
                                              that.time to time),
                           major = length,
                           minor = time * time)

    @JvmName("minusAcceleration")
    operator fun minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount,
                           major = length,
                           minor = time * time)

    // endregion

    // region Scalar

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.length to length)
                               * convert(that.time to time,
                                         that.time to time))

    @JvmName("divAcceleration")
    operator fun div(that: AccelerationAmount<L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Speed

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount * convert(that.time to time),
                    length = length,
                    time = time)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount,
                    length = length,
                    time = time)

    // endregion

    // region Frequency

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.length to length)
                                 * convert(that.time to time),
                        minor = time)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        minor = time)

    // endregion

    // region Force

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        ForceAmount(amount = amount * that.amount,
                    major = that.mass * length,
                    minor = time * time)

    // endregion
}