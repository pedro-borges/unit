package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Mole.MOLE
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MolarEnergy
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

open class MolarEnergyAmount<L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, U2<N, T>>)
    : Amount<MolarEnergy>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = MolarEnergy::class,
                              units = mapOf(unit.major.first to 2,
                                            unit.major.second to 1,
                                            unit.minor.first to -1,
                                            unit.minor.second to -2)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val substance = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MolarEnergy>) =
            MolarEnergyAmount(amount = amount * unit.scale,
                              unit = U2(METER, kilo(GRAM)) to U2(MOLE, SECOND))
    }

    // region -> MolarEnergy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MolarEnergyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = U2(distance, mass) to U2(substance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolarEnergyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = U2(distance, mass) to U2(substance, time))

    @JvmName("plusMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> plus(that: MolarEnergyAmount<L, M, N, T>) =
        MolarEnergyAmount(amount = amount + that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.mass to mass)
                                   / convert(that.substance to substance,
                                             that.time to time,
                                             that.time to time),
                          unit = U2(distance, mass) to U2(substance, time))

    operator fun plus(that: MolarEnergyAmount<L, M, N, T>) =
        MolarEnergyAmount(amount = amount + that.amount,
                          unit = U2(distance, mass) to U2(substance, time))

    @JvmName("minusMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> minus(that: MolarEnergyAmount<L, M, N, T>) =
        MolarEnergyAmount(amount = amount - that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.mass to mass)
                                   / convert(that.substance to substance,
                                             that.time to time,
                                             that.time to time),
                          unit = U2(distance, mass) to U2(substance, time))

    operator fun minus(that: MolarEnergyAmount<L, M, N, T>) =
        MolarEnergyAmount(amount = amount - that.amount,
                          unit = U2(distance, mass) to U2(substance, time))

    // endregion

    // region -> Scalar

    @JvmName("divMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> div(that: MolarEnergyAmount<L, M, N, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.substance to substance,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MolarEnergyAmount<L, M, N, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Energy

    @JvmName("timesSubstance")
    operator fun <N : Unit<Substance>> times(that: SubstanceAmount<N>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.substance to substance),
                     unit = U2(distance, mass) to time)

    operator fun times(that: SubstanceAmount<N>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> EnergyDensity

    @JvmName("divMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarVolumeAmount<L, N>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance)
                                     * convert(that.substance to substance),
                            unit = mass to U2(distance, time))

    operator fun div(that: MolarVolumeAmount<L, N>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(distance, time))

    @JvmName("timesMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarityAmount<L, N>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.substance to substance)
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance),
                            unit = mass to U2(distance, time))

    operator fun times(that: MolarityAmount<L, N>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> MolarHeatCapacity

    operator fun <K : Unit<Temperature>> div(that: TemperatureAmount<K>) =
        MolarHeatCapacityAmount(amount = amount / that.amount,
                                unit = U2(distance, mass) to U3(substance, that.temperature, time))

    operator fun <K : Unit<Temperature>> times(that: ThermalExpansionCoefficientAmount<K>) =
        MolarHeatCapacityAmount(amount = amount * that.amount,
                                unit = U2(distance, mass) to U3(substance, that.temperature, time))

    // endregion

    // region -> MolarMass

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        MolarMassAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance)
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = mass to substance)

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        MolarMassAmount(amount = amount / that.amount,
                        unit = mass to substance)

    // endregion

    // region -> MolarVolume

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        MolarVolumeAmount(amount = amount / that.amount
                                   / convert(that.mass to mass)
                                   * convert(that.distance to distance,
                                             that.time to time,
                                             that.time to time),
                          unit = distance to substance)

    operator fun div(that: EnergyDensityAmount<L, M, T>) =
        MolarVolumeAmount(amount = amount / that.amount,
                          unit = distance to substance)

    // endregion

    // region -> PowerDensity

    @JvmName("timesReactionRate")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> times(that: ReactionRateAmount<L, N, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    * convert(that.substance to substance)
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance,
                                              that.time to time),
                           unit = mass to U2(distance, time))

    operator fun times(that: ReactionRateAmount<L, N, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = mass to U2(distance, time))

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> times(that: MolalityAmount<M, N>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.substance to substance)
                                      / convert(that.mass to mass),
                             unit = distance to time)

    operator fun times(that: MolalityAmount<M, N>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to time)

    @JvmName("divMolarMass")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolarMassAmount<M, N>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.substance to substance),
                             unit = distance to time)

    operator fun div(that: MolarMassAmount<M, N>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> Temperature

    @JvmName("divMolarHeatCapacity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, K : Unit<Temperature>, T : Unit<Time>> div(that: MolarHeatCapacityAmount<L, M, N, K, T>) =
        TemperatureAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.mass to mass)
                                   * convert(that.substance to substance,
                                             that.time to time,
                                             that.time to time),
                          unit = that.temperature)

    operator fun <K : Unit<Temperature>> div(that: MolarHeatCapacityAmount<L, M, N, K, T>) =
        TemperatureAmount(amount = amount / that.amount,
                          unit = that.temperature)

    // endregion
}
