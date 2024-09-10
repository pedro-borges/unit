package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Acceleration
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class AccelerationAmount<D : Unit<Distance>, T2 : Unit<Time>>(
        amount: Number,
        unit: Pair<D, T2>)
    : Amount<Acceleration>(amount = amount.toBigBigDecimal(),
                           unit = CompositeUnit(
                               domain = Acceleration::class,
                               units = listOf(unit.major to 1,
                                              unit.minor to -2)))
{
    val distance = unit.major
    val time = unit.minor

    // region -> Acceleration

    override operator fun <U : Unit<Scalar>> times(that: ScalarAmount<U>) =
        AccelerationAmount(amount = amount * that.amount * that.unit.scale,
                           unit = distance to time)

    override operator fun <U : Unit<Scalar>> div(that: ScalarAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / that.unit.scale,
                           unit = distance to time)

    @JvmName("plusAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> plus(that: AccelerationAmount<D, T>) =
        AccelerationAmount(amount = amount + that.amount
                                    * convert(that.distance to distance)
                                    / (convert(that.time to time,
                                               that.time to time)),
                           unit = distance to time)

    operator fun plus(that: AccelerationAmount<D, T2>) =
        AccelerationAmount(amount = amount + that.amount,
                           unit = distance to time)

    @JvmName("minusAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> minus(that: AccelerationAmount<D, T>) =
        AccelerationAmount(amount = amount - that.amount
                                    * convert(that.distance to distance)
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = distance to time)

    operator fun minus(that: AccelerationAmount<D, T2>) =
        AccelerationAmount(amount = amount - that.amount,
                           unit = distance to time)

    // endregion

    // region -> Natural

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        Numeric(amount = amount / that.amount
                              / convert(that.distance to distance)
                              * convert(that.time to time, that.time to time))

    operator fun div(that: AccelerationAmount<D, T2>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Speed

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount * convert(that.time to time),
                    unit = distance to time)

    operator fun times(that: TimeAmount<T2>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to time)

    // endregion

    // region -> Frequency

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance)
                                 * convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: SpeedAmount<D, T2>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Force

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.mass, distance) to time)

    // endregion
}