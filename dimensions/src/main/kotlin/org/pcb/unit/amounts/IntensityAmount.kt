package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Intensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class IntensityAmount<M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, T>)
    : Amount<Intensity>(amount = amount.toBigDecimal(),
                        unit = CompositeUnit(
                            domain = Intensity::class,
                            units = mapOf(unit.major to 1,
                                          unit.minor to -3)))
{
    val mass = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Intensity>) =
            IntensityAmount(amount = amount * unit.scale,
                            unit = kilo(GRAM) to SECOND)
    }

    // region -> Intensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        IntensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                        unit = mass to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        IntensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                        unit = mass to time)

    @JvmName("plusIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> plus(that: IntensityAmount<M, T>) =
        IntensityAmount(amount = amount + that.amount
                                 * convert(that.mass to mass)
                                 / convert(that.time to time,
                                           that.time to time,
                                           that.time to time),
                        unit = mass to time)

    operator fun plus(that: IntensityAmount<M, T>) =
        IntensityAmount(amount = amount + that.amount,
                        unit = mass to time)

    @JvmName("minusIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> minus(that: IntensityAmount<M, T>) =
        IntensityAmount(amount = amount - that.amount
                                 * convert(that.mass to mass)
                                 / convert(that.time to time,
                                           that.time to time,
                                           that.time to time),
                        unit = mass to time)

    operator fun minus(that: IntensityAmount<M, T>) =
        IntensityAmount(amount = amount - that.amount,
                        unit = mass to time)

    // endregion

    // region -> Scalar

    @JvmName("divIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: IntensityAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: IntensityAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass)
                                    * convert(that.time to time),
                           unit = that.distance to time)

    operator fun <L : Unit<Distance>> div(that: ViscosityAmount<L, M, T>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = that.distance to time)

    // endregion

    // region -> Distance

    @JvmName("divPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: PowerDensityAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = that.distance)

    // endregion

    // region -> ElectricCurrentDensity

    @JvmName("divElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount
                                              / convert(that.mass to mass)
                                              * convert(that.time to time,
                                                        that.time to time,
                                                        that.time to time),
                                     unit = that.electricCurrent to that.distance)

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount,
                                     unit = that.electricCurrent to that.distance)

    // endregion

    // region -> ElectricFieldStrength

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount,
                                    unit = U2(that.distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> ElectricTension

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: ElectricCurrentDensityAmount<L, I>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(that.distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> EnergyDensity

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.time to time),
                            unit = mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>> times(that: PaceAmount<L, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(that.distance, time))

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     * convert(that.time to time),
                            unit = mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>> div(that: SpeedAmount<L, T>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(that.distance, time))

    // endregion

    // region -> Force

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.time to time),
                    unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> times(that: AbsementAmount<L, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.mass to mass)
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: PowerAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to that.distance)

    // endregion

    // region -> Jerk

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        JerkAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = that.distance to time)

    operator fun <L : Unit<Distance>> div(that: LinearDensityAmount<L, M>) =
        JerkAmount(amount = amount / that.amount,
                   unit = that.distance to time)

    // endregion

    // region -> LinearDensity

    @JvmName("divJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: JerkAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount
                                     * convert(that.time to time,
                                               that.time to time,
                                               that.time to time),
                            unit = mass to that.distance)

    operator fun <L : Unit<Distance>> div(that: JerkAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to that.distance)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("divElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.mass to mass)
                                             * convert(that.time to time,
                                                       that.time to time,
                                                       that.time to time),
                                    unit = that.electricCurrent to that.distance)

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount,
                                    unit = that.electricCurrent to that.distance)

    // endregion

    // region -> Power

    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        PowerAmount(amount = amount / that.amount,
                    unit = U2(that.distance, mass) to time)

    // endregion

    // region -> PowerDensity

    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        PowerDensityAmount(amount = amount / that.amount,
                           unit = mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = mass to U2(that.distance, time))

    // endregion

    // region -> RadiantExposure

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       * convert(that.time to time),
                              unit = mass to time)

    operator fun div(that: FrequencyAmount<T>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.time to time),
                              unit = mass to time)

    operator fun times(that: TimeAmount<T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.time to time,
                                                  that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: YankAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to that.distance)

    // endregion

    // region -> Speed

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass)
                             * convert(that.time to time,
                                       that.time to time),
                    unit = that.distance to time)

    operator fun <L : Unit<Distance>> div(that: EnergyDensityAmount<L, M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = that.distance to time)

    // endregion

    // region -> TemperatureGradient

    @JvmName("divThermalConductivity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalConductivityAmount<L, M, K, T>) =
        TemperatureGradientAmount(amount = amount / that.amount
                                           / convert(that.mass to mass)
                                           * convert(that.time to time,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = that.temperature to that.distance)

    operator fun <L : Unit<Distance>, K : Unit<Temperature>> div(that: ThermalConductivityAmount<L, M, K, T>) =
        TemperatureGradientAmount(amount = amount / that.amount,
                                  unit = that.temperature to that.distance)

    // endregion

    // region -> ThermalConductivity

    operator fun <L : Unit<Distance>, K : Unit<Temperature>> div(that: TemperatureGradientAmount<L, K>) =
        ThermalConductivityAmount(amount = amount / that.amount,
                                  unit = U2(that.distance, mass) to U2(that.temperature, time))

    // endregion

    // region -> Viscosity

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        ViscosityAmount(amount = amount / that.amount
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>> div(that: AccelerationAmount<L, T>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(that.distance, time))

    // endregion

    // region -> Yank

    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(that.distance, mass) to time)

    // endregion
}
