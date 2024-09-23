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

open class DistanceAmount<D : Unit<Distance>>(
        amount: Number,
        unit: D)
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
    operator fun <D : Unit<Distance>> plus(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount + that.amount
                                * convert(that.distance to distance),
                       unit = distance)

    operator fun plus(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount + that.amount,
                       unit = distance)

    @JvmName("minusDistance")
    operator fun <D : Unit<Distance>> minus(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount - that.amount
                                * convert(that.distance to distance),
                       unit = distance)

    operator fun minus(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount - that.amount,
                       unit = distance)

    // endregion

    // region -> Scalar

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: DistanceAmount<D>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesSpacialFrequency")
    operator fun <D : Unit<Distance>> times(that: SpacialFrequencyAmount<D>) =
        ScalarAmount(amount = amount * that.amount
                              / convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: SpacialFrequencyAmount<D>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Action

    @JvmName("timesMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<D, M, T>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<D, M, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Area

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.distance to distance),
                   unit = distance)

    operator fun times(that: DistanceAmount<D>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    @JvmName("divSpacialFrequency")
    operator fun <D : Unit<Distance>> div(that: SpacialFrequencyAmount<D>) =
        AreaAmount(amount = amount / that.amount
                            * convert(that.distance to distance),
                   unit = distance)

    operator fun div(that: SpacialFrequencyAmount<D>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> AreaDensity

    @JvmName("timesMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<D, M>) =
        AreaDensityAmount(amount = amount * that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<D, M>) =
        AreaDensityAmount(amount = amount * that.amount,
                          unit = that.mass to distance)

    @JvmName("divSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = that.mass to distance)

    // endregion

    // region -> Energy

    @JvmName("timesForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> EnergyFluxDensity

    @JvmName("timesPowerDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<D, M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount
                                         / convert(that.distance to distance),
                                unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<D, M, T>) =
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
    operator fun <D : Unit<Distance>> div(that: VolumeAmount<D>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun div(that: VolumeAmount<D>) =
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
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<D, M, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<D, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.distance to distance),
                              unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to that.time)

    // endregion

    // region -> RadiantIntensity

    @JvmName("timesSpectralIntensity")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: SpectralIntensityAmount<A, D, M, T>) =
        RadiantIntensityAmount(amount = amount * that.amount
                                        * convert(that.distance to distance),
                               unit = U2(distance, that.mass) to U2(that.angle, that.time))

    operator fun <A : Unit<Angle>, M : Unit<Mass>, T : Unit<Time>> times(that: SpectralIntensityAmount<A, D, M, T>) =
        RadiantIntensityAmount(amount = amount * that.amount,
                               unit = U2(distance, that.mass) to U2(that.angle, that.time))

    // endregion

    // region -> SpacialFrequency

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun div(that: AreaAmount<D>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    @JvmName("timesFuelEfficiency")
    operator fun <D : Unit<Distance>> times(that: FuelEfficiencyAmount<D>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun times(that: FuelEfficiencyAmount<D>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<D, T>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.distance to distance),
                             unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: AccelerationAmount<D, T>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to that.time)

    // endregion

    // region -> SpecificVolume

    @JvmName("divAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<D, M>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> div(that: AreaDensityAmount<D, M>) =
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
    operator fun <D : Unit<Distance>, K : Unit<Temperature>> times(that: TemperatureGradientAmount<D, K>) =
        TemperatureAmount(amount = amount * that.amount
                                   / convert(that.distance to distance),
                          unit = that.temperature)

    operator fun <K : Unit<Temperature>> times(that: TemperatureGradientAmount<D, K>) =
        TemperatureAmount(amount = amount * that.amount,
                          unit = that.temperature)

    // endregion

    // region -> Time

    @JvmName("timesPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        TimeAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> times(that: PaceAmount<D, T>) =
        TimeAmount(amount = amount * that.amount,
                   unit = that.time)

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> Volume

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun times(that: AreaAmount<D>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    @JvmName("divFuelEfficiency")
    operator fun <D : Unit<Distance>> div(that: FuelEfficiencyAmount<D>) =
        VolumeAmount(amount = amount / that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun div(that: FuelEfficiencyAmount<D>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion

    // region -> Yank

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyFluxDensityAmount<M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to that.time)

    // endregion
}
