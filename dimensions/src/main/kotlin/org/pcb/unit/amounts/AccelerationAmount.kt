package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Acceleration
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AccelerationAmount<D : Unit<Distance>, T2 : Unit<Time>>(
        amount: Number,
        unit: Pair<D, T2>)
    : Amount<Acceleration>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = Acceleration::class,
                               units = mapOf(unit.major to 1,
                                             unit.minor to -2)))
{
    val distance = unit.major
    val time = unit.minor

    // region -> Acceleration

    override operator fun <U : Unit<Scalar>> times(that: org.pcb.unit.amounts.ScalarAmount<U>) =
        org.pcb.unit.amounts.AccelerationAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                                unit = distance to time)

    override operator fun <U : Unit<Scalar>> div(that: org.pcb.unit.amounts.ScalarAmount<U>) =
        org.pcb.unit.amounts.AccelerationAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                                unit = distance to time)

    @JvmName("plusAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> plus(that: org.pcb.unit.amounts.AccelerationAmount<D, T>) =
        org.pcb.unit.amounts.AccelerationAmount(amount = amount + that.amount
                                                         * convert(that.distance to distance)
                                                         / (convert(that.time to time,
                                                                    that.time to time)),
                                                unit = distance to time)

    operator fun plus(that: org.pcb.unit.amounts.AccelerationAmount<D, T2>) =
        org.pcb.unit.amounts.AccelerationAmount(amount = amount + that.amount,
                                                unit = distance to time)

    @JvmName("minusAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> minus(that: org.pcb.unit.amounts.AccelerationAmount<D, T>) =
        org.pcb.unit.amounts.AccelerationAmount(amount = amount - that.amount
                                                         * convert(that.distance to distance)
                                                         / convert(that.time to time,
                                                                   that.time to time),
                                                unit = distance to time)

    operator fun minus(that: org.pcb.unit.amounts.AccelerationAmount<D, T2>) =
        org.pcb.unit.amounts.AccelerationAmount(amount = amount - that.amount,
                                                unit = distance to time)

    // endregion

    // region -> Natural

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: org.pcb.unit.amounts.AccelerationAmount<D, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.distance to distance)
                         * convert(that.time to time, that.time to time))

    operator fun div(that: org.pcb.unit.amounts.AccelerationAmount<D, T2>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Speed

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: org.pcb.unit.amounts.TimeAmount<T>) =
        org.pcb.unit.amounts.SpeedAmount(amount = amount * that.amount * convert(that.time to time),
                                         unit = distance to time)

    operator fun times(that: org.pcb.unit.amounts.TimeAmount<T2>) =
        org.pcb.unit.amounts.SpeedAmount(amount = amount * that.amount,
                                         unit = distance to time)

    // endregion

    // region -> Frequency

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: org.pcb.unit.amounts.SpeedAmount<D, T>) =
        org.pcb.unit.amounts.FrequencyAmount(amount = amount / that.amount
                                                      / convert(that.distance to distance)
                                                      * convert(that.time to time),
                                             unit = NUMERIC to time)

    operator fun div(that: org.pcb.unit.amounts.SpeedAmount<D, T2>) =
        org.pcb.unit.amounts.FrequencyAmount(amount = amount / that.amount,
                                             unit = NUMERIC to time)

    // endregion

    // region -> Force

    operator fun <M : Unit<Mass>> times(that: org.pcb.unit.amounts.MassAmount<M>) =
        org.pcb.unit.amounts.ForceAmount(amount = amount * that.amount,
                                         unit = U2(that.mass, distance) to time)

    // endregion
}