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

open class AreaAmount<L : Unit<Distance>>(
        amount: Number,
        unit: L)
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
    operator fun <L : Unit<Distance>> plus(that: AreaAmount<L>) =
        AreaAmount(amount = amount + that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = distance)

    operator fun plus(that: AreaAmount<L>) =
        AreaAmount(amount = amount + that.amount,
                   unit = distance)

    @JvmName("minusArea")
    operator fun <L : Unit<Distance>> minus(that: AreaAmount<L>) =
        AreaAmount(amount = amount - that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = distance)

    operator fun minus(that: AreaAmount<L>) =
        AreaAmount(amount = amount - that.amount,
                   unit = distance)

    // endregion

    // region -> Scalar

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: AreaAmount<L>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        ScalarAmount(amount = amount * that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Action

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Distance

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance),
                       unit = distance)

    operator fun div(that: DistanceAmount<L>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        DistanceAmount(amount = amount * that.amount
                                / convert(that.distance to distance),
                       unit = distance)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> Energy

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: RadiantExposureAmount<M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Force

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.distance to distance),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> LinearDensity

    @JvmName("timesMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        LinearDensityAmount(amount = amount * that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance),
                            unit = that.mass to distance)

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        LinearDensityAmount(amount = amount * that.amount,
                            unit = that.mass to distance)

    @JvmName("divSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        LinearDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance),
                            unit = that.mass to distance)

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = that.mass to distance)

    // endregion

    // region -> LuminousFlux

    @JvmName("timesIlluminance")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, J : Unit<LuminousIntensity>> times(that: IlluminanceAmount<BAC, L, J>) =
        LuminousFluxAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = U2(that.angle, that.luminousIntensity))

    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>> times(that: IlluminanceAmount<BAC, L, J>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(that.angle, that.luminousIntensity))

    // endregion

    // region -> LuminousIntensity

    @JvmName("timesLuminance")
    operator fun <L : Unit<Distance>, J : Unit<LuminousIntensity>> times(that: LuminanceAmount<L, J>) =
        LuminousIntensityAmount(amount = amount * that.amount
                                         / convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = that.luminousIntensity)

    operator fun <J : Unit<LuminousIntensity>> times(that: LuminanceAmount<L, J>) =
        LuminousIntensityAmount(amount = amount * that.amount,
                                unit = that.luminousIntensity)

    // endregion

    // region -> MagneticFlux

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Mass

    @JvmName("timesAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        MassAmount(amount = amount * that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> MomentOfInertia

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        MomentOfInertiaAmount(amount = amount * that.amount,
                              unit = U2(distance, that.mass))

    // endregion

    // region -> Pace

    @JvmName("divVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = that.time to distance)

    operator fun <T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = that.time to distance)

    // endregion

    // region -> Power

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: IntensityAmount<M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun div(that: VolumeAmount<L>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> SpecificVolume

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      * convert(that.distance to distance),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = distance to that.mass)

    // endregion

    // region -> Volume

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = distance)

    operator fun times(that: DistanceAmount<L>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        VolumeAmount(amount = amount / that.amount
                              * convert(that.distance to distance),
                     unit = distance)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      * convert(that.distance to distance),
                             unit = distance to that.time)

    operator fun <T : Unit<Time>> div(that: PaceAmount<L, T>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to that.time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      * convert(that.distance to distance),
                             unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = distance to that.time)

    // endregion

    // region -> Yank

    @JvmName("timesPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to that.time)

    // endregion
}
