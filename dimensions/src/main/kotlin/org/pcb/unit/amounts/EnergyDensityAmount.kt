package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.EnergyDensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class EnergyDensityAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, U2<L, T>>)
    : Amount<EnergyDensity>(amount = amount.toBigDecimal(),
                            unit = CompositeUnit(
                                domain = EnergyDensity::class,
                                units = mapOf(unit.major to 1,
                                              unit.minor.first to -1,
                                              unit.minor.second to -2)))
{
    val mass = unit.major
    val distance = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<EnergyDensity>) =
            EnergyDensityAmount(amount = amount * unit.scale,
                                unit = kilo(GRAM) to U2(METER, SECOND))
    }

    // region -> EnergyDensity

    override operator fun <S : Unit<Scalar>> times(that: Number) =
        EnergyDensityAmount(amount = amount * that.toBigDecimal(),
                            unit = mass to U2(distance, time))

    override operator fun <S : Unit<Scalar>> div(that: Number) =
        EnergyDensityAmount(amount = amount / that.toBigDecimal(),
                            unit = mass to U2(distance, time))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                            unit = mass to U2(distance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                            unit = mass to U2(distance, time))

    @JvmName("plusEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: EnergyDensityAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount + that.amount
                                     * convert(that.mass to mass)
                                     / convert(that.distance to distance,
                                               that.time to time,
                                               that.time to time),
                            unit = mass to U2(distance, time))

    operator fun plus(that: EnergyDensityAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount + that.amount,
                            unit = mass to U2(distance, time))

    @JvmName("minusEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: EnergyDensityAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount - that.amount
                                     * convert(that.mass to mass)
                                     / convert(that.distance to distance,
                                               that.time to time,
                                               that.time to time),
                            unit = mass to U2(distance, time))

    operator fun minus(that: EnergyDensityAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount - that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> Scalar

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: EnergyDensityAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass)
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = distance to time)

    operator fun div(that: AreaDensityAmount<L, M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> AreaDensity

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance)
                                   * convert(that.time to time,
                                             that.time to time),
                          unit = mass to distance)

    operator fun div(that: AccelerationAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Distance

    @JvmName("divEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityGradientAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: EnergyDensityGradientAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricDisplacementField

    @JvmName("divElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount
                                                 / convert(that.distance to distance,
                                                           that.mass to mass)
                                                 * convert(that.time to time,
                                                           that.time to time,
                                                           that.time to time),
                                        unit = U2(that.electricCurrent, time) to distance)

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount,
                                        unit = U2(that.electricCurrent, time) to distance)

    // endregion

    // region -> ElectricFieldStrength

    @JvmName("divElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.time to time)
                                             * convert(that.distance to distance,
                                                       that.distance to distance),
                                    unit = U2(distance, mass) to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount,
                                    unit = U2(distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun times(that: VolumeAmount<L>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> EnergyDensityGradient

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        EnergyDensityGradientAmount(amount = amount / that.amount
                                             / convert(that.distance to distance),
                                    unit = mass to U2(distance, time))

    operator fun div(that: DistanceAmount<L>) =
        EnergyDensityGradientAmount(amount = amount / that.amount,
                                    unit = mass to U2(distance, time))

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        EnergyDensityGradientAmount(amount = amount * that.amount
                                             / convert(that.distance to distance),
                                    unit = mass to U2(distance, time))

    operator fun times(that: SpacialFrequencyAmount<L>) =
        EnergyDensityGradientAmount(amount = amount * that.amount,
                                    unit = mass to U2(distance, time))

    // endregion

    // region -> Force

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: AreaAmount<L>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        ForceAmount(amount = amount / that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.mass to mass)
                                 * convert(that.distance to distance,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: ViscosityAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time),
                             unit = NUMERIC to distance)

    operator fun div(that: ForceAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Intensity

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        IntensityAmount(amount = amount / that.amount
                                 / convert(that.time to time)
                                 * convert(that.distance to distance),
                        unit = mass to time)

    operator fun div(that: PaceAmount<L, T>) =
        IntensityAmount(amount = amount / that.amount,
                        unit = mass to time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        IntensityAmount(amount = amount * that.amount
                                 * convert(that.distance to distance)
                                 / convert(that.time to time),
                        unit = mass to time)

    operator fun times(that: SpeedAmount<L, T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = mass to time)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("divMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.mass to mass)
                                             * convert(that.time to time,
                                                       that.time to time),
                                    unit = that.electricCurrent to distance)

    operator fun <I : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount,
                                    unit = that.electricCurrent to distance)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("divMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticFluxDensityAmount(amount = amount / that.amount
                                           * convert(that.distance to distance),
                                  unit = mass to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = mass to U2(that.electricCurrent, time))

    // endregion

    // region -> MassDensity

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        MassDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance)
                                   * convert(that.time to time,
                                             that.time to time),
                          unit = mass to distance)

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.time to time),
                           unit = mass to time)

    operator fun times(that: AbsementAmount<L, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    // endregion

    // region -> MolarEnergy

    @JvmName("timesMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        MolarEnergyAmount(amount = amount * that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = U2(distance, mass) to U2(that.substance, time))

    operator fun <N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(distance, mass) to U2(that.substance, time))

    @JvmName("divMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        MolarEnergyAmount(amount = amount / that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = U2(distance, mass) to U2(that.substance, time))

    operator fun <N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        MolarEnergyAmount(amount = amount / that.amount,
                          unit = U2(distance, mass) to U2(that.substance, time))

    // endregion

    // region -> Molarity

    @JvmName("divMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> div(that: MolarEnergyAmount<L, M, N, T>) =
        MolarityAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance,
                                          that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = that.substance to distance)

    operator fun <N : Unit<Substance>> div(that: MolarEnergyAmount<L, M, N, T>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = that.substance to distance)

    // endregion

    // region -> Pace

    @JvmName("divIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: IntensityAmount<M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: IntensityAmount<M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> Power

    @JvmName("timesVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.distance to distance)
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: VolumetricFlowAmount<L, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> PowerDensity

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    / convert(that.time to time),
                           unit = mass to U2(distance, time))

    operator fun times(that: FrequencyAmount<T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = mass to U2(distance, time))

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        PowerDensityAmount(amount = amount / that.amount
                                    / convert(that.time to time),
                           unit = mass to U2(distance, time))

    operator fun div(that: TimeAmount<T>) =
        PowerDensityAmount(amount = amount / that.amount,
                           unit = mass to U2(distance, time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance),
                              unit = mass to time)

    operator fun times(that: DistanceAmount<L>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       * convert(that.distance to distance),
                              unit = mass to time)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("divMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<L, M>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance),
                             unit = distance to time)

    operator fun div(that: MassDensityAmount<L, M>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    @JvmName("timesSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<L, M>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.mass to mass),
                             unit = distance to time)

    operator fun times(that: SpecificVolumeAmount<L, M>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: PowerDensityAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Viscosity

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        ViscosityAmount(amount = amount / that.amount
                                 * convert(that.time to time),
                        unit = mass to U2(distance, time))

    operator fun div(that: FrequencyAmount<T>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(distance, time))

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ViscosityAmount(amount = amount * that.amount
                                 * convert(that.time to time),
                        unit = mass to U2(distance, time))

    operator fun times(that: TimeAmount<T>) =
        ViscosityAmount(amount = amount * that.amount,
                        unit = mass to U2(distance, time))

    // endregion
}
