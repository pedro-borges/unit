package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
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

    // region -> EnergyFluxDensity

    @JvmName("timesPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount
                                         / convert(that.distance to distance),
                                unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount,
                                unit = that.mass to that.time)

    // endregion

    // region -> Force

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: RadiantExposureAmount<M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

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

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyFluxDensityAmount<M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to that.time)

    // endregion
}
