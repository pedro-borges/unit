package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Force
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

open class ForceAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, T>)
    : Amount<Force>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = mapOf(unit.major.first to 1,
                                      unit.major.second to 1,
                                      unit.minor to -2)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Force>) =
            ForceAmount(amount = amount * unit.scale,
                        unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Force

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ForceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ForceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    @JvmName("plusForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: ForceAmount<L, M, T>) =
        ForceAmount(amount = amount + that.amount
                             * convert(that.distance to distance,
                                       that.mass to mass)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun plus(that: ForceAmount<L, M, T>) =
        ForceAmount(amount = amount + that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("minusForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: ForceAmount<L, M, T>) =
        ForceAmount(amount = amount - that.amount
                             * convert(that.distance to distance,
                                       that.mass to mass)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun minus(that: ForceAmount<L, M, T>) =
        ForceAmount(amount = amount - that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ForceAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: IntensityAmount<M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun div(that: IntensityAmount<M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> Acceleration

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass),
                           unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> Action

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: AbsementAmount<L, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Area

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.time to time,
                                      that.time to time),
                   unit = distance)

    operator fun div(that: EnergyDensityAmount<L, M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> CentrifugalForce

    operator fun <BAC : Unit<Angle>> times(that: AngleAmount<BAC>) =
        CentrifugalForceAmount(amount = amount * that.amount,
                               unit = U3(that.angle, distance, mass) to time)

    // endregion

    // region -> Distance

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Energy

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun times(that: DistanceAmount<L>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        EnergyAmount(amount = amount / that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        EnergyAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> EnergyDensity

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance),
                            unit = mass to U2(distance, time))

    operator fun div(that: AreaAmount<L>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(distance, time))

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance),
                            unit = mass to U2(distance, time))

    operator fun times(that: FuelEfficiencyAmount<L>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> Frequency

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: MomentumAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> HeatCapacity

    @JvmName("divTemperatureGradient")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>> div(that: TemperatureGradientAmount<L, K>) =
        HeatCapacityAmount(amount = amount / that.amount
                                    * convert(that.distance to distance),
                           unit = U2(distance, mass) to U2(that.temperature, time))

    operator fun <K : Unit<Temperature>> div(that: TemperatureGradientAmount<L, K>) =
        HeatCapacityAmount(amount = amount / that.amount,
                           unit = U2(distance, mass) to U2(that.temperature, time))

    // endregion

    // region -> Intensity

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        IntensityAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.time to time),
                        unit = mass to time)

    operator fun div(that: AbsementAmount<L, T>) =
        IntensityAmount(amount = amount / that.amount,
                        unit = mass to time)

    // endregion

    // region -> LinearDensity

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance)
                                     * convert(that.time to time,
                                               that.time to time),
                            unit = mass to distance)

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to distance)

    // endregion

    // region -> Mass

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = mass)

    operator fun div(that: AccelerationAmount<L, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.time to time)
                                    / convert(that.distance to distance),
                           unit = mass to time)

    operator fun times(that: PaceAmount<L, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance)
                                    * convert(that.time to time),
                           unit = mass to time)

    operator fun div(that: SpeedAmount<L, T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> Momentum

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MomentumAmount(amount = amount / that.amount
                                * convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun div(that: FrequencyAmount<T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: PowerAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> Power

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        PowerAmount(amount = amount / that.amount
                             / convert(that.time to time)
                             * convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun div(that: PaceAmount<L, T>) =
        PowerAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: SpeedAmount<L, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> RadiantExposure

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       / convert(that.distance to distance),
                              unit = mass to time)

    operator fun div(that: DistanceAmount<L>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.distance to distance),
                              unit = mass to time)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        * convert(that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: EnergyAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance),
                             unit = distance to time)

    operator fun div(that: LinearDensityAmount<L, M>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> Speed

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass)
                             * convert(that.time to time),
                    unit = distance to time)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> TemperatureGradient

    @JvmName("divHeatCapacity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: HeatCapacityAmount<L, M, K, T>) =
        TemperatureGradientAmount(amount = amount / that.amount
                                           / convert(that.distance to distance,
                                                     that.distance to distance,
                                                     that.mass to mass)
                                           * convert(that.time to time,
                                                     that.time to time),
                                  unit = that.temperature to distance)

    operator fun <K : Unit<Temperature>> div(that: HeatCapacityAmount<L, M, K, T>) =
        TemperatureGradientAmount(amount = amount / that.amount,
                                  unit = that.temperature to distance)

    // endregion

    // region -> Time

    @JvmName("divYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: YankAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Yank

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun times(that: FrequencyAmount<T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, mass) to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        YankAmount(amount = amount / that.amount
                            / convert(that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun div(that: TimeAmount<T>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}
