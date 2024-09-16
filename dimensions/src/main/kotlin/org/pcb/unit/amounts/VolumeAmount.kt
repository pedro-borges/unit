package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.dimensions.Volume
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class VolumeAmount<D3 : Unit<Distance>>(
        amount: Number,
        unit: D3)
    : Amount<Volume>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Volume::class,
                         units = mapOf(unit to 3)))
{
    val distance = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Volume>) =
            VolumeAmount(amount = amount * unit.scale,
                         unit = METER)
    }

    // region -> Volume

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                     unit = distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                     unit = distance)

    @JvmName("plusVolume")
    operator fun <D : Unit<Distance>> plus(that: VolumeAmount<D>) =
        VolumeAmount(amount = amount + that.amount * convert(that.distance to distance,
                                                             that.distance to distance,
                                                             that.distance to distance),
                     unit = distance)

    operator fun plus(that: VolumeAmount<D3>) =
        VolumeAmount(amount = amount + that.amount,
                     unit = distance)

    @JvmName("minusVolume")
    operator fun <D : Unit<Distance>> minus(that: VolumeAmount<D>) =
        VolumeAmount(amount = amount - that.amount * convert(that.distance to distance,
                                                             that.distance to distance,
                                                             that.distance to distance),
                     unit = distance)

    operator fun minus(that: VolumeAmount<D3>) =
        VolumeAmount(amount = amount - that.amount,
                     unit = distance)

    // endregion

    // region -> Numeric

    @JvmName("divVolume")
    operator fun <D : Unit<Distance>> div(that: VolumeAmount<D>) =
        Numeric(amount = amount / that.amount / convert(that.distance to distance,
                                                        that.distance to distance,
                                                        that.distance to distance))

    operator fun div(that: VolumeAmount<D3>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Area

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        AreaAmount(amount = amount / that.amount / convert(that.distance to distance),
                   unit = distance)

    operator fun div(that: DistanceAmount<D3>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        DistanceAmount(amount = amount / that.amount / convert(that.distance to distance,
                                                               that.distance to distance),
                       unit = distance)

    operator fun div(that: AreaAmount<D3>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Energy

    @JvmName("timesEnergyDensity")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: EnergyDensityAmount<M, D, T>) =
        EnergyAmount(amount = amount * that.amount / convert(that.distance to distance),
                     unit = U2(that.mass, distance) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<M, D3, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.mass, distance) to that.time)

    //endregion
}