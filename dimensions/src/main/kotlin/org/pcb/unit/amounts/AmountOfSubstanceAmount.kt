package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.AmountOfSubstance
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AmountOfSubstanceAmount<N : Unit<AmountOfSubstance>>(
        amount: Number,
        unit: N)
    : Amount<AmountOfSubstance>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = AmountOfSubstance::class,
                                    units = mapOf(unit to 1)))
{
    val amountOfSubstance = unit

    // region -> AmountOfSubstance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AmountOfSubstanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                unit = amountOfSubstance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AmountOfSubstanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                unit = amountOfSubstance)

    @JvmName("plusAmountOfSubstance")
    operator fun <N : Unit<AmountOfSubstance>> plus(that: AmountOfSubstanceAmount<N>) =
        AmountOfSubstanceAmount(amount = amount + that.amount
                                         * convert(that.amountOfSubstance to amountOfSubstance),
                                unit = amountOfSubstance)

    operator fun plus(that: AmountOfSubstanceAmount<N>) =
        AmountOfSubstanceAmount(amount = amount + that.amount,
                                unit = amountOfSubstance)

    @JvmName("minusAmountOfSubstance")
    operator fun <N : Unit<AmountOfSubstance>> minus(that: AmountOfSubstanceAmount<N>) =
        AmountOfSubstanceAmount(amount = amount - that.amount
                                         * convert(that.amountOfSubstance to amountOfSubstance),
                                unit = amountOfSubstance)

    operator fun minus(that: AmountOfSubstanceAmount<N>) =
        AmountOfSubstanceAmount(amount = amount - that.amount,
                                unit = amountOfSubstance)

    // endregion

    // region -> Scalar

    @JvmName("divAmountOfSubstance")
    operator fun <N : Unit<AmountOfSubstance>> div(that: AmountOfSubstanceAmount<N>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.amountOfSubstance to amountOfSubstance),
                     unit = NUMERIC)

    operator fun div(that: AmountOfSubstanceAmount<N>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Energy

    @JvmName("timesMolarEnergy")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MolarEnergyAmount<N, D, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.amountOfSubstance to amountOfSubstance),
                     unit = U2(that.distance, that.mass) to that.time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MolarEnergyAmount<N, D, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> HeatCapacity

    @JvmName("timesMolarHeatCapacity")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        HeatCapacityAmount(amount = amount * that.amount
                                    / convert(that.amountOfSubstance to amountOfSubstance),
                           unit = U2(that.distance, that.mass) to U2(that.temperature, that.time))

    operator fun <D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: MolarHeatCapacityAmount<N, D, M, K, T>) =
        HeatCapacityAmount(amount = amount * that.amount,
                           unit = U2(that.distance, that.mass) to U2(that.temperature, that.time))

    // endregion

    // region -> Mass

    @JvmName("divMolality")
    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> div(that: MolalityAmount<N, M>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.amountOfSubstance to amountOfSubstance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> div(that: MolalityAmount<N, M>) =
        MassAmount(amount = amount / that.amount,
                   unit = that.mass)

    @JvmName("timesMolarMass")
    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> times(that: MolarMassAmount<N, M>) =
        MassAmount(amount = amount * that.amount
                            / convert(that.amountOfSubstance to amountOfSubstance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: MolarMassAmount<N, M>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> Molality

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        MolalityAmount(amount = amount / that.amount,
                       unit = amountOfSubstance to that.mass)

    // endregion

    // region -> Molarity

    operator fun <D : Unit<Distance>> div(that: VolumeAmount<D>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = amountOfSubstance to that.distance)

    // endregion

    // region -> Volume

    @JvmName("timesMolarVolume")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> times(that: MolarVolumeAmount<N, D>) =
        VolumeAmount(amount = amount * that.amount
                              / convert(that.amountOfSubstance to amountOfSubstance),
                     unit = that.distance)

    operator fun <D : Unit<Distance>> times(that: MolarVolumeAmount<N, D>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = that.distance)

    @JvmName("divMolarity")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> div(that: MolarityAmount<N, D>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.amountOfSubstance to amountOfSubstance),
                     unit = that.distance)

    operator fun <D : Unit<Distance>> div(that: MolarityAmount<N, D>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = that.distance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("timesCatalyticEfficiency")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>, T : Unit<Time>> times(that: CatalyticEfficiencyAmount<N, D, T>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      / convert(that.amountOfSubstance to amountOfSubstance),
                             unit = that.distance to that.time)

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: CatalyticEfficiencyAmount<N, D, T>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = that.distance to that.time)

    // endregion
}
