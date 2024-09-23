package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Mole.MOLE
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MolarMass
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

open class MolarMassAmount<N : Unit<Substance>, M : Unit<Mass>>(
        amount: Number,
        unit: Pair<M, N>)
    : Amount<MolarMass>(amount = amount.toBigDecimal(),
                        unit = CompositeUnit(
                            domain = MolarMass::class,
                            units = mapOf(unit.major to 1,
                                          unit.minor to -1)))
{
    val mass = unit.major
    val substance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MolarMass>) =
            MolarMassAmount(amount = amount * unit.scale,
                            unit = kilo(GRAM) to MOLE)
    }

    // region -> MolarMass

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MolarMassAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                        unit = mass to substance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolarMassAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                        unit = mass to substance)

    @JvmName("plusMolarMass")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> plus(that: MolarMassAmount<N, M>) =
        MolarMassAmount(amount = amount + that.amount
                                 * convert(that.mass to mass)
                                 / convert(that.substance to substance),
                        unit = mass to substance)

    operator fun plus(that: MolarMassAmount<N, M>) =
        MolarMassAmount(amount = amount + that.amount,
                        unit = mass to substance)

    @JvmName("minusMolarMass")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> minus(that: MolarMassAmount<N, M>) =
        MolarMassAmount(amount = amount - that.amount
                                 * convert(that.mass to mass)
                                 / convert(that.substance to substance),
                        unit = mass to substance)

    operator fun minus(that: MolarMassAmount<N, M>) =
        MolarMassAmount(amount = amount - that.amount,
                        unit = mass to substance)

    // endregion

    // region -> Scalar

    @JvmName("timesMolality")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> times(that: MolalityAmount<N, M>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.substance to substance)
                              / convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun times(that: MolalityAmount<N, M>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divMolarMass")
    operator fun <N : Unit<Substance>, M : Unit<Mass>> div(that: MolarMassAmount<N, M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.substance to substance),
                     unit = NUMERIC)

    operator fun div(that: MolarMassAmount<N, M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Mass

    @JvmName("timesSubstance")
    operator fun <N : Unit<Substance>> times(that: SubstanceAmount<N>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.substance to substance),
                   unit = mass)

    operator fun times(that: SubstanceAmount<N>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    // endregion

    // region -> MassDensity

    @JvmName("divMolarVolume")
    operator fun <N : Unit<Substance>, D : Unit<Distance>> div(that: MolarVolumeAmount<N, D>) =
        MassDensityAmount(amount = amount / that.amount
                                   * convert(that.substance to substance),
                          unit = mass to that.distance)

    operator fun <D : Unit<Distance>> div(that: MolarVolumeAmount<N, D>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = mass to that.distance)

    @JvmName("timesMolarity")
    operator fun <N : Unit<Substance>, D : Unit<Distance>> times(that: MolarityAmount<N, D>) =
        MassDensityAmount(amount = amount * that.amount
                                   * convert(that.substance to substance),
                          unit = mass to that.distance)

    operator fun <D : Unit<Distance>> times(that: MolarityAmount<N, D>) =
        MassDensityAmount(amount = amount * that.amount,
                          unit = mass to that.distance)

    // endregion

    // region -> MolarEnergy

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<D, T>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(that.distance, mass) to U2(substance, that.time))

    // endregion

    // region -> MolarHeatCapacity

    operator fun <D : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> times(that: SpecificHeatCapacityAmount<D, K, T>) =
        MolarHeatCapacityAmount(amount = amount * that.amount,
                                unit = U2(that.distance, mass) to U3(substance, that.temperature, that.time))

    // endregion

    // region -> MolarVolume

    @JvmName("divMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<D, M>) =
        MolarVolumeAmount(amount = amount / that.amount
                                   / convert(that.mass to mass),
                          unit = that.distance to substance)

    operator fun <D : Unit<Distance>> div(that: MassDensityAmount<D, M>) =
        MolarVolumeAmount(amount = amount / that.amount,
                          unit = that.distance to substance)

    @JvmName("timesSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<D, M>) =
        MolarVolumeAmount(amount = amount * that.amount
                                   / convert(that.mass to mass),
                          unit = that.distance to substance)

    operator fun <D : Unit<Distance>> times(that: SpecificVolumeAmount<D, M>) =
        MolarVolumeAmount(amount = amount * that.amount,
                          unit = that.distance to substance)

    // endregion
}
