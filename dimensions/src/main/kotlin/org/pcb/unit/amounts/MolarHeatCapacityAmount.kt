package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Kelvin.KELVIN
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Mole.MOLE
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MolarHeatCapacity
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

open class MolarHeatCapacityAmount<N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, U3<N, K, T>>)
    : Amount<MolarHeatCapacity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = MolarHeatCapacity::class,
                                    units = mapOf(unit.major.first to 2,
                                                  unit.major.second to 1,
                                                  unit.minor.first to -1,
                                                  unit.minor.second to -1,
                                                  unit.minor.third to -2)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val substance = unit.minor.first
    val temperature = unit.minor.second
    val time = unit.minor.third

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MolarHeatCapacity>) =
            MolarHeatCapacityAmount(amount = amount * unit.scale,
                                    unit = U2(METER, kilo(GRAM)) to U3(MOLE, KELVIN, SECOND))
    }

    // region -> MolarHeatCapacity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MolarHeatCapacityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                unit = U2(distance, mass) to U3(substance, temperature, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolarHeatCapacityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                unit = U2(distance, mass) to U3(substance, temperature, time))

    @JvmName("plusMolarHeatCapacity")
    operator fun <N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> plus(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        MolarHeatCapacityAmount(amount = amount + that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance,
                                                   that.mass to mass)
                                         / convert(that.substance to substance,
                                                   that.temperature to temperature,
                                                   that.time to time,
                                                   that.time to time),
                                unit = U2(distance, mass) to U3(substance, temperature, time))

    operator fun plus(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        MolarHeatCapacityAmount(amount = amount + that.amount,
                                unit = U2(distance, mass) to U3(substance, temperature, time))

    @JvmName("minusMolarHeatCapacity")
    operator fun <N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> minus(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        MolarHeatCapacityAmount(amount = amount - that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance,
                                                   that.mass to mass)
                                         / convert(that.substance to substance,
                                                   that.temperature to temperature,
                                                   that.time to time,
                                                   that.time to time),
                                unit = U2(distance, mass) to U3(substance, temperature, time))

    operator fun minus(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        MolarHeatCapacityAmount(amount = amount - that.amount,
                                unit = U2(distance, mass) to U3(substance, temperature, time))

    // endregion

    // region -> Scalar

    @JvmName("divMolarHeatCapacity")
    operator fun <N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.substance to substance,
                                        that.temperature to temperature,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> HeatCapacity

    @JvmName("timesSubstance")
    operator fun <N : Unit<Substance>> times(that: SubstanceAmount<N>) =
        HeatCapacityAmount(amount = amount * that.amount
                                    * convert(that.substance to substance),
                           unit = U2(distance, mass) to U2(temperature, time))

    operator fun times(that: SubstanceAmount<N>) =
        HeatCapacityAmount(amount = amount * that.amount,
                           unit = U2(distance, mass) to U2(temperature, time))

    // endregion

    // region -> MolarEnergy

    @JvmName("timesTemperature")
    operator fun <K : Unit<Temperature>> times(that: TemperatureAmount<K>) =
        MolarEnergyAmount(amount = amount * that.amount
                                   * convert(that.temperature to temperature),
                          unit = U2(distance, mass) to U2(substance, time))

    operator fun times(that: TemperatureAmount<K>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(distance, mass) to U2(substance, time))

    @JvmName("divThermalExpansionCoefficient")
    operator fun <K : Unit<Temperature>> div(that: ThermalExpansionCoefficientAmount<K>) =
        MolarEnergyAmount(amount = amount / that.amount
                                   * convert(that.temperature to temperature),
                          unit = U2(distance, mass) to U2(substance, time))

    operator fun div(that: ThermalExpansionCoefficientAmount<K>) =
        MolarEnergyAmount(amount = amount / that.amount,
                          unit = U2(distance, mass) to U2(substance, time))

    // endregion

    // region -> MolarMass

    @JvmName("divSpecificHeatCapacity")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> div(that: SpecificHeatCapacityAmount<D, K, T>) =
        MolarMassAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance)
                                 * convert(that.temperature to temperature,
                                           that.time to time,
                                           that.time to time),
                        unit = mass to substance)

    operator fun div(that: SpecificHeatCapacityAmount<D, K, T>) =
        MolarMassAmount(amount = amount / that.amount,
                        unit = mass to substance)

    // endregion

    // region -> SpecificHeatCapacity

    @JvmName("timesMolality")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> times(that: MolalityAmount<N, M>) =
        SpecificHeatCapacityAmount(amount = amount * that.amount
                                            * convert(that.substance to substance)
                                            / convert(that.mass to mass),
                                   unit = distance to U2(temperature, time))

    operator fun times(that: MolalityAmount<N, M>) =
        SpecificHeatCapacityAmount(amount = amount * that.amount,
                                   unit = distance to U2(temperature, time))

    @JvmName("divMolarMass")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> div(that: MolarMassAmount<N, M>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount
                                            / convert(that.mass to mass)
                                            * convert(that.substance to substance),
                                   unit = distance to U2(temperature, time))

    operator fun div(that: MolarMassAmount<N, M>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount,
                                   unit = distance to U2(temperature, time))

    // endregion

    // region -> ThermalExpansionCoefficient

    @JvmName("divMolarEnergy")
    operator fun <N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MolarEnergyAmount<N, D, M, T>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount
                                                   / convert(that.distance to distance,
                                                             that.distance to distance,
                                                             that.mass to mass)
                                                   * convert(that.substance to substance,
                                                             that.time to time,
                                                             that.time to time),
                                          unit = NUMERIC to temperature)

    operator fun div(that: MolarEnergyAmount<N, D, M, T>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount,
                                          unit = NUMERIC to temperature)

    // endregion
}
