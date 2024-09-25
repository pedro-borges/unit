package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Speed
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SpeedAmount<L : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<Speed>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Speed::class,
                        units = mapOf(unit.major to 1,
                                      unit.minor to -1)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Speed>) =
            SpeedAmount(amount = amount * unit.scale,
                        unit = METER to SECOND)
    }

    // region -> Speed

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                    unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                    unit = distance to time)

    @JvmName("plusSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time),
                    unit = distance to time)

    operator fun plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount,
                    unit = distance to time)

    @JvmName("minusSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time),
                    unit = distance to time)

    operator fun minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount,
                    unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.time to time)
                              / convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: PaceAmount<L, T>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: SpeedAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        AccelerationAmount(amount = amount * that.amount
                                    / convert(that.time to time),
                           unit = distance to time)

    operator fun times(that: FrequencyAmount<T>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = distance to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.time to time),
                           unit = distance to time)

    operator fun div(that: TimeAmount<T>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> Area

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.time to time),
                   unit = distance)

    operator fun times(that: AbsementAmount<L, T>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        DistanceAmount(amount = amount / that.amount
                                * convert(that.time to time),
                       unit = distance)

    operator fun div(that: FrequencyAmount<T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        DistanceAmount(amount = amount * that.amount
                                * convert(that.time to time),
                       unit = distance)

    operator fun times(that: TimeAmount<T>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricConductance

    @JvmName("timesPermittivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: PermittivityAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount * that.amount
                                           * convert(that.time to time,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.time to time)
                                           / convert(that.distance to distance,
                                                     that.distance to distance,
                                                     that.distance to distance),
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, distance))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: PermittivityAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount * that.amount,
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> ElectricFieldStrength

    @JvmName("timesMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount * that.amount
                                             / convert(that.time to time,
                                                       that.time to time),
                                    unit = U2(distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount * that.amount,
                                    unit = U2(distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> ElectricResistance

    @JvmName("timesPermeability")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: PermeabilityAmount<L, I, M, T>) =
        ElectricResistanceAmount(amount = amount * that.amount
                                          * convert(that.distance to distance)
                                          / convert(that.time to time,
                                                    that.time to time),
                                 unit = U2(distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: PermeabilityAmount<L, I, M, T>) =
        ElectricResistanceAmount(amount = amount * that.amount,
                                 unit = U2(distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.time to time),
                     unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: MomentumAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Force

    @JvmName("timesMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: MassFlowRateAmount<M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance),
                        unit = NUMERIC to time)

    operator fun div(that: DistanceAmount<L>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        FrequencyAmount(amount = amount * that.amount
                                 / convert(that.distance to distance),
                        unit = NUMERIC to time)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance)
                                      * convert(that.time to time),
                             unit = NUMERIC to distance)

    operator fun div(that: VolumetricFlowAmount<L, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Intensity

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        IntensityAmount(amount = amount * that.amount
                                 / convert(that.distance to distance,
                                           that.time to time,
                                           that.time to time),
                        unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: EnergyDensityAmount<L, M, T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = that.mass to time)

    // endregion

    // region -> MagneticDipoleMoment

    @JvmName("timesElectricDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricDipoleMomentAmount<L, I, T>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount
                                            * convert(that.distance to distance,
                                                      that.time to time),
                                   unit = U2(distance, that.electricCurrent))

    operator fun <I : Unit<ElectricCurrent>> times(that: ElectricDipoleMomentAmount<L, I, T>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U2(distance, that.electricCurrent))

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("timesElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricDisplacementFieldAmount<L, I, T>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount
                                             * convert(that.time to time)
                                             / convert(that.distance to distance,
                                                       that.distance to distance),
                                    unit = that.electricCurrent to distance)

    operator fun <I : Unit<ElectricCurrent>> times(that: ElectricDisplacementFieldAmount<L, I, T>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount,
                                    unit = that.electricCurrent to distance)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.distance to distance),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> Momentum

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> Power

    @JvmName("timesForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: ForceAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> PowerDensity

    @JvmName("timesEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityGradientAmount<L, M, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.time to time,
                                              that.time to time),
                           unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> times(that: EnergyDensityGradientAmount<L, M, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = that.mass to U2(distance, time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.distance to distance,
                                                 that.time to time),
                              unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: ViscosityAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to time)

    // endregion

    // region -> SpecificEnergy

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.time to time)
                                      * convert(that.distance to distance),
                             unit = distance to time)

    operator fun div(that: PaceAmount<L, T>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.distance to distance)
                                      / convert(that.time to time),
                             unit = distance to time)

    operator fun times(that: SpeedAmount<L, T>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: AccelerationAmount<L, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Viscosity

    @JvmName("timesAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        ViscosityAmount(amount = amount * that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance),
                        unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        ViscosityAmount(amount = amount * that.amount,
                        unit = that.mass to U2(distance, time))

    // endregion

    // region -> VolumetricFlow

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance),
                             unit = distance to time)

    operator fun times(that: AreaAmount<L>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = distance to time)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance),
                             unit = distance to time)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> Yank

    @JvmName("timesRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: RadiantExposureAmount<M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time),
                   unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: RadiantExposureAmount<M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to time)

    // endregion
}
