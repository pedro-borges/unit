package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Area
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.scalar.Numeric

open class AreaAmount<D2 : Unit<Distance>>(
        amount: Number,
        unit: D2)
    : Amount<Distance>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Distance::class,
                           units = listOf(unit to 2)))
{
    val distance = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Area>) =
            AreaAmount(amount = amount.toBigBigDecimal() * unit.scale,
                       unit = METER)
    }

    // region -> Area

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AreaAmount(amount = amount * that.amount * that.unit.scale,
                   unit = distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AreaAmount(amount = amount / that.amount / that.unit.scale,
                   unit = distance)

    @JvmName("plusArea")
    operator fun <D : Unit<Distance>> plus(that: AreaAmount<D>) =
        AreaAmount(amount = amount + that.amount * convert(that.distance to distance,
                                                           that.distance to distance),
                   unit = distance)

    operator fun plus(that: AreaAmount<D2>) =
        AreaAmount(amount = amount + that.amount,
                   unit = distance)

    @JvmName("minusArea")
    operator fun <D : Unit<Distance>> minus(that: AreaAmount<D>) =
        AreaAmount(amount = amount - that.amount * convert(that.distance to distance,
                                                           that.distance to distance),
                   unit = distance)

    operator fun minus(that: AreaAmount<D2>) =
        AreaAmount(amount = amount - that.amount,
                   unit = distance)

    // endregion

    // region -> Natural

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        Numeric(amount = amount / that.amount / convert(that.distance to distance,
                                                             that.distance to distance))

    operator fun div(that: AreaAmount<D2>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Distance

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount / that.amount / convert(that.distance to distance),
                       unit = distance)

    operator fun div(that: DistanceAmount<D2>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Volume

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        VolumeAmount(amount = amount * that.amount * convert(that.distance to distance),
                     unit = distance)

    operator fun times(that: DistanceAmount<D2>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion

    // region -> MagneticFlux

    operator fun <M : Unit<Mass>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: MagneticFluxDensityAmount<M, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.mass, distance) to U2(that.time, that.electricCurrent))

    // endregion
}