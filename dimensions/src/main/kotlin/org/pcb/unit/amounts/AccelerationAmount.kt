package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Acceleration
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AccelerationAmount<L : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<Acceleration>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = Acceleration::class,
                               units = mapOf(unit.major to 1,
                                             unit.minor to -2)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Acceleration>) =
            AccelerationAmount(amount = amount * unit.scale,
                               unit = METER to SECOND)
    }

    // region -> Acceleration

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AccelerationAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = distance to time)

    @JvmName("plusAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount
                                    * convert(that.distance to distance)
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = distance to time)

    operator fun plus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount + that.amount,
                           unit = distance to time)

    @JvmName("minusAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount
                                    * convert(that.distance to distance)
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = distance to time)

    operator fun minus(that: AccelerationAmount<L, T>) =
        AccelerationAmount(amount = amount - that.amount,
                           unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: AccelerationAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance),
                            unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, time))

    // endregion

    // region -> EnergyDensityGradient

    @JvmName("timesMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        EnergyDensityGradientAmount(amount = amount * that.amount
                                             / convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.distance to distance),
                                    unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        EnergyDensityGradientAmount(amount = amount * that.amount,
                                    unit = that.mass to U2(distance, time))

    @JvmName("divSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        EnergyDensityGradientAmount(amount = amount / that.amount
                                             / convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.distance to distance),
                                    unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        EnergyDensityGradientAmount(amount = amount / that.amount,
                                    unit = that.mass to U2(distance, time))

    // endregion

    // region -> Force

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        FrequencyAmount(amount = amount * that.amount
                                 * convert(that.time to time)
                                 / convert(that.distance to distance),
                        unit = NUMERIC to time)

    operator fun times(that: PaceAmount<L, T>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to time)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance)
                                 * convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: SpeedAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Intensity

    @JvmName("timesViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        IntensityAmount(amount = amount * that.amount
                                 / convert(that.distance to distance,
                                           that.time to time),
                        unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: ViscosityAmount<L, M, T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = that.mass to time)

    // endregion

    // region -> Jerk

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        JerkAmount(amount = amount * that.amount
                            / convert(that.time to time),
                   unit = distance to time)

    operator fun times(that: FrequencyAmount<T>) =
        JerkAmount(amount = amount * that.amount,
                   unit = distance to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        JerkAmount(amount = amount / that.amount
                            / convert(that.time to time),
                   unit = distance to time)

    operator fun div(that: TimeAmount<T>) =
        JerkAmount(amount = amount / that.amount,
                   unit = distance to time)

    // endregion

    // region -> Power

    @JvmName("timesMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: MomentumAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.distance to distance),
                              unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance)
                                        * convert(that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.distance to distance),
                             unit = distance to time)

    operator fun times(that: DistanceAmount<L>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to time)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      * convert(that.distance to distance),
                             unit = distance to time)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> SpecificHeatCapacity

    @JvmName("divTemperatureGradient")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>> div(that: TemperatureGradientAmount<L, K>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount
                                            * convert(that.distance to distance),
                                   unit = distance to U2(that.temperature, time))

    operator fun <K : Unit<Temperature>> div(that: TemperatureGradientAmount<L, K>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount,
                                   unit = distance to U2(that.temperature, time))

    // endregion

    // region -> SpecificVolume

    @JvmName("divEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityGradientAmount<L, M, T>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.time to time,
                                                that.time to time),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> div(that: EnergyDensityGradientAmount<L, M, T>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = distance to that.mass)

    // endregion

    // region -> Speed

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        SpeedAmount(amount = amount / that.amount
                             * convert(that.time to time),
                    unit = distance to time)

    operator fun div(that: FrequencyAmount<T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount
                             * convert(that.time to time),
                    unit = distance to time)

    operator fun times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to time)

    // endregion

    // region -> TemperatureGradient

    @JvmName("divSpecificHeatCapacity")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> div(that: SpecificHeatCapacityAmount<L, K, T>) =
        TemperatureGradientAmount(amount = amount / that.amount
                                           / convert(that.distance to distance,
                                                     that.distance to distance)
                                           * convert(that.time to time,
                                                     that.time to time),
                                  unit = that.temperature to distance)

    operator fun <K : Unit<Temperature>> div(that: SpecificHeatCapacityAmount<L, K, T>) =
        TemperatureGradientAmount(amount = amount / that.amount,
                                  unit = that.temperature to distance)

    // endregion

    // region -> Time

    @JvmName("divJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: JerkAmount<L, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: JerkAmount<L, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Yank

    @JvmName("timesMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.time to time),
                   unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: MassFlowRateAmount<M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to time)

    // endregion
}
