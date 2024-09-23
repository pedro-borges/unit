package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.SpacialFrequency
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SpacialFrequencyAmount<D : Unit<Distance>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, D>)
    : Amount<SpacialFrequency>(amount = amount.toBigDecimal(),
                               unit = CompositeUnit(
                                   domain = SpacialFrequency::class,
                                   units = mapOf(unit.minor to -1)))
{
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<SpacialFrequency>) =
            SpacialFrequencyAmount(amount = amount * unit.scale,
                                   unit = NUMERIC to METER)
    }

    // region -> SpacialFrequency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpacialFrequencyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                               unit = NUMERIC to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpacialFrequencyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                               unit = NUMERIC to distance)

    @JvmName("plusSpacialFrequency")
    operator fun <D : Unit<Distance>> plus(that: SpacialFrequencyAmount<D>) =
        SpacialFrequencyAmount(amount = amount + that.amount
                                        / convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun plus(that: SpacialFrequencyAmount<D>) =
        SpacialFrequencyAmount(amount = amount + that.amount,
                               unit = NUMERIC to distance)

    @JvmName("minusSpacialFrequency")
    operator fun <D : Unit<Distance>> minus(that: SpacialFrequencyAmount<D>) =
        SpacialFrequencyAmount(amount = amount - that.amount
                                        / convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun minus(that: SpacialFrequencyAmount<D>) =
        SpacialFrequencyAmount(amount = amount - that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Scalar

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: DistanceAmount<D>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divSpacialFrequency")
    operator fun <D : Unit<Distance>> div(that: SpacialFrequencyAmount<D>) =
        ScalarAmount(amount = amount / that.amount
                              * convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: SpacialFrequencyAmount<D>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("timesSpecificEnergy")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<D, T>) =
        AccelerationAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: SpecificEnergyAmount<D, T>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = distance to that.time)

    // endregion

    // region -> Area

    @JvmName("timesVolume")
    operator fun <D : Unit<Distance>> times(that: VolumeAmount<D>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = distance)

    operator fun times(that: VolumeAmount<D>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        DistanceAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun times(that: AreaAmount<D>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    @JvmName("divFuelEfficiency")
    operator fun <D : Unit<Distance>> div(that: FuelEfficiencyAmount<D>) =
        DistanceAmount(amount = amount / that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun div(that: FuelEfficiencyAmount<D>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> EnergyDensity

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: RadiantExposureAmount<M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> EnergyFluxDensity

    @JvmName("timesYank")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<D, M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance),
                                unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<D, M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount,
                                unit = that.mass to that.time)

    // endregion

    // region -> Force

    @JvmName("timesEnergy")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<D, M, T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<D, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Frequency

    @JvmName("divPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 * convert(that.distance to distance),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> div(that: PaceAmount<D, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to that.time)

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        FrequencyAmount(amount = amount * that.amount
                                 * convert(that.distance to distance),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun div(that: DistanceAmount<D>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    @JvmName("timesSpacialFrequency")
    operator fun <D : Unit<Distance>> times(that: SpacialFrequencyAmount<D>) =
        FuelEfficiencyAmount(amount = amount * that.amount
                                      / convert(that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun times(that: SpacialFrequencyAmount<D>) =
        FuelEfficiencyAmount(amount = amount * that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> MassDensity

    @JvmName("timesAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<D, M>) =
        MassDensityAmount(amount = amount * that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<D, M>) =
        MassDensityAmount(amount = amount * that.amount,
                          unit = that.mass to distance)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<D, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance),
                           unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<D, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to that.time)

    // endregion

    // region -> Momentum

    @JvmName("timesAction")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<D, M, T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<D, M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Pace

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = that.time to distance)

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        PaceAmount(amount = amount * that.amount,
                   unit = that.time to distance)

    // endregion

    // region -> PowerDensity

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyFluxDensityAmount<M, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance),
                              unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to that.time)

    // endregion

    // region -> SpectralIntensity

    @JvmName("timesRadiantIntensity")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: RadiantIntensityAmount<A, D, M, T>) =
        SpectralIntensityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = U2(distance, that.mass) to U2(that.angle, that.time))

    operator fun <A : Unit<Angle>, M : Unit<Mass>, T : Unit<Time>> times(that: RadiantIntensityAmount<A, D, M, T>) =
        SpectralIntensityAmount(amount = amount * that.amount,
                                unit = U2(distance, that.mass) to U2(that.angle, that.time))

    // endregion

    // region -> TemperatureGradient

    operator fun <K : Unit<Temperature>> times(that: TemperatureAmount<K>) =
        TemperatureGradientAmount(amount = amount * that.amount,
                                  unit = that.temperature to distance)

    operator fun <K : Unit<Temperature>> div(that: ThermalExpansionCoefficientAmount<K>) =
        TemperatureGradientAmount(amount = amount / that.amount,
                                  unit = that.temperature to distance)

    // endregion

    // region -> ThermalExpansionCoefficient

    @JvmName("divTemperatureGradient")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>> div(that: TemperatureGradientAmount<D, K>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount
                                                   * convert(that.distance to distance),
                                          unit = NUMERIC to that.temperature)

    operator fun <K : Unit<Temperature>> div(that: TemperatureGradientAmount<D, K>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount,
                                          unit = NUMERIC to that.temperature)

    // endregion

    // region -> Yank

    @JvmName("timesPower")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<D, M, T>) =
        YankAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<D, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to that.time)

    // endregion
}
