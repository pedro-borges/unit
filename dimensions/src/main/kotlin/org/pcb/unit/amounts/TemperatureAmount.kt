package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class TemperatureAmount<K : Unit<Temperature>>(
        amount: Number,
        unit: K)
    : Amount<Temperature>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = Temperature::class,
                              units = mapOf(unit to 1)))
{
    val temperature = unit

    // region -> Temperature

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        TemperatureAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = temperature)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        TemperatureAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = temperature)

    @JvmName("plusTemperature")
    operator fun <K : Unit<Temperature>> plus(that: TemperatureAmount<K>) =
        TemperatureAmount(amount = amount + that.amount
                                   * convert(that.temperature to temperature),
                          unit = temperature)

    operator fun plus(that: TemperatureAmount<K>) =
        TemperatureAmount(amount = amount + that.amount,
                          unit = temperature)

    @JvmName("minusTemperature")
    operator fun <K : Unit<Temperature>> minus(that: TemperatureAmount<K>) =
        TemperatureAmount(amount = amount - that.amount
                                   * convert(that.temperature to temperature),
                          unit = temperature)

    operator fun minus(that: TemperatureAmount<K>) =
        TemperatureAmount(amount = amount - that.amount,
                          unit = temperature)

    // endregion

    // region -> Scalar

    @JvmName("divTemperature")
    operator fun <K : Unit<Temperature>> div(that: TemperatureAmount<K>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.temperature to temperature),
                     unit = NUMERIC)

    operator fun div(that: TemperatureAmount<K>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesThermalExpansionCoefficient")
    operator fun <K : Unit<Temperature>> times(that: ThermalExpansionCoefficientAmount<K>) =
        ScalarAmount(amount = amount * that.amount
                              / convert(that.temperature to temperature),
                     unit = NUMERIC)

    operator fun times(that: ThermalExpansionCoefficientAmount<K>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Distance

    @JvmName("divTemperatureGradient")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>> div(that: TemperatureGradientAmount<D, K>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.temperature to temperature),
                       unit = that.distance)

    operator fun <D : Unit<Distance>> div(that: TemperatureGradientAmount<D, K>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = that.distance)

    // endregion

    // region -> Energy

    @JvmName("timesHeatCapacity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: HeatCapacityAmount<D, M, K, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.temperature to temperature),
                     unit = U2(that.distance, that.mass) to that.time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: HeatCapacityAmount<D, M, K, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> MolarEnergy

    @JvmName("timesMolarHeatCapacity")
    operator fun <N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        MolarEnergyAmount(amount = amount * that.amount
                                   / convert(that.temperature to temperature),
                          unit = U2(that.distance, that.mass) to U2(that.amountOfSubstance, that.time))

    operator fun <N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(that.distance, that.mass) to U2(that.amountOfSubstance, that.time))

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesSpecificHeatCapacity")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> times(that: SpecificHeatCapacityAmount<D, K, T>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      / convert(that.temperature to temperature),
                             unit = that.distance to that.time)

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpecificHeatCapacityAmount<D, K, T>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = that.distance to that.time)

    // endregion

    // region -> TemperatureGradient

    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        TemperatureGradientAmount(amount = amount / that.amount,
                                  unit = temperature to that.distance)

    operator fun <D : Unit<Distance>> times(that: SpacialFrequencyAmount<D>) =
        TemperatureGradientAmount(amount = amount * that.amount,
                                  unit = temperature to that.distance)

    // endregion

    // region -> Yank

    @JvmName("timesThermalConductivity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: ThermalConductivityAmount<D, M, K, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.temperature to temperature),
                   unit = U2(that.distance, that.mass) to that.time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ThermalConductivityAmount<D, M, K, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, that.mass) to that.time)

    // endregion
}
