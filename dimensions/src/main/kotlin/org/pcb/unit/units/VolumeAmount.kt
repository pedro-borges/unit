package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Volume
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class VolumeAmount<L3 : Unit<Distance>>(
        amount: Number,
        unit: L3)
    : Amount<Distance>(amount = amount.toBigBigDecimal(),
                     unit = CompositeUnit(
                         domain = Distance::class,
                         units = listOf(unit to 3)))
{
    val distance = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Volume>) =
            VolumeAmount(amount = amount.toBigBigDecimal() * unit.scale,
                         unit = METER)
    }

    // region -> Volume

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount * that.amount * that.unit.scale,
                     unit = distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount / that.amount / that.unit.scale,
                     unit = distance)

    @JvmName("plusVolume")
    operator fun <D: Unit<Distance>> plus(that: VolumeAmount<D>) =
        VolumeAmount(amount = amount + that.amount * convert(that.distance to distance,
                                                             that.distance to distance,
                                                             that.distance to distance),
                     unit = distance)

    operator fun plus(that: VolumeAmount<L3>) =
        VolumeAmount(amount = amount + that.amount,
                     unit = distance)

    @JvmName("minusVolume")
    operator fun <D: Unit<Distance>> minus(that: VolumeAmount<D>) =
        VolumeAmount(amount = amount - that.amount * convert(that.distance to distance,
                                                             that.distance to distance,
                                                             that.distance to distance),
                     unit = distance)

    operator fun minus(that: VolumeAmount<L3>) =
        VolumeAmount(amount = amount - that.amount,
                     unit = distance)

    // endregion

    // region -> Natural

    @JvmName("divVolume")
    operator fun <D: Unit<Distance>> div(that: VolumeAmount<D>) =
        ScalarAmount(amount = amount / that.amount / convert(that.distance to distance,
                                                             that.distance to distance,
                                                             that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: VolumeAmount<L3>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Area

    @JvmName("divDistance")
    operator fun <D: Unit<Distance>> div(that: DistanceAmount<D>) =
        AreaAmount(amount = amount / that.amount / convert(that.distance to distance),
                   unit = distance)

    operator fun div(that: DistanceAmount<L3>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divArea")
    operator fun <D: Unit<Distance>> div(that: AreaAmount<D>) =
        DistanceAmount(amount = amount / that.amount / convert(that.distance to distance,
                                                             that.distance to distance),
                     unit = distance)

    operator fun div(that: AreaAmount<L3>) =
        DistanceAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion
}