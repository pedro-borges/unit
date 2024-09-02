package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.dimension.Acceleration
import org.pcb.units.dimension.Length
import org.pcb.units.dimension.Mass
import org.pcb.units.dimension.Scalar
import org.pcb.units.dimension.Time
import org.pcb.units.div
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

open class AccelerationAmount<L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        val length: L,
        val time: T,
        time2: T)
    : Amount<Acceleration>(amount = amount.toBigBigDecimal() * convert(time to time2),
                           unit = CompositeUnit(
                               domain = Acceleration::class,
                               units = listOf(length to 1,
                                              time to -2)))
{
    // region Acceleration

    override operator fun <U : Unit<Scalar>> times(that: ScalarAmount<U>): AccelerationAmount<L, T> =
        AccelerationAmount(amount = amount * that.amount * that.unit.scale,
                           length = length,
                           time = time,
                           time2 = time)

    override operator fun <U : Unit<Scalar>> div(that: ScalarAmount<U>): AccelerationAmount<L, T> =
        AccelerationAmount(amount = amount / that.amount / that.unit.scale,
                           length = length,
                           time = time,
                           time2 = time)

    operator fun <L : Unit<Length>, T : Unit<Time>> plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount * convert(that.length to length,
                                                                   time to that.time,
                                                                   time to that.time),
                           length = length,
                           time = time,
                           time2 = time)

    @JvmName("plusAcceleration")
    operator fun plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount,
                           length = length,
                           time = time,
                           time2 = time)

    operator fun <L : Unit<Length>, T : Unit<Time>> minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount * convert(that.length to length,
                                                                   time to that.time,
                                                                   time to that.time),
                           length = length,
                           time = time,
                           time2 = time)

    @JvmName("minusAcceleration")
    operator fun minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount,
                           length = length,
                           time = time,
                           time2 = time)

    // endregion

    // region Scalar

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.length to length,
                                                              time to that.time,
                                                              time to that.time))

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
        FrequencyAmount(amount = amount / that.amount * convert(length to that.length,
                                                                that.time to time),
                        time = time)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        time = time)

    // endregion

    // region Force

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        ForceAmount(amount = amount * that.amount,
                    mass = that.mass,
                    length = length,
                    time = time,
                    time2 = time)

    // endregion
}