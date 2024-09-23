package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Mole.MOLE
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Molarity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MolarityAmount<L : Unit<Distance>, N : Unit<Substance>>(
        amount: Number,
        unit: Pair<N, L>)
    : Amount<Molarity>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Molarity::class,
                           units = mapOf(unit.major to 1,
                                         unit.minor to -3)))
{
    val substance = unit.major
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
                       unit = substance to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolarityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = substance to distance)

    @JvmName("plusMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> plus(that: MolarityAmount<L, N>) =
        MolarityAmount(amount = amount + that.amount
                                * convert(that.substance to substance)
                                / convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = substance to distance)

    operator fun plus(that: MolarityAmount<L, N>) =
        MolarityAmount(amount = amount + that.amount,
                       unit = substance to distance)

    @JvmName("minusMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> minus(that: MolarityAmount<L, N>) =
        MolarityAmount(amount = amount - that.amount
                                * convert(that.substance to substance)
                                / convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = substance to distance)

    operator fun minus(that: MolarityAmount<L, N>) =
        MolarityAmount(amount = amount - that.amount,
                       unit = substance to distance)

    // endregion

    // region -> Scalar

    @JvmName("timesMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              / convert(that.substance to substance),
                     unit = NUMERIC)

    operator fun times(that: MolarVolumeAmount<L, N>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.substance to substance)
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: MolarityAmount<L, N>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> times(that: MolarEnergyAmount<L, M, N, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance,
                                               that.distance to distance)
                                     / convert(that.substance to substance),
                            unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MolarEnergyAmount<L, M, N, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> Frequency

    @JvmName("timesCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        FrequencyAmount(amount = amount * that.amount
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance)
                                 / convert(that.substance to substance),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> MassDensity

    @JvmName("divMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolalityAmount<M, N>) =
        MassDensityAmount(amount = amount / that.amount
                                   / convert(that.substance to substance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> div(that: MolalityAmount<M, N>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = that.mass to distance)

    @JvmName("timesMolarMass")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> times(that: MolarMassAmount<M, N>) =
        MassDensityAmount(amount = amount * that.amount
                                   / convert(that.substance to substance),
                          unit = that.mass to distance)

    operator fun <M : Unit<Mass>> times(that: MolarMassAmount<M, N>) =
        MassDensityAmount(amount = amount * that.amount,
                          unit = that.mass to distance)

    // endregion

    // region -> Molality

    @JvmName("divMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<L, M>) =
        MolalityAmount(amount = amount / that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = substance to that.mass)

    operator fun <M : Unit<Mass>> div(that: MassDensityAmount<L, M>) =
        MolalityAmount(amount = amount / that.amount,
                       unit = substance to that.mass)

    @JvmName("timesSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<L, M>) =
        MolalityAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = substance to that.mass)

    operator fun <M : Unit<Mass>> times(that: SpecificVolumeAmount<L, M>) =
        MolalityAmount(amount = amount * that.amount,
                       unit = substance to that.mass)

    // endregion

    // region -> Substance

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        SubstanceAmount(amount = amount * that.amount
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = substance)

    operator fun times(that: VolumeAmount<L>) =
        SubstanceAmount(amount = amount * that.amount,
                        unit = substance)

    // endregion
}
