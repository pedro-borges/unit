package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Mole.MOLE
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Molality
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

open class MolalityAmount<M : Unit<Mass>, N : Unit<Substance>>(
        amount: Number,
        unit: Pair<N, M>)
    : Amount<Molality>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Molality::class,
                           units = mapOf(unit.major to 1,
                                         unit.minor to -1)))
{
    val substance = unit.major
    val mass = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Molality>) =
            MolalityAmount(amount = amount * unit.scale,
                           unit = MOLE to kilo(GRAM))
    }

    // region -> Molality

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MolalityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                       unit = substance to mass)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolalityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = substance to mass)

    @JvmName("plusMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> plus(that: MolalityAmount<M, N>) =
        MolalityAmount(amount = amount + that.amount
                                * convert(that.substance to substance)
                                / convert(that.mass to mass),
                       unit = substance to mass)

    operator fun plus(that: MolalityAmount<M, N>) =
        MolalityAmount(amount = amount + that.amount,
                       unit = substance to mass)

    @JvmName("minusMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> minus(that: MolalityAmount<M, N>) =
        MolalityAmount(amount = amount - that.amount
                                * convert(that.substance to substance)
                                / convert(that.mass to mass),
                       unit = substance to mass)

    operator fun minus(that: MolalityAmount<M, N>) =
        MolalityAmount(amount = amount - that.amount,
                       unit = substance to mass)

    // endregion

    // region -> Scalar

    @JvmName("divMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolalityAmount<M, N>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.substance to substance)
                              * convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: MolalityAmount<M, N>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesMolarMass")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> times(that: MolarMassAmount<M, N>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.mass to mass)
                              / convert(that.substance to substance),
                     unit = NUMERIC)

    operator fun times(that: MolarMassAmount<M, N>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Molarity

    @JvmName("timesMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        MolarityAmount(amount = amount * that.amount
                                * convert(that.mass to mass),
                       unit = substance to that.distance)

    operator fun <L : Unit<Distance>> times(that: MassDensityAmount<L, M>) =
        MolarityAmount(amount = amount * that.amount,
                       unit = substance to that.distance)

    @JvmName("divSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        MolarityAmount(amount = amount / that.amount
                                * convert(that.mass to mass),
                       unit = substance to that.distance)

    operator fun <L : Unit<Distance>> div(that: SpecificVolumeAmount<L, M>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = substance to that.distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> times(that: MolarEnergyAmount<L, M, N, T>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.mass to mass)
                                      / convert(that.substance to substance),
                             unit = that.distance to that.time)

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: MolarEnergyAmount<L, M, N, T>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = that.distance to that.time)

    // endregion

    // region -> SpecificHeatCapacity

    @JvmName("timesMolarHeatCapacity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, K : Unit<Temperature>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<L, M, N, K, T>) =
        SpecificHeatCapacityAmount(amount = amount * that.amount
                                            * convert(that.mass to mass)
                                            / convert(that.substance to substance),
                                   unit = that.distance to U2(that.temperature, that.time))

    operator fun <L : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<L, M, N, K, T>) =
        SpecificHeatCapacityAmount(amount = amount * that.amount,
                                   unit = that.distance to U2(that.temperature, that.time))

    // endregion

    // region -> SpecificVolume

    @JvmName("timesMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        SpecificVolumeAmount(amount = amount * that.amount
                                      / convert(that.substance to substance),
                             unit = that.distance to mass)

    operator fun <L : Unit<Distance>> times(that: MolarVolumeAmount<L, N>) =
        SpecificVolumeAmount(amount = amount * that.amount,
                             unit = that.distance to mass)

    @JvmName("divMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      / convert(that.substance to substance),
                             unit = that.distance to mass)

    operator fun <L : Unit<Distance>> div(that: MolarityAmount<L, N>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = that.distance to mass)

    // endregion

    // region -> Substance

    @JvmName("timesMass")
    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        SubstanceAmount(amount = amount * that.amount
                                 * convert(that.mass to mass),
                        unit = substance)

    operator fun times(that: MassAmount<M>) =
        SubstanceAmount(amount = amount * that.amount,
                        unit = substance)

    // endregion
}
