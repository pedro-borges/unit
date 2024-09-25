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
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.dimensions.Yank
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class YankAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, T>)
    : Amount<Yank>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Yank::class,
                       units = mapOf(unit.major.first to 1,
                                     unit.major.second to 1,
                                     unit.minor to -3)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Yank>) =
            YankAmount(amount = amount * unit.scale,
                       unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Yank

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        YankAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        YankAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = U2(distance, mass) to time)

    @JvmName("plusYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: YankAmount<L, M, T>) =
        YankAmount(amount = amount + that.amount
                            * convert(that.distance to distance,
                                      that.mass to mass)
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun plus(that: YankAmount<L, M, T>) =
        YankAmount(amount = amount + that.amount,
                   unit = U2(distance, mass) to time)

    @JvmName("minusYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: YankAmount<L, M, T>) =
        YankAmount(amount = amount - that.amount
                            * convert(that.distance to distance,
                                      that.mass to mass)
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun minus(that: YankAmount<L, M, T>) =
        YankAmount(amount = amount - that.amount,
                   unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: YankAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass)
                                    * convert(that.time to time),
                           unit = distance to time)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> Area

    @JvmName("divPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<L, M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = distance)

    operator fun div(that: PowerDensityAmount<L, M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: IntensityAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: IntensityAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.mass to mass)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricFieldStrength

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount,
                                    unit = U2(distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> ElectricTension

    @JvmName("divMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        ElectricTensionAmount(amount = amount / that.amount
                                       * convert(that.distance to distance),
                              unit = U2(distance, mass) to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: AbsementAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> EnergyDensityGradient

    @JvmName("divVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        EnergyDensityGradientAmount(amount = amount / that.amount
                                             / convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.distance to distance)
                                             * convert(that.time to time),
                                    unit = mass to U2(distance, time))

    operator fun div(that: VolumetricFlowAmount<L, T>) =
        EnergyDensityGradientAmount(amount = amount / that.amount,
                                    unit = mass to U2(distance, time))

    // endregion

    // region -> Force

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        ForceAmount(amount = amount / that.amount
                             * convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun div(that: FrequencyAmount<T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: TimeAmount<T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: ForceAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Intensity

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        IntensityAmount(amount = amount / that.amount
                                 / convert(that.distance to distance),
                        unit = mass to time)

    operator fun div(that: DistanceAmount<L>) =
        IntensityAmount(amount = amount / that.amount,
                        unit = mass to time)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        IntensityAmount(amount = amount * that.amount
                                 / convert(that.distance to distance),
                        unit = mass to time)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = mass to time)

    // endregion

    // region -> Jerk

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        JerkAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        JerkAmount(amount = amount / that.amount,
                   unit = distance to time)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("divElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.mass to mass)
                                             * convert(that.time to time,
                                                       that.time to time,
                                                       that.time to time),
                                    unit = that.electricCurrent to distance)

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount,
                                    unit = that.electricCurrent to distance)

    // endregion

    // region -> Mass

    @JvmName("divJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: JerkAmount<L, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = mass)

    operator fun div(that: JerkAmount<L, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance)
                                    * convert(that.time to time,
                                              that.time to time),
                           unit = mass to time)

    operator fun div(that: AccelerationAmount<L, T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> Power

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: DistanceAmount<L>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        PowerAmount(amount = amount / that.amount
                             * convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        PowerAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> PowerDensity

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        PowerDensityAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = mass to U2(distance, time))

    operator fun div(that: AreaAmount<L>) =
        PowerDensityAmount(amount = amount / that.amount,
                           unit = mass to U2(distance, time))

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        PowerDensityAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = mass to U2(distance, time))

    operator fun times(that: FuelEfficiencyAmount<L>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = mass to U2(distance, time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.time to time)
                                       / convert(that.distance to distance),
                              unit = mass to time)

    operator fun times(that: PaceAmount<L, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       / convert(that.distance to distance)
                                       * convert(that.time to time),
                              unit = mass to time)

    operator fun div(that: SpeedAmount<L, T>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    // endregion

    // region -> SolidAngle

    @JvmName("divSpectralIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: SpectralIntensityAmount<BAC, L, M, T>) =
        SolidAngleAmount(amount = amount / that.amount
                                  / convert(that.distance to distance,
                                            that.mass to mass)
                                  * convert(that.time to time,
                                            that.time to time,
                                            that.time to time),
                         unit = that.angle)

    operator fun <BAC : Unit<Angle>> div(that: SpectralIntensityAmount<BAC, L, M, T>) =
        SolidAngleAmount(amount = amount / that.amount,
                         unit = that.angle)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        * convert(that.time to time,
                                                  that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: PowerAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance,
                                                that.time to time),
                             unit = distance to time)

    operator fun div(that: ViscosityAmount<L, M, T>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> SpectralIntensity

    operator fun <BAC : Unit<Angle>> div(that: SolidAngleAmount<BAC>) =
        SpectralIntensityAmount(amount = amount / that.amount,
                                unit = U2(distance, mass) to U2(that.angle, time))

    // endregion

    // region -> Speed

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass)
                             * convert(that.time to time,
                                       that.time to time),
                    unit = distance to time)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Temperature

    @JvmName("divThermalConductivity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalConductivityAmount<L, M, K, T>) =
        TemperatureAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.mass to mass)
                                   * convert(that.time to time,
                                             that.time to time,
                                             that.time to time),
                          unit = that.temperature)

    operator fun <K : Unit<Temperature>> div(that: ThermalConductivityAmount<L, M, K, T>) =
        TemperatureAmount(amount = amount / that.amount,
                          unit = that.temperature)

    // endregion

    // region -> ThermalConductivity

    operator fun <K : Unit<Temperature>> div(that: TemperatureAmount<K>) =
        ThermalConductivityAmount(amount = amount / that.amount,
                                  unit = U2(distance, mass) to U2(that.temperature, time))

    operator fun <K : Unit<Temperature>> times(that: ThermalExpansionCoefficientAmount<K>) =
        ThermalConductivityAmount(amount = amount * that.amount,
                                  unit = U2(distance, mass) to U2(that.temperature, time))

    // endregion

    // region -> Viscosity

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        ViscosityAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance)
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = mass to U2(distance, time))

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(distance, time))

    // endregion

    // region -> VolumetricFlow

    @JvmName("divEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityGradientAmount<L, M, T>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.time to time,
                                                that.time to time),
                             unit = distance to time)

    operator fun div(that: EnergyDensityGradientAmount<L, M, T>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion
}
