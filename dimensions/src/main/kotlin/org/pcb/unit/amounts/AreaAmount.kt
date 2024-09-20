package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
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

open class AreaAmount<D : Unit<Distance>>(
        amount: Number,
        unit: D)
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
        AreaAmount(amount = amount + that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = distance)

    operator fun plus(that: AreaAmount<D>) =
        AreaAmount(amount = amount + that.amount,
                   unit = distance)

    @JvmName("minusArea")
    operator fun <D : Unit<Distance>> minus(that: AreaAmount<D>) =
        AreaAmount(amount = amount - that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = distance)

    operator fun minus(that: AreaAmount<D>) =
        AreaAmount(amount = amount - that.amount,
                   unit = distance)

    // endregion

    // region -> Scalar

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: AreaAmount<D>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Distance

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance),
                       unit = distance)

    operator fun div(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Force

    @JvmName("timesEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.distance to distance),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> LuminousFlux

    @JvmName("timesIlluminance")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, LI : Unit<LuminousIntensity>> times(that: IlluminanceAmount<A, D, LI>) =
        LuminousFluxAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = U2(that.angle, that.luminousIntensity))

    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>> times(that: IlluminanceAmount<A, D, LI>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(that.angle, that.luminousIntensity))

    // endregion

    // region -> LuminousIntensity

    @JvmName("timesLuminance")
    operator fun <D : Unit<Distance>, LI : Unit<LuminousIntensity>> times(that: LuminanceAmount<D, LI>) =
        LuminousIntensityAmount(amount = amount * that.amount
                                         / convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = that.luminousIntensity)

    operator fun <LI : Unit<LuminousIntensity>> times(that: LuminanceAmount<D, LI>) =
        LuminousIntensityAmount(amount = amount * that.amount,
                                unit = that.luminousIntensity)

    // endregion

    // region -> MagneticFlux

    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<EC, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Mass

    @JvmName("timesAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<D, M>) =
        MassAmount(amount = amount * that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<D, M>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> Pace

    @JvmName("divVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<D, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = that.time to distance)

    operator fun <T : Unit<Time>> div(that: VolumetricFlowAmount<D, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = that.time to distance)

    // endregion

    // region -> Volume

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = distance)

    operator fun times(that: DistanceAmount<D>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("divPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      * convert(that.distance to distance),
                             unit = distance to that.time)

    operator fun <T : Unit<Time>> div(that: PaceAmount<D, T>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to that.time)

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      * convert(that.distance to distance),
                             unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = distance to that.time)

    // endregion
}
