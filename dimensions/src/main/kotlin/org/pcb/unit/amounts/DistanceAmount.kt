package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class DistanceAmount<D : Unit<Distance>>(
        amount: Number,
        unit: D)
    : Amount<Distance>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Distance::class,
                           units = mapOf(unit to 1)))
{
    val distance = unit

    // region -> Distance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        DistanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                       unit = distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        DistanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = distance)

    @JvmName("plusDistance")
    operator fun <D : Unit<Distance>> plus(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount + that.amount * convert(that.distance to distance),
                       unit = distance)

    operator fun plus(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount + that.amount,
                       unit = distance)

    @JvmName("minusDistance")
    operator fun <D : Unit<Distance>> minus(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount - that.amount * convert(that.distance to distance),
                       unit = distance)

    operator fun minus(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount - that.amount,
                       unit = distance)

    // endregion

    // region -> Area

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.distance to distance),
                   unit = distance)

    operator fun times(that: DistanceAmount<D>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> Energy

    @JvmName("timesForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Momentum

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Scalar

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        Numeric(amount = amount / that.amount
                         / convert(that.distance to distance))

    operator fun div(that: DistanceAmount<D>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Speed

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to that.time)

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to that.time)

    // endregion

    // region -> Time

    @JvmName("timesPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        TimeAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> times(that: PaceAmount<D, T>) =
        TimeAmount(amount = amount * that.amount,
                   unit = that.time)

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> Volume

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun times(that: AreaAmount<D>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion
}