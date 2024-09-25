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
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.dimensions.Viscosity
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ViscosityAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, U2<L, T>>)
    : Amount<Viscosity>(amount = amount.toBigDecimal(),
                        unit = CompositeUnit(
                            domain = Viscosity::class,
                            units = mapOf(unit.major to 1,
                                          unit.minor.first to -1,
                                          unit.minor.second to -1)))
{
    val mass = unit.major
    val distance = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Viscosity>) =
            ViscosityAmount(amount = amount * unit.scale,
                            unit = kilo(GRAM) to U2(METER, SECOND))
    }

    // region -> Viscosity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ViscosityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                        unit = mass to U2(distance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ViscosityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                        unit = mass to U2(distance, time))

    @JvmName("plusViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: ViscosityAmount<L, M, T>) =
        ViscosityAmount(amount = amount + that.amount
                                 * convert(that.mass to mass)
                                 / convert(that.distance to distance,
                                           that.time to time),
                        unit = mass to U2(distance, time))

    operator fun plus(that: ViscosityAmount<L, M, T>) =
        ViscosityAmount(amount = amount + that.amount,
                        unit = mass to U2(distance, time))

    @JvmName("minusViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: ViscosityAmount<L, M, T>) =
        ViscosityAmount(amount = amount - that.amount
                                 * convert(that.mass to mass)
                                 / convert(that.distance to distance,
                                           that.time to time),
                        unit = mass to U2(distance, time))

    operator fun minus(that: ViscosityAmount<L, M, T>) =
        ViscosityAmount(amount = amount - that.amount,
                        unit = mass to U2(distance, time))

    // endregion

    // region -> Scalar

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ViscosityAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityGradientAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.time to time,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun div(that: EnergyDensityGradientAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> Action

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun times(that: VolumeAmount<L>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> AngularMomentum

    @JvmName("timesMomentOfInertia")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MomentOfInertiaAmount<L, M>) =
        AngularMomentumAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass),
                              unit = U2(mass, distance) to time)

    operator fun times(that: MomentOfInertiaAmount<L, M>) =
        AngularMomentumAmount(amount = amount * that.amount,
                              unit = U2(mass, distance) to time)

    // endregion

    // region -> AreaDensity

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        AreaDensityAmount(amount = amount * that.amount
                                   * convert(that.time to time)
                                   / convert(that.distance to distance),
                          unit = mass to distance)

    operator fun times(that: PaceAmount<L, T>) =
        AreaDensityAmount(amount = amount * that.amount,
                          unit = mass to distance)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance)
                                   * convert(that.time to time),
                          unit = mass to distance)

    operator fun div(that: SpeedAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Energy

    @JvmName("timesVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: VolumetricFlowAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     / convert(that.time to time),
                            unit = mass to U2(distance, time))

    operator fun times(that: FrequencyAmount<T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(distance, time))

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.time to time),
                            unit = mass to U2(distance, time))

    operator fun div(that: TimeAmount<T>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> EnergyDensityGradient

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        EnergyDensityGradientAmount(amount = amount / that.amount
                                             / convert(that.distance to distance,
                                                       that.time to time),
                                    unit = mass to U2(distance, time))

    operator fun div(that: AbsementAmount<L, T>) =
        EnergyDensityGradientAmount(amount = amount / that.amount,
                                    unit = mass to U2(distance, time))

    // endregion

    // region -> Frequency

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.mass to mass)
                                 * convert(that.distance to distance),
                        unit = NUMERIC to time)

    operator fun div(that: LinearDensityAmount<L, M>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.mass to mass)
                                      * convert(that.time to time),
                             unit = NUMERIC to distance)

    operator fun div(that: MomentumAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Intensity

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        IntensityAmount(amount = amount * that.amount
                                 * convert(that.distance to distance)
                                 / convert(that.time to time,
                                           that.time to time),
                        unit = mass to time)

    operator fun times(that: AccelerationAmount<L, T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = mass to time)

    // endregion

    // region -> LinearDensity

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        LinearDensityAmount(amount = amount / that.amount
                                     * convert(that.time to time),
                            unit = mass to distance)

    operator fun div(that: FrequencyAmount<T>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to distance)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        LinearDensityAmount(amount = amount * that.amount
                                     * convert(that.time to time),
                            unit = mass to distance)

    operator fun times(that: TimeAmount<T>) =
        LinearDensityAmount(amount = amount * that.amount,
                            unit = mass to distance)

    // endregion

    // region -> Mass

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.time to time),
                   unit = mass)

    operator fun times(that: AbsementAmount<L, T>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance),
                           unit = mass to time)

    operator fun times(that: DistanceAmount<L>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    * convert(that.distance to distance),
                           unit = mass to time)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> MolarEnergy

    @JvmName("timesCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        MolarEnergyAmount(amount = amount * that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance)
                                   / convert(that.time to time),
                          unit = U2(distance, mass) to U2(that.substance, time))

    operator fun <N : Unit<Substance>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(distance, mass) to U2(that.substance, time))

    // endregion

    // region -> Momentum

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = U2(distance, mass) to time)

    operator fun times(that: AreaAmount<L>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, mass) to time)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        MomentumAmount(amount = amount / that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = U2(distance, mass) to time)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> RadiantExposure

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       / convert(that.time to time)
                                       * convert(that.distance to distance),
                              unit = mass to time)

    operator fun div(that: PaceAmount<L, T>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance)
                                       / convert(that.time to time),
                              unit = mass to time)

    operator fun times(that: SpeedAmount<L, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Speed

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass)
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = distance to time)

    operator fun div(that: AreaDensityAmount<L, M>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> ThermalConductivity

    @JvmName("timesSpecificHeatCapacity")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> times(that: SpecificHeatCapacityAmount<L, K, T>) =
        ThermalConductivityAmount(amount = amount * that.amount
                                           * convert(that.distance to distance,
                                                     that.distance to distance)
                                           / convert(that.time to time,
                                                     that.time to time),
                                  unit = U2(distance, mass) to U2(that.temperature, time))

    operator fun <K : Unit<Temperature>> times(that: SpecificHeatCapacityAmount<L, K, T>) =
        ThermalConductivityAmount(amount = amount * that.amount,
                                  unit = U2(distance, mass) to U2(that.temperature, time))

    // endregion

    // region -> Time

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: EnergyDensityAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Yank

    @JvmName("timesSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        YankAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance)
                            / convert(that.time to time,
                                      that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun times(that: SpecificEnergyAmount<L, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}
