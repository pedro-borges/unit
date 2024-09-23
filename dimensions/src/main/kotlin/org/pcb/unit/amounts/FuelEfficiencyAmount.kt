package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.FuelEfficiency
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class FuelEfficiencyAmount<L : Unit<Distance>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, L>)
    : Amount<FuelEfficiency>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = FuelEfficiency::class,
                                 units = mapOf(unit.minor to -2)))
{
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<FuelEfficiency>) =
            FuelEfficiencyAmount(amount = amount * unit.scale,
                                 unit = NUMERIC to METER)
    }

    // region -> FuelEfficiency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        FuelEfficiencyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                             unit = NUMERIC to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        FuelEfficiencyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                             unit = NUMERIC to distance)

    @JvmName("plusFuelEfficiency")
    operator fun <L : Unit<Distance>> plus(that: FuelEfficiencyAmount<L>) =
        FuelEfficiencyAmount(amount = amount + that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun plus(that: FuelEfficiencyAmount<L>) =
        FuelEfficiencyAmount(amount = amount + that.amount,
                             unit = NUMERIC to distance)

    @JvmName("minusFuelEfficiency")
    operator fun <L : Unit<Distance>> minus(that: FuelEfficiencyAmount<L>) =
        FuelEfficiencyAmount(amount = amount - that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun minus(that: FuelEfficiencyAmount<L>) =
        FuelEfficiencyAmount(amount = amount - that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Scalar

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: AreaAmount<L>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        ScalarAmount(amount = amount / that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AreaDensity

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        AreaDensityAmount(amount = amount * that.amount,
                          unit = that.mass to distance)

    // endregion

    // region -> Distance

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        DistanceAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun times(that: VolumeAmount<L>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance),
                            unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> EnergyFluxDensity

    @JvmName("timesPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<L, M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<L, M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount,
                                unit = that.mass to that.time)

    // endregion

    // region -> Illuminance

    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>> times(that: LuminousFluxAmount<BAC, J>) =
        IlluminanceAmount(amount = amount * that.amount,
                          unit = U2(that.angle, that.luminousIntensity) to distance)

    // endregion

    // region -> Luminance

    operator fun <J : Unit<LuminousIntensity>> times(that: LuminousIntensityAmount<J>) =
        LuminanceAmount(amount = amount * that.amount,
                        unit = that.luminousIntensity to distance)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("timesMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount
                                           * convert(that.distance to distance,
                                                     that.distance to distance),
                                  unit = that.mass to U2(that.electricCurrent, that.time))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount,
                                  unit = that.mass to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Mass

    @JvmName("timesMomentOfInertia")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MomentOfInertiaAmount<L, M>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: MomentOfInertiaAmount<L, M>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to that.time)

    // endregion

    // region -> PowerDensity

    @JvmName("timesYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    * convert(that.distance to distance),
                           unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to that.time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        * convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun times(that: DistanceAmount<L>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to distance)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        * convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Speed

    @JvmName("timesVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        SpeedAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.distance to distance),
                    unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to that.time)

    // endregion
}
