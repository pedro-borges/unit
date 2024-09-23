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
import org.pcb.unit.dimensions.EnergyFluxDensity
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

open class EnergyFluxDensityAmount<M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, T>)
    : Amount<EnergyFluxDensity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = EnergyFluxDensity::class,
                                    units = mapOf(unit.major to 1,
                                                  unit.minor to -3)))
{
    val mass = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<EnergyFluxDensity>) =
            EnergyFluxDensityAmount(amount = amount * unit.scale,
                                    unit = kilo(GRAM) to SECOND)
    }

    // region -> EnergyFluxDensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyFluxDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                unit = mass to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyFluxDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                unit = mass to time)

    @JvmName("plusEnergyFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> plus(that: EnergyFluxDensityAmount<M, T>) =
        EnergyFluxDensityAmount(amount = amount + that.amount
                                         * convert(that.mass to mass)
                                         / convert(that.time to time,
                                                   that.time to time,
                                                   that.time to time),
                                unit = mass to time)

    operator fun plus(that: EnergyFluxDensityAmount<M, T>) =
        EnergyFluxDensityAmount(amount = amount + that.amount,
                                unit = mass to time)

    @JvmName("minusEnergyFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> minus(that: EnergyFluxDensityAmount<M, T>) =
        EnergyFluxDensityAmount(amount = amount - that.amount
                                         * convert(that.mass to mass)
                                         / convert(that.time to time,
                                                   that.time to time,
                                                   that.time to time),
                                unit = mass to time)

    operator fun minus(that: EnergyFluxDensityAmount<M, T>) =
        EnergyFluxDensityAmount(amount = amount - that.amount,
                                unit = mass to time)

    // endregion

    // region -> Scalar

    @JvmName("divEnergyFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: EnergyFluxDensityAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: EnergyFluxDensityAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Distance

    @JvmName("divPowerDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<D, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = that.distance)

    operator fun <D : Unit<Distance>> div(that: PowerDensityAmount<D, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = that.distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.time to time),
                            unit = mass to U2(that.distance, time))

    operator fun <D : Unit<Distance>> times(that: PaceAmount<D, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(that.distance, time))

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     * convert(that.time to time),
                            unit = mass to U2(that.distance, time))

    operator fun <D : Unit<Distance>> div(that: SpeedAmount<D, T>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(that.distance, time))

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
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerAmount<D, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = NUMERIC to that.distance)

    operator fun <D : Unit<Distance>> div(that: PowerAmount<D, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to that.distance)

    // endregion

    // region -> Power

    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.distance, mass) to time)

    operator fun <D : Unit<Distance>> div(that: FuelEfficiencyAmount<D>) =
        PowerAmount(amount = amount / that.amount,
                    unit = U2(that.distance, mass) to time)

    // endregion

    // region -> PowerDensity

    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        PowerDensityAmount(amount = amount / that.amount,
                           unit = mass to U2(that.distance, time))

    operator fun <D : Unit<Distance>> times(that: SpacialFrequencyAmount<D>) =
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
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<D, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.time to time,
                                                  that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to that.distance)

    operator fun <D : Unit<Distance>> div(that: YankAmount<D, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to that.distance)

    // endregion

    // region -> Speed

    @JvmName("divEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<D, M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass)
                             * convert(that.time to time,
                                       that.time to time),
                    unit = that.distance to time)

    operator fun <D : Unit<Distance>> div(that: EnergyDensityAmount<D, M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = that.distance to time)

    // endregion

    // region -> TemperatureGradient

    @JvmName("divThermalConductivity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalConductivityAmount<D, M, K, T>) =
        TemperatureGradientAmount(amount = amount / that.amount
                                           / convert(that.mass to mass)
                                           * convert(that.time to time,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = that.temperature to that.distance)

    operator fun <D : Unit<Distance>, K : Unit<Temperature>> div(that: ThermalConductivityAmount<D, M, K, T>) =
        TemperatureGradientAmount(amount = amount / that.amount,
                                  unit = that.temperature to that.distance)

    // endregion

    // region -> ThermalConductivity

    operator fun <D : Unit<Distance>, K : Unit<Temperature>> div(that: TemperatureGradientAmount<D, K>) =
        ThermalConductivityAmount(amount = amount / that.amount,
                                  unit = U2(that.distance, mass) to U2(that.temperature, time))

    // endregion

    // region -> Yank

    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, mass) to time)

    operator fun <D : Unit<Distance>> div(that: SpacialFrequencyAmount<D>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(that.distance, mass) to time)

    // endregion
}
