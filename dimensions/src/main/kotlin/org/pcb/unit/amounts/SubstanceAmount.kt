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

open class SubstanceAmount<N : Unit<Substance>>(
        amount: Number,
        unit: N)
    : Amount<Substance>(amount = amount.toBigDecimal(),
                        unit = CompositeUnit(
                            domain = Substance::class,
                            units = mapOf(unit to 1)))
{
    val substance = unit

    // region -> Substance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SubstanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                        unit = substance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SubstanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                        unit = substance)

    @JvmName("plusSubstance")
    operator fun <N : Unit<Substance>> plus(that: SubstanceAmount<N>) =
        SubstanceAmount(amount = amount + that.amount
                                 * convert(that.substance to substance),
                        unit = substance)

    operator fun plus(that: SubstanceAmount<N>) =
        SubstanceAmount(amount = amount + that.amount,
                        unit = substance)

    @JvmName("minusSubstance")
    operator fun <N : Unit<Substance>> minus(that: SubstanceAmount<N>) =
        SubstanceAmount(amount = amount - that.amount
                                 * convert(that.substance to substance),
                        unit = substance)

    operator fun minus(that: SubstanceAmount<N>) =
        SubstanceAmount(amount = amount - that.amount,
                        unit = substance)

    // endregion

    // region -> Scalar

    @JvmName("divSubstance")
    operator fun <N : Unit<Substance>> div(that: SubstanceAmount<N>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.substance to substance),
                     unit = NUMERIC)

    operator fun div(that: SubstanceAmount<N>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Energy

    @JvmName("timesMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> times(that: MolarEnergyAmount<L, M, N, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.substance to substance),
                     unit = U2(that.distance, that.mass) to that.time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MolarEnergyAmount<L, M, N, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> HeatCapacity

    @JvmName("timesMolarHeatCapacity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, K : Unit<Temperature>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<L, M, N, K, T>) =
        HeatCapacityAmount(amount = amount * that.amount
                                    / convert(that.substance to substance),
                           unit = U2(that.distance, that.mass) to U2(that.temperature, that.time))

    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<L, M, N, K, T>) =
        HeatCapacityAmount(amount = amount * that.amount,
                           unit = U2(that.distance, that.mass) to U2(that.temperature, that.time))

    // endregion

    // region -> Mass

    @JvmName("divMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolalityAmount<M, N>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.substance to substance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> div(that: MolalityAmount<M, N>) =
        MassAmount(amount = amount / that.amount,
                   unit = that.mass)

    @JvmName("timesMolarMass")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> times(that: MolarMassAmount<M, N>) =
        MassAmount(amount = amount * that.amount
                            / convert(that.substance to substance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: MolarMassAmount<M, N>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> Molality

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        MolalityAmount(amount = amount / that.amount,
                       unit = substance to that.mass)

    // endregion

    // region -> Molarity

    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = substance to that.distance)

    // endregion

    // region -> Volume

    @JvmName("timesMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        VolumeAmount(amount = amount * that.amount
                              / convert(that.substance to substance),
                     unit = that.distance)

    operator fun <L : Unit<Distance>> times(that: MolarVolumeAmount<L, N>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = that.distance)

    @JvmName("divMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.substance to substance),
                     unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: MolarityAmount<L, N>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = that.distance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("timesCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      / convert(that.substance to substance),
                             unit = that.distance to that.time)

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = that.distance to that.time)

    // endregion
}
