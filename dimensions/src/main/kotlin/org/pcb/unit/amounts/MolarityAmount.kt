package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Mole.MOLE
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.AmountOfSubstance
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Molarity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MolarityAmount<N : Unit<AmountOfSubstance>, D : Unit<Distance>>(
        amount: Number,
        unit: Pair<N, D>)
    : Amount<Molarity>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Molarity::class,
                           units = mapOf(unit.major to 1,
                                         unit.minor to -3)))
{
    val amountOfSubstance = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Molarity>) =
            MolarityAmount(amount = amount * unit.scale,
                           unit = MOLE to METER)
    }

    // region -> Molarity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MolarityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                       unit = amountOfSubstance to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolarityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = amountOfSubstance to distance)

    @JvmName("plusMolarity")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> plus(that: MolarityAmount<N, D>) =
        MolarityAmount(amount = amount + that.amount
                                * convert(that.amountOfSubstance to amountOfSubstance)
                                / convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = amountOfSubstance to distance)

    operator fun plus(that: MolarityAmount<N, D>) =
        MolarityAmount(amount = amount + that.amount,
                       unit = amountOfSubstance to distance)

    @JvmName("minusMolarity")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> minus(that: MolarityAmount<N, D>) =
        MolarityAmount(amount = amount - that.amount
                                * convert(that.amountOfSubstance to amountOfSubstance)
                                / convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = amountOfSubstance to distance)

    operator fun minus(that: MolarityAmount<N, D>) =
        MolarityAmount(amount = amount - that.amount,
                       unit = amountOfSubstance to distance)

    // endregion

    // region -> Scalar

    @JvmName("timesMolarVolume")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> times(that: MolarVolumeAmount<N, D>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              / convert(that.amountOfSubstance to amountOfSubstance),
                     unit = NUMERIC)

    operator fun times(that: MolarVolumeAmount<N, D>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divMolarity")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> div(that: MolarityAmount<N, D>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.amountOfSubstance to amountOfSubstance)
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: MolarityAmount<N, D>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AmountOfSubstance

    @JvmName("timesVolume")
    operator fun <D : Unit<Distance>> times(that: VolumeAmount<D>) =
        AmountOfSubstanceAmount(amount = amount * that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance,
                                                   that.distance to distance),
                                unit = amountOfSubstance)

    operator fun times(that: VolumeAmount<D>) =
        AmountOfSubstanceAmount(amount = amount * that.amount,
                                unit = amountOfSubstance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesMolarEnergy")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MolarEnergyAmount<N, D, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance,
                                               that.distance to distance)
                                     / convert(that.amountOfSubstance to amountOfSubstance),
                            unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MolarEnergyAmount<N, D, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> Frequency

    @JvmName("timesCatalyticEfficiency")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>, T : Unit<Time>> times(that: CatalyticEfficiencyAmount<N, D, T>) =
        FrequencyAmount(amount = amount * that.amount
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance)
                                 / convert(that.amountOfSubstance to amountOfSubstance),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> times(that: CatalyticEfficiencyAmount<N, D, T>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> MassDensity

    @JvmName("divMolality")
    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> div(that: MolalityAmount<N, M>) =
        MassDensityAmount(amount = amount / that.amount
                                   / convert(that.amountOfSubstance to amountOfSubstance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> div(that: MolalityAmount<N, M>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = that.mass to distance)

    @JvmName("timesMolarMass")
    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> times(that: MolarMassAmount<N, M>) =
        MassDensityAmount(amount = amount * that.amount
                                   / convert(that.amountOfSubstance to amountOfSubstance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> times(that: MolarMassAmount<N, M>) =
        MassDensityAmount(amount = amount * that.amount,
                          unit = that.mass to distance)

    // endregion

    // region -> Molality

    @JvmName("divMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<D, M>) =
        MolalityAmount(amount = amount / that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = amountOfSubstance to that.mass)

    operator fun <M : Unit<Mass>> div(that: MassDensityAmount<D, M>) =
        MolalityAmount(amount = amount / that.amount,
                       unit = amountOfSubstance to that.mass)

    @JvmName("timesSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<D, M>) =
        MolalityAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = amountOfSubstance to that.mass)

    operator fun <M : Unit<Mass>> times(that: SpecificVolumeAmount<D, M>) =
        MolalityAmount(amount = amount * that.amount,
                       unit = amountOfSubstance to that.mass)

    // endregion
}
