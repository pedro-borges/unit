package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class DistanceAmount<L : Unit<Distance>>(
        amount: Number,
        unit: L)
    : Amount<Distance>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Distance::class,
                           units = mapOf(unit to 1)))
{
    val distance = unit

    // region -> Distance

    override operator fun times(that: Number) =
        DistanceAmount(amount = amount * that.toBigDecimal(),
                       unit = distance)

    override operator fun div(that: Number) =
        DistanceAmount(amount = amount / that.toBigDecimal(),
                       unit = distance)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        DistanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                       unit = distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        DistanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = distance)

    @JvmName("plusDistance")
    operator fun <L : Unit<Distance>> plus(that: DistanceAmount<L>) =
        DistanceAmount(amount = amount + that.amount
                                * convert(that.distance to distance),
                       unit = distance)

    operator fun plus(that: DistanceAmount<L>) =
        DistanceAmount(amount = amount + that.amount,
                       unit = distance)

    @JvmName("minusDistance")
    operator fun <L : Unit<Distance>> minus(that: DistanceAmount<L>) =
        DistanceAmount(amount = amount - that.amount
                                * convert(that.distance to distance),
                       unit = distance)

    operator fun minus(that: DistanceAmount<L>) =
        DistanceAmount(amount = amount - that.amount,
                       unit = distance)

    // endregion

    // region -> Scalar

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: DistanceAmount<L>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        ScalarAmount(amount = amount * that.amount
                              / convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, that.time))

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        AbsementAmount(amount = amount * that.amount,
                       unit = U2(distance, that.time))

    // endregion

    // region -> Action

    @JvmName("timesMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Area

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.distance to distance),
                   unit = distance)

    operator fun times(that: DistanceAmount<L>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        AreaAmount(amount = amount / that.amount
                            * convert(that.distance to distance),
                   unit = distance)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> AreaDensity

    @JvmName("timesMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        AreaDensityAmount(amount = amount * that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        AreaDensityAmount(amount = amount * that.amount,
                          unit = that.mass to distance)

    @JvmName("divSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = that.mass to distance)

    // endregion

    // region -> Capacitance

    @JvmName("timesPermittivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: PermittivityAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount * that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: PermittivityAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> ElectricConductance

    @JvmName("timesElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount * that.amount
                                           / convert(that.distance to distance,
                                                     that.distance to distance,
                                                     that.distance to distance),
                                  unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount * that.amount,
                                  unit = U2(that.time, that.electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: MagneticFieldStrengthAmount<L, I>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       / convert(that.distance to distance),
                              unit = that.electricCurrent)

    operator fun <I : Unit<ElectricCurrent>> times(that: MagneticFieldStrengthAmount<L, I>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricDipoleMoment

    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<I, T>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U3(distance, that.electricCurrent, that.time))

    // endregion

    // region -> ElectricTension

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount * that.amount
                                       * convert(that.distance to distance),
                              unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Energy

    @JvmName("timesForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityGradientAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance),
                            unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityGradientAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> Force

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: RadiantExposureAmount<M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Frequency

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun div(that: VolumeAmount<L>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Inductance

    @JvmName("timesPermeability")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: PermeabilityAmount<L, I, M, T>) =
        InductanceAmount(amount = amount * that.amount
                                  * convert(that.distance to distance),
                         unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: PermeabilityAmount<L, I, M, T>) =
        InductanceAmount(amount = amount * that.amount,
                         unit = U2(distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Intensity

    @JvmName("timesPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
        IntensityAmount(amount = amount * that.amount
                                 / convert(that.distance to distance),
                        unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = that.mass to that.time)

    // endregion

    // region -> LinearDensity

    @JvmName("timesAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        LinearDensityAmount(amount = amount * that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance),
                            unit = that.mass to distance)

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        LinearDensityAmount(amount = amount * that.amount,
                            unit = that.mass to distance)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("timesElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: ElectricCurrentDensityAmount<L, I>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount
                                             / convert(that.distance to distance,
                                                       that.distance to distance),
                                    unit = that.electricCurrent to distance)

    operator fun <I : Unit<ElectricCurrent>> times(that: ElectricCurrentDensityAmount<L, I>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount,
                                    unit = that.electricCurrent to distance)

    // endregion

    // region -> Mass

    @JvmName("timesLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        MassAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.distance to distance),
                           unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to that.time)

    // endregion

    // region -> Momentum

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Power

    @JvmName("timesYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.distance to distance),
                              unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to that.time)

    // endregion

    // region -> RadiantIntensity

    @JvmName("timesSpectralIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: SpectralIntensityAmount<BAC, L, M, T>) =
        RadiantIntensityAmount(amount = amount * that.amount
                                        * convert(that.distance to distance),
                               unit = U2(distance, that.mass) to U2(that.angle, that.time))

    operator fun <BAC : Unit<Angle>, M : Unit<Mass>, T : Unit<Time>> times(that: SpectralIntensityAmount<BAC, L, M, T>) =
        RadiantIntensityAmount(amount = amount * that.amount,
                               unit = U2(distance, that.mass) to U2(that.angle, that.time))

    // endregion

    // region -> SpacialFrequency

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun div(that: AreaAmount<L>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.distance to distance),
                             unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to that.time)

    // endregion

    // region -> SpecificVolume

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = distance to that.mass)

    // endregion

    // region -> Speed

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to that.time)

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to that.time)

    // endregion

    // region -> Temperature

    @JvmName("timesTemperatureGradient")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>> times(that: TemperatureGradientAmount<L, K>) =
        TemperatureAmount(amount = amount * that.amount
                                   / convert(that.distance to distance),
                          unit = that.temperature)

    operator fun <K : Unit<Temperature>> times(that: TemperatureGradientAmount<L, K>) =
        TemperatureAmount(amount = amount * that.amount,
                          unit = that.temperature)

    // endregion

    // region -> Time

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        TimeAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> times(that: PaceAmount<L, T>) =
        TimeAmount(amount = amount * that.amount,
                   unit = that.time)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> Volume

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun times(that: AreaAmount<L>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        VolumeAmount(amount = amount / that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion

    // region -> Yank

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: IntensityAmount<M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to that.time)

    // endregion
}
