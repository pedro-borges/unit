package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Kelvin.KELVIN
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.AmountOfSubstance
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.SpecificHeatCapacity
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SpecificHeatCapacityAmount<D : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<D, U2<K, T>>)
    : Amount<SpecificHeatCapacity>(amount = amount.toBigDecimal(),
                                   unit = CompositeUnit(
                                       domain = SpecificHeatCapacity::class,
                                       units = mapOf(unit.major to 2,
                                                     unit.minor.first to -1,
                                                     unit.minor.second to -2)))
{
    val distance = unit.major
    val temperature = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<SpecificHeatCapacity>) =
            SpecificHeatCapacityAmount(amount = amount * unit.scale,
                                       unit = METER to U2(KELVIN, SECOND))
    }

    // region -> SpecificHeatCapacity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpecificHeatCapacityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                   unit = distance to U2(temperature, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                   unit = distance to U2(temperature, time))

    @JvmName("plusSpecificHeatCapacity")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> plus(that: SpecificHeatCapacityAmount<D, K, T>) =
        SpecificHeatCapacityAmount(amount = amount + that.amount
                                            * convert(that.distance to distance,
                                                      that.distance to distance)
                                            / convert(that.temperature to temperature,
                                                      that.time to time,
                                                      that.time to time),
                                   unit = distance to U2(temperature, time))

    operator fun plus(that: SpecificHeatCapacityAmount<D, K, T>) =
        SpecificHeatCapacityAmount(amount = amount + that.amount,
                                   unit = distance to U2(temperature, time))

    @JvmName("minusSpecificHeatCapacity")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> minus(that: SpecificHeatCapacityAmount<D, K, T>) =
        SpecificHeatCapacityAmount(amount = amount - that.amount
                                            * convert(that.distance to distance,
                                                      that.distance to distance)
                                            / convert(that.temperature to temperature,
                                                      that.time to time,
                                                      that.time to time),
                                   unit = distance to U2(temperature, time))

    operator fun minus(that: SpecificHeatCapacityAmount<D, K, T>) =
        SpecificHeatCapacityAmount(amount = amount - that.amount,
                                   unit = distance to U2(temperature, time))

    // endregion

    // region -> Scalar

    @JvmName("divSpecificHeatCapacity")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> div(that: SpecificHeatCapacityAmount<D, K, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance)
                              * convert(that.temperature to temperature,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: SpecificHeatCapacityAmount<D, K, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("timesTemperatureGradient")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>> times(that: TemperatureGradientAmount<D, K>) =
        AccelerationAmount(amount = amount * that.amount
                                    * convert(that.temperature to temperature)
                                    / convert(that.distance to distance),
                           unit = distance to time)

    operator fun times(that: TemperatureGradientAmount<D, K>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = distance to time)

    // endregion

    // region -> HeatCapacity

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        HeatCapacityAmount(amount = amount * that.amount,
                           unit = U2(distance, that.mass) to U2(temperature, time))

    // endregion

    // region -> Molality

    @JvmName("divMolarHeatCapacity")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        MolalityAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance)
                                * convert(that.temperature to temperature,
                                          that.time to time,
                                          that.time to time),
                       unit = that.amountOfSubstance to that.mass)

    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> div(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        MolalityAmount(amount = amount / that.amount,
                       unit = that.amountOfSubstance to that.mass)

    // endregion

    // region -> MolarHeatCapacity

    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> div(that: MolalityAmount<N, M>) =
        MolarHeatCapacityAmount(amount = amount / that.amount,
                                unit = U2(distance, that.mass) to U3(that.amountOfSubstance, temperature, time))

    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> times(that: MolarMassAmount<N, M>) =
        MolarHeatCapacityAmount(amount = amount * that.amount,
                                unit = U2(distance, that.mass) to U3(that.amountOfSubstance, temperature, time))

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesTemperature")
    operator fun <K : Unit<Temperature>> times(that: TemperatureAmount<K>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.temperature to temperature),
                             unit = distance to time)

    operator fun times(that: TemperatureAmount<K>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to time)

    @JvmName("divThermalExpansionCoefficient")
    operator fun <K : Unit<Temperature>> div(that: ThermalExpansionCoefficientAmount<K>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      * convert(that.temperature to temperature),
                             unit = distance to time)

    operator fun div(that: ThermalExpansionCoefficientAmount<K>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> ThermalExpansionCoefficient

    @JvmName("divSpecificEnergy")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<D, T>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount
                                                   / convert(that.distance to distance,
                                                             that.distance to distance)
                                                   * convert(that.time to time,
                                                             that.time to time),
                                          unit = NUMERIC to temperature)

    operator fun div(that: SpecificEnergyAmount<D, T>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount,
                                          unit = NUMERIC to temperature)

    // endregion
}
