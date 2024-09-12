package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Area
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AreaAmount<D2 : Unit<Distance>>(
        amount: Number,
        unit: D2)
    : Amount<Area>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Area::class,
                       units = mapOf(unit to 2)))
{
    val distance = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Area>) =
            AreaAmount(amount = amount * unit.scale,
                       unit = METER)
    }

    // region -> Area

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AreaAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AreaAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
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

    // region -> Force

    @JvmName("timesPressure")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: PressureAmount<M, D, T>) =
        ForceAmount(amount = amount * that.amount / convert(that.distance to distance),
                    unit = U2(that.mass, distance) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PressureAmount<M, D2, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.mass, distance) to that.time)

    // endregion

    // region -> LuminousFlux

    @JvmName("timesIlluminance")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>, D : Unit<Distance>> times(that: IlluminanceAmount<LI, A, D>) =
        LuminousFluxAmount(amount = amount * that.amount * convert(that.distance to distance,
                                                                   that.distance to distance),
                           unit = U2(that.luminousIntensity, that.angle))

    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>> times(that: IlluminanceAmount<LI, A, D2>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(that.luminousIntensity, that.angle))

    //endregion
}