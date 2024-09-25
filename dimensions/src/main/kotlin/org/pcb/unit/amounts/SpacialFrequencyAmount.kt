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
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.SpacialFrequency
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SpacialFrequencyAmount<L : Unit<Distance>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, L>)
    : Amount<SpacialFrequency>(amount = amount.toBigDecimal(),
                               unit = CompositeUnit(
                                   domain = SpacialFrequency::class,
                                   units = mapOf(unit.minor to -1)))
{
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<SpacialFrequency>) =
            SpacialFrequencyAmount(amount = amount * unit.scale,
                                   unit = NUMERIC to METER)
    }

    // region -> SpacialFrequency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpacialFrequencyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                               unit = NUMERIC to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpacialFrequencyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                               unit = NUMERIC to distance)

    @JvmName("plusSpacialFrequency")
    operator fun <L : Unit<Distance>> plus(that: SpacialFrequencyAmount<L>) =
        SpacialFrequencyAmount(amount = amount + that.amount
                                        / convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun plus(that: SpacialFrequencyAmount<L>) =
        SpacialFrequencyAmount(amount = amount + that.amount,
                               unit = NUMERIC to distance)

    @JvmName("minusSpacialFrequency")
    operator fun <L : Unit<Distance>> minus(that: SpacialFrequencyAmount<L>) =
        SpacialFrequencyAmount(amount = amount - that.amount
                                        / convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun minus(that: SpacialFrequencyAmount<L>) =
        SpacialFrequencyAmount(amount = amount - that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Scalar

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: DistanceAmount<L>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        ScalarAmount(amount = amount / that.amount
                              * convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("timesSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        AccelerationAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = distance to that.time)

    // endregion

    // region -> Area

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = distance)

    operator fun times(that: VolumeAmount<L>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> AreaDensity

    @JvmName("timesLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        AreaDensityAmount(amount = amount * that.amount
                                   / convert(that.distance to distance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        AreaDensityAmount(amount = amount * that.amount,
                          unit = that.mass to distance)

    // endregion

    // region -> Distance

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        DistanceAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun times(that: AreaAmount<L>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        DistanceAmount(amount = amount / that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCharge

    @JvmName("timesElectricDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricDipoleMomentAmount<L, I, T>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      * convert(that.distance to distance),
                             unit = U2(that.electricCurrent, that.time))

    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricDipoleMomentAmount<L, I, T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(that.electricCurrent, that.time))

    // endregion

    // region -> ElectricConductivity

    @JvmName("timesElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount * that.amount
                                            / convert(that.distance to distance,
                                                      that.distance to distance),
                                   unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount * that.amount,
                                   unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    @JvmName("divElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount / that.amount
                                            / convert(that.distance to distance,
                                                      that.distance to distance),
                                   unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount / that.amount,
                                   unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> ElectricCurrentDensity

    @JvmName("timesMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: MagneticFieldStrengthAmount<L, I>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount
                                              / convert(that.distance to distance),
                                     unit = that.electricCurrent to distance)

    operator fun <I : Unit<ElectricCurrent>> times(that: MagneticFieldStrengthAmount<L, I>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount,
                                     unit = that.electricCurrent to distance)

    // endregion

    // region -> ElectricFieldStrength

    @JvmName("timesElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount * that.amount
                                             * convert(that.distance to distance,
                                                       that.distance to distance),
                                    unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount * that.amount,
                                    unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> ElectricResistance

    @JvmName("divElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricResistanceAmount(amount = amount / that.amount
                                          * convert(that.distance to distance,
                                                    that.distance to distance,
                                                    that.distance to distance),
                                 unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> EnergyDensity

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: RadiantExposureAmount<M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> EnergyDensityGradient

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        EnergyDensityGradientAmount(amount = amount * that.amount
                                             / convert(that.distance to distance),
                                    unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        EnergyDensityGradientAmount(amount = amount * that.amount,
                                    unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> Force

    @JvmName("timesEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Frequency

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 * convert(that.distance to distance),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> div(that: PaceAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to that.time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount * that.amount
                                 * convert(that.distance to distance),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun div(that: DistanceAmount<L>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        FuelEfficiencyAmount(amount = amount * that.amount
                                      / convert(that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        FuelEfficiencyAmount(amount = amount * that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Intensity

    @JvmName("timesYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        IntensityAmount(amount = amount * that.amount
                                 * convert(that.distance to distance),
                        unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = that.mass to that.time)

    // endregion

    // region -> LinearDensity

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        LinearDensityAmount(amount = amount * that.amount,
                            unit = that.mass to distance)

    // endregion

    // region -> MagneticFieldStrength

    operator fun <I : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<I>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount,
                                    unit = that.electricCurrent to distance)

    // endregion

    // region -> MassDensity

    @JvmName("timesAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        MassDensityAmount(amount = amount * that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        MassDensityAmount(amount = amount * that.amount,
                          unit = that.mass to distance)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance),
                           unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to that.time)

    // endregion

    // region -> Momentum

    @JvmName("timesAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<L, M, T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<L, M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Pace

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = that.time to distance)

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        PaceAmount(amount = amount * that.amount,
                   unit = that.time to distance)

    // endregion

    // region -> Permeability

    @JvmName("timesInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<L, I, M, T>) =
        PermeabilityAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<L, I, M, T>) =
        PermeabilityAmount(amount = amount * that.amount,
                           unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Permittivity

    @JvmName("timesCapacitance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: CapacitanceAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: CapacitanceAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount * that.amount,
                           unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> PowerDensity

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: IntensityAmount<M, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance),
                              unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to that.time)

    // endregion

    // region -> SpectralIntensity

    @JvmName("timesRadiantIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: RadiantIntensityAmount<BAC, L, M, T>) =
        SpectralIntensityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = U2(distance, that.mass) to U2(that.angle, that.time))

    operator fun <BAC : Unit<Angle>, M : Unit<Mass>, T : Unit<Time>> times(that: RadiantIntensityAmount<BAC, L, M, T>) =
        SpectralIntensityAmount(amount = amount * that.amount,
                                unit = U2(distance, that.mass) to U2(that.angle, that.time))

    // endregion

    // region -> TemperatureGradient

    operator fun <K : Unit<Temperature>> times(that: TemperatureAmount<K>) =
        TemperatureGradientAmount(amount = amount * that.amount,
                                  unit = that.temperature to distance)

    operator fun <K : Unit<Temperature>> div(that: ThermalExpansionCoefficientAmount<K>) =
        TemperatureGradientAmount(amount = amount / that.amount,
                                  unit = that.temperature to distance)

    // endregion

    // region -> ThermalExpansionCoefficient

    @JvmName("divTemperatureGradient")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>> div(that: TemperatureGradientAmount<L, K>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount
                                                   * convert(that.distance to distance),
                                          unit = NUMERIC to that.temperature)

    operator fun <K : Unit<Temperature>> div(that: TemperatureGradientAmount<L, K>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount,
                                          unit = NUMERIC to that.temperature)

    // endregion

    // region -> Time

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        TimeAmount(amount = amount * that.amount
                            * convert(that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        TimeAmount(amount = amount * that.amount,
                   unit = that.time)

    // endregion

    // region -> Viscosity

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        ViscosityAmount(amount = amount * that.amount,
                        unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> Yank

    @JvmName("timesPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to that.time)

    // endregion
}
