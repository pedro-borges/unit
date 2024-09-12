package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Speed
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SpeedAmount<D : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<D, T>)
    : Amount<Speed>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Speed::class,
                        units = mapOf(unit.major to 1,
                                      unit.minor to -1)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Speed>) =
            SpeedAmount(amount = amount * unit.scale,
                        unit = METER to SECOND)
    }

    // region -> Speed

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                    unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                    unit = distance to time)

    @JvmName("plusSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> plus(that: SpeedAmount<D, T>) =
        SpeedAmount(amount = amount + that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time),
                    unit = distance to time)

    operator fun plus(that: SpeedAmount<D, T>) =
        SpeedAmount(amount = amount + that.amount,
                    unit = distance to time)

    @JvmName("minusSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> minus(that: SpeedAmount<D, T>) =
        SpeedAmount(amount = amount - that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time),
                    unit = distance to time)

    operator fun minus(that: SpeedAmount<D, T>) =
        SpeedAmount(amount = amount - that.amount,
                    unit = distance to time)

    // endregion

    // region -> Natural

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.distance to distance)
                         * convert(that.time to time))

    operator fun div(that: SpeedAmount<D, T>) =
        Numeric(amount = amount / that.amount)

    @JvmName("timesPace")
    operator fun <T : Unit<Time>, D : Unit<Distance>> times(that: PaceAmount<T, D>) =
        Numeric(amount = amount * that.amount
                         * convert(that.time to time)
                         / convert(that.distance to distance))

    operator fun times(that: PaceAmount<T, D>) =
        Numeric(amount = amount * that.amount)

    // endregion

    // region -> Distance

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        DistanceAmount(amount = amount * that.amount * convert(that.time to time),
                       unit = distance)

    operator fun times(that: TimeAmount<T>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> Acceleration

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.time to time),
                                                unit = distance to time)

    // endregion

    // region -> Frequency

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.distance to distance),
                        unit = NUMERIC to time)

    operator fun div(that: DistanceAmount<D>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Energy

    @JvmName("timesMomentum")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: MomentumAmount<M, D, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.time to time),
                     unit = U2(that.mass, distance) to time)

    operator fun <M : Unit<Mass>> times(that: MomentumAmount<M, D, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.mass, distance) to time)

    // endregion

    // region -> Power

    @JvmName("timesForce")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: ForceAmount<M, D, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(that.mass, distance) to time)

    operator fun <M : Unit<Mass>> times(that: ForceAmount<M, D, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.mass, distance) to time)

    // endregion
}