package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.EnergyDensityGradient
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class EnergyDensityGradientAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, U2<L, T>>)
    : Amount<EnergyDensityGradient>(amount = amount.toBigDecimal(),
                                    unit = CompositeUnit(
                                        domain = EnergyDensityGradient::class,
                                        units = mapOf(unit.major to 1,
                                                      unit.minor.first to -2,
                                                      unit.minor.second to -2)))
{
    val mass = unit.major
    val distance = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<EnergyDensityGradient>) =
            EnergyDensityGradientAmount(amount = amount * unit.scale,
                                        unit = kilo(GRAM) to U2(METER, SECOND))
    }

    // region -> EnergyDensityGradient

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyDensityGradientAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                    unit = mass to U2(distance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyDensityGradientAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                    unit = mass to U2(distance, time))

    @JvmName("plusEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: EnergyDensityGradientAmount<L, M, T>) =
        EnergyDensityGradientAmount(amount = amount + that.amount
                                             * convert(that.mass to mass)
                                             / convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.time to time,
                                                       that.time to time),
                                    unit = mass to U2(distance, time))

    operator fun plus(that: EnergyDensityGradientAmount<L, M, T>) =
        EnergyDensityGradientAmount(amount = amount + that.amount,
                                    unit = mass to U2(distance, time))

    @JvmName("minusEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: EnergyDensityGradientAmount<L, M, T>) =
        EnergyDensityGradientAmount(amount = amount - that.amount
                                             * convert(that.mass to mass)
                                             / convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.time to time,
                                                       that.time to time),
                                    unit = mass to U2(distance, time))

    operator fun minus(that: EnergyDensityGradientAmount<L, M, T>) =
        EnergyDensityGradientAmount(amount = amount - that.amount,
                                    unit = mass to U2(distance, time))

    // endregion

    // region -> Scalar

    @JvmName("divEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityGradientAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: EnergyDensityGradientAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<L, M>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass)
                                    * convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance),
                           unit = distance to time)

    operator fun div(that: MassDensityAmount<L, M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    @JvmName("timesSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<L, M>) =
        AccelerationAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance)
                                    / convert(that.mass to mass),
                           unit = distance to time)

    operator fun times(that: SpecificVolumeAmount<L, M>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = distance to time)

    // endregion

    // region -> ElectricCurrentDensity

    @JvmName("divMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount
                                              / convert(that.mass to mass)
                                              * convert(that.time to time,
                                                        that.time to time),
                                     unit = that.electricCurrent to distance)

    operator fun <I : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount,
                                     unit = that.electricCurrent to distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance),
                            unit = mass to U2(distance, time))

    operator fun times(that: DistanceAmount<L>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(distance, time))

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     * convert(that.distance to distance),
                            unit = mass to U2(distance, time))

    operator fun div(that: SpacialFrequencyAmount<L>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> Force

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: VolumeAmount<L>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time),
                             unit = NUMERIC to distance)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("divElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: ElectricCurrentDensityAmount<L, I>) =
        MagneticFluxDensityAmount(amount = amount / that.amount
                                           * convert(that.distance to distance,
                                                     that.distance to distance),
                                  unit = mass to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentDensityAmount<L, I>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = mass to U2(that.electricCurrent, time))

    // endregion

    // region -> MassDensity

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        MassDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance)
                                   * convert(that.time to time,
                                             that.time to time),
                          unit = mass to distance)

    operator fun div(that: AccelerationAmount<L, T>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Pace

    @JvmName("divPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: PowerDensityAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> PowerDensity

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        PowerDensityAmount(amount = amount / that.amount
                                    / convert(that.time to time)
                                    * convert(that.distance to distance),
                           unit = mass to U2(distance, time))

    operator fun div(that: PaceAmount<L, T>) =
        PowerDensityAmount(amount = amount / that.amount,
                           unit = mass to U2(distance, time))

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    * convert(that.distance to distance)
                                    / convert(that.time to time),
                           unit = mass to U2(distance, time))

    operator fun times(that: SpeedAmount<L, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = mass to U2(distance, time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = mass to time)

    operator fun times(that: AreaAmount<L>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = mass to time)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.distance to distance,
                                                  that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: EnergyDensityAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Viscosity

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        ViscosityAmount(amount = amount * that.amount
                                 * convert(that.distance to distance,
                                           that.time to time),
                        unit = mass to U2(distance, time))

    operator fun times(that: AbsementAmount<L, T>) =
        ViscosityAmount(amount = amount * that.amount,
                        unit = mass to U2(distance, time))

    // endregion

    // region -> Yank

    @JvmName("timesVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        YankAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance)
                            / convert(that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun times(that: VolumetricFlowAmount<L, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}
