package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Kelvin.KELVIN
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.HeatCapacity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class HeatCapacityAmount<L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, U2<K, T>>)
    : Amount<HeatCapacity>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = HeatCapacity::class,
                               units = mapOf(unit.major.first to 2,
                                             unit.major.second to 1,
                                             unit.minor.first to -1,
                                             unit.minor.second to -2)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val temperature = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<HeatCapacity>) =
            HeatCapacityAmount(amount = amount * unit.scale,
                               unit = U2(METER, kilo(GRAM)) to U2(KELVIN, SECOND))
    }

    // region -> HeatCapacity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        HeatCapacityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = U2(distance, mass) to U2(temperature, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        HeatCapacityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = U2(distance, mass) to U2(temperature, time))

    @JvmName("plusHeatCapacity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> plus(that: HeatCapacityAmount<L, M, K, T>) =
        HeatCapacityAmount(amount = amount + that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance,
                                              that.mass to mass)
                                    / convert(that.temperature to temperature,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(distance, mass) to U2(temperature, time))

    operator fun plus(that: HeatCapacityAmount<L, M, K, T>) =
        HeatCapacityAmount(amount = amount + that.amount,
                           unit = U2(distance, mass) to U2(temperature, time))

    @JvmName("minusHeatCapacity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> minus(that: HeatCapacityAmount<L, M, K, T>) =
        HeatCapacityAmount(amount = amount - that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance,
                                              that.mass to mass)
                                    / convert(that.temperature to temperature,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(distance, mass) to U2(temperature, time))

    operator fun minus(that: HeatCapacityAmount<L, M, K, T>) =
        HeatCapacityAmount(amount = amount - that.amount,
                           unit = U2(distance, mass) to U2(temperature, time))

    // endregion

    // region -> Scalar

    @JvmName("divHeatCapacity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: HeatCapacityAmount<L, M, K, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.temperature to temperature,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: HeatCapacityAmount<L, M, K, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Energy

    @JvmName("timesTemperature")
    operator fun <K : Unit<Temperature>> times(that: TemperatureAmount<K>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.temperature to temperature),
                     unit = U2(distance, mass) to time)

    operator fun times(that: TemperatureAmount<K>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("divThermalExpansionCoefficient")
    operator fun <K : Unit<Temperature>> div(that: ThermalExpansionCoefficientAmount<K>) =
        EnergyAmount(amount = amount / that.amount
                              * convert(that.temperature to temperature),
                     unit = U2(distance, mass) to time)

    operator fun div(that: ThermalExpansionCoefficientAmount<K>) =
        EnergyAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Force

    @JvmName("timesTemperatureGradient")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>> times(that: TemperatureGradientAmount<L, K>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.temperature to temperature)
                             / convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: TemperatureGradientAmount<L, K>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Mass

    @JvmName("divSpecificHeatCapacity")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> div(that: SpecificHeatCapacityAmount<L, K, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance)
                            * convert(that.temperature to temperature,
                                      that.time to time,
                                      that.time to time),
                   unit = mass)

    operator fun div(that: SpecificHeatCapacityAmount<L, K, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MolarHeatCapacity

    operator fun <N : Unit<Substance>> div(that: SubstanceAmount<N>) =
        MolarHeatCapacityAmount(amount = amount / that.amount,
                                unit = U2(distance, mass) to U3(that.substance, temperature, time))

    // endregion

    // region -> SpecificHeatCapacity

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount
                                            / convert(that.mass to mass),
                                   unit = distance to U2(temperature, time))

    operator fun div(that: MassAmount<M>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount,
                                   unit = distance to U2(temperature, time))

    // endregion

    // region -> Substance

    @JvmName("divMolarHeatCapacity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, K : Unit<Temperature>, T : Unit<Time>> div(that: MolarHeatCapacityAmount<L, M, N, K, T>) =
        SubstanceAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.temperature to temperature,
                                           that.time to time,
                                           that.time to time),
                        unit = that.substance)

    operator fun <N : Unit<Substance>> div(that: MolarHeatCapacityAmount<L, M, N, K, T>) =
        SubstanceAmount(amount = amount / that.amount,
                        unit = that.substance)

    // endregion

    // region -> ThermalExpansionCoefficient

    @JvmName("divEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<L, M, T>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount
                                                   / convert(that.distance to distance,
                                                             that.distance to distance,
                                                             that.mass to mass)
                                                   * convert(that.time to time,
                                                             that.time to time),
                                          unit = NUMERIC to temperature)

    operator fun div(that: EnergyAmount<L, M, T>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount,
                                          unit = NUMERIC to temperature)

    // endregion
}
