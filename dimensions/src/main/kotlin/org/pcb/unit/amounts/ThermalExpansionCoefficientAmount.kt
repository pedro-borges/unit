package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Kelvin.KELVIN
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.ThermalExpansionCoefficient
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ThermalExpansionCoefficientAmount<K : Unit<Temperature>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, K>)
    : Amount<ThermalExpansionCoefficient>(amount = amount.toBigDecimal(),
                                          unit = CompositeUnit(
                                              domain = ThermalExpansionCoefficient::class,
                                              units = mapOf(unit.minor to -1)))
{
    val temperature = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ThermalExpansionCoefficient>) =
            ThermalExpansionCoefficientAmount(amount = amount * unit.scale,
                                              unit = NUMERIC to KELVIN)
    }

    // region -> ThermalExpansionCoefficient

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ThermalExpansionCoefficientAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                          unit = NUMERIC to temperature)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                          unit = NUMERIC to temperature)

    @JvmName("plusThermalExpansionCoefficient")
    operator fun <K : Unit<Temperature>> plus(that: ThermalExpansionCoefficientAmount<K>) =
        ThermalExpansionCoefficientAmount(amount = amount + that.amount
                                                   / convert(that.temperature to temperature),
                                          unit = NUMERIC to temperature)

    operator fun plus(that: ThermalExpansionCoefficientAmount<K>) =
        ThermalExpansionCoefficientAmount(amount = amount + that.amount,
                                          unit = NUMERIC to temperature)

    @JvmName("minusThermalExpansionCoefficient")
    operator fun <K : Unit<Temperature>> minus(that: ThermalExpansionCoefficientAmount<K>) =
        ThermalExpansionCoefficientAmount(amount = amount - that.amount
                                                   / convert(that.temperature to temperature),
                                          unit = NUMERIC to temperature)

    operator fun minus(that: ThermalExpansionCoefficientAmount<K>) =
        ThermalExpansionCoefficientAmount(amount = amount - that.amount,
                                          unit = NUMERIC to temperature)

    // endregion

    // region -> Scalar

    @JvmName("timesTemperature")
    operator fun <K : Unit<Temperature>> times(that: TemperatureAmount<K>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.temperature to temperature),
                     unit = NUMERIC)

    operator fun times(that: TemperatureAmount<K>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divThermalExpansionCoefficient")
    operator fun <K : Unit<Temperature>> div(that: ThermalExpansionCoefficientAmount<K>) =
        ScalarAmount(amount = amount / that.amount
                              * convert(that.temperature to temperature),
                     unit = NUMERIC)

    operator fun div(that: ThermalExpansionCoefficientAmount<K>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> HeatCapacity

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<L, M, T>) =
        HeatCapacityAmount(amount = amount * that.amount,
                           unit = U2(that.distance, that.mass) to U2(temperature, that.time))

    // endregion

    // region -> MolarHeatCapacity

    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> times(that: MolarEnergyAmount<L, M, N, T>) =
        MolarHeatCapacityAmount(amount = amount * that.amount,
                                unit = U2(that.distance, that.mass) to U3(that.substance, temperature, that.time))

    // endregion

    // region -> SpacialFrequency

    @JvmName("timesTemperatureGradient")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>> times(that: TemperatureGradientAmount<L, K>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        * convert(that.temperature to temperature),
                               unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> times(that: TemperatureGradientAmount<L, K>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to that.distance)

    // endregion

    // region -> SpecificHeatCapacity

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        SpecificHeatCapacityAmount(amount = amount * that.amount,
                                   unit = that.distance to U2(temperature, that.time))

    // endregion

    // region -> ThermalConductivity

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        ThermalConductivityAmount(amount = amount * that.amount,
                                  unit = U2(that.distance, that.mass) to U2(temperature, that.time))

    // endregion
}
