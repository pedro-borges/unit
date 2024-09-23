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

open class FuelEfficiencyAmount<D : Unit<Distance>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, D>)
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
    operator fun <D : Unit<Distance>> plus(that: FuelEfficiencyAmount<D>) =
        FuelEfficiencyAmount(amount = amount + that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun plus(that: FuelEfficiencyAmount<D>) =
        FuelEfficiencyAmount(amount = amount + that.amount,
                             unit = NUMERIC to distance)

    @JvmName("minusFuelEfficiency")
    operator fun <D : Unit<Distance>> minus(that: FuelEfficiencyAmount<D>) =
        FuelEfficiencyAmount(amount = amount - that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun minus(that: FuelEfficiencyAmount<D>) =
        FuelEfficiencyAmount(amount = amount - that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Scalar

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: AreaAmount<D>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divFuelEfficiency")
    operator fun <D : Unit<Distance>> div(that: FuelEfficiencyAmount<D>) =
        ScalarAmount(amount = amount / that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: FuelEfficiencyAmount<D>) =
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
    operator fun <D : Unit<Distance>> times(that: VolumeAmount<D>) =
        DistanceAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun times(that: VolumeAmount<D>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance),
                            unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> EnergyFluxDensity

    @JvmName("timesPower")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<D, M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<D, M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount,
                                unit = that.mass to that.time)

    // endregion

    // region -> Illuminance

    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>> times(that: LuminousFluxAmount<A, LI>) =
        IlluminanceAmount(amount = amount * that.amount,
                          unit = U2(that.angle, that.luminousIntensity) to distance)

    // endregion

    // region -> Luminance

    operator fun <LI : Unit<LuminousIntensity>> times(that: LuminousIntensityAmount<LI>) =
        LuminanceAmount(amount = amount * that.amount,
                        unit = that.luminousIntensity to distance)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("timesMagneticFlux")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<D, EC, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount
                                           * convert(that.distance to distance,
                                                     that.distance to distance),
                                  unit = that.mass to U2(that.electricCurrent, that.time))

    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<D, EC, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount,
                                  unit = that.mass to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Mass

    @JvmName("timesMomentOfInertia")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: MomentOfInertiaAmount<D, M>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: MomentOfInertiaAmount<D, M>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesAction")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<D, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<D, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to that.time)

    // endregion

    // region -> PowerDensity

    @JvmName("timesYank")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<D, M, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    * convert(that.distance to distance),
                           unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<D, M, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesEnergy")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<D, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<D, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to that.time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        * convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun times(that: DistanceAmount<D>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to distance)

    @JvmName("divSpacialFrequency")
    operator fun <D : Unit<Distance>> div(that: SpacialFrequencyAmount<D>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        * convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun div(that: SpacialFrequencyAmount<D>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Speed

    @JvmName("timesVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<D, T>) =
        SpeedAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.distance to distance),
                    unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: VolumetricFlowAmount<D, T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to that.time)

    // endregion
}
