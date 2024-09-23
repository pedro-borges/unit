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

open class MolalityAmount<N : Unit<Substance>, M : Unit<Mass>>(
        amount: Number,
        unit: Pair<N, M>)
    : Amount<Molality>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Molality::class,
                           units = mapOf(unit.major to 1,
                                         unit.minor to -1)))
{
    val amountOfSubstance = unit.major
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
                       unit = amountOfSubstance to mass)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolalityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = amountOfSubstance to mass)

    @JvmName("plusMolality")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> plus(that: MolalityAmount<N, M>) =
        MolalityAmount(amount = amount + that.amount
                                * convert(that.amountOfSubstance to amountOfSubstance)
                                / convert(that.mass to mass),
                       unit = amountOfSubstance to mass)

    operator fun plus(that: MolalityAmount<N, M>) =
        MolalityAmount(amount = amount + that.amount,
                       unit = amountOfSubstance to mass)

    @JvmName("minusMolality")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> minus(that: MolalityAmount<N, M>) =
        MolalityAmount(amount = amount - that.amount
                                * convert(that.amountOfSubstance to amountOfSubstance)
                                / convert(that.mass to mass),
                       unit = amountOfSubstance to mass)

    operator fun minus(that: MolalityAmount<N, M>) =
        MolalityAmount(amount = amount - that.amount,
                       unit = amountOfSubstance to mass)

    // endregion

    // region -> Scalar

    @JvmName("divMolality")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> div(that: MolalityAmount<N, M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.amountOfSubstance to amountOfSubstance)
                              * convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: MolalityAmount<N, M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesMolarMass")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> times(that: MolarMassAmount<N, M>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.mass to mass)
                              / convert(that.amountOfSubstance to amountOfSubstance),
                     unit = NUMERIC)

    operator fun times(that: MolarMassAmount<N, M>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AmountOfSubstance

    @JvmName("timesMass")
    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        SubstanceAmount(amount = amount * that.amount
                                 * convert(that.mass to mass),
                        unit = amountOfSubstance)

    operator fun times(that: MassAmount<M>) =
        SubstanceAmount(amount = amount * that.amount,
                        unit = amountOfSubstance)

    // endregion

    // region -> Molarity

    @JvmName("timesMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<D, M>) =
        MolarityAmount(amount = amount * that.amount
                                * convert(that.mass to mass),
                       unit = amountOfSubstance to that.distance)

    operator fun <D : Unit<Distance>> times(that: MassDensityAmount<D, M>) =
        MolarityAmount(amount = amount * that.amount,
                       unit = amountOfSubstance to that.distance)

    @JvmName("divSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        MolarityAmount(amount = amount / that.amount
                                * convert(that.mass to mass),
                       unit = amountOfSubstance to that.distance)

    operator fun <D : Unit<Distance>> div(that: SpecificVolumeAmount<D, M>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = amountOfSubstance to that.distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesMolarEnergy")
    operator fun <N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MolarEnergyAmount<N, D, M, T>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.mass to mass)
                                      / convert(that.amountOfSubstance to amountOfSubstance),
                             unit = that.distance to that.time)

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: MolarEnergyAmount<N, D, M, T>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = that.distance to that.time)

    // endregion

    // region -> SpecificHeatCapacity

    @JvmName("timesMolarHeatCapacity")
    operator fun <N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        SpecificHeatCapacityAmount(amount = amount * that.amount
                                            * convert(that.mass to mass)
                                            / convert(that.amountOfSubstance to amountOfSubstance),
                                   unit = that.distance to U2(that.temperature, that.time))

    operator fun <D : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        SpecificHeatCapacityAmount(amount = amount * that.amount,
                                   unit = that.distance to U2(that.temperature, that.time))

    // endregion

    // region -> SpecificVolume

    @JvmName("timesMolarVolume")
    operator fun <N : Unit<Substance>, D : Unit<Distance>> times(that: MolarVolumeAmount<N, D>) =
        SpecificVolumeAmount(amount = amount * that.amount
                                      / convert(that.amountOfSubstance to amountOfSubstance),
                             unit = that.distance to mass)

    operator fun <D : Unit<Distance>> times(that: MolarVolumeAmount<N, D>) =
        SpecificVolumeAmount(amount = amount * that.amount,
                             unit = that.distance to mass)

    @JvmName("divMolarity")
    operator fun <N : Unit<Substance>, D : Unit<Distance>> div(that: MolarityAmount<N, D>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      / convert(that.amountOfSubstance to amountOfSubstance),
                             unit = that.distance to mass)

    operator fun <D : Unit<Distance>> div(that: MolarityAmount<N, D>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = that.distance to mass)

    // endregion
}
