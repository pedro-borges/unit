package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric

open class DistanceAmount<D : Unit<Distance>>(
        amount: Number,
        unit: D)
    : Amount<Distance>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Distance::class,
                           units = listOf(unit to 1)))
{
    val distance = unit

    // region -> Distance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        DistanceAmount(amount = amount * that.amount * that.unit.scale,
                       unit = distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        DistanceAmount(amount = amount / that.amount / that.unit.scale,
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

    // region -> Natural

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        Numeric(amount = amount / that.amount / convert(that.distance to distance))

    operator fun div(that: DistanceAmount<D>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Speed

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to that.time)

    // endregion

    // region -> Area

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        AreaAmount(amount = amount * that.amount * convert(that.distance to distance),
                   unit = distance)

    operator fun times(that: DistanceAmount<D>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> Volume

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        VolumeAmount(amount = amount * that.amount * convert(that.distance to distance,
                                                             that.distance to distance),
                     unit = distance)

    operator fun times(that: AreaAmount<D>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion

    // region -> Energy

    @JvmName("timesForce")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: ForceAmount<M, D, T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.distance to distance),
                     unit = U2(that.mass, distance) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<M, D, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.mass, distance) to that.time)

    // endregion

    // region -> Momentum

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFluxAmount<M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.mass, distance) to that.time)

    // endregion
}