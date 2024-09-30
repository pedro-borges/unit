package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.base.Mole.MOLE
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.ReciprocalSubstance
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ReciprocalSubstanceAmount<N : Unit<Substance>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, N>)
    : Amount<ReciprocalSubstance>(amount = amount.toBigDecimal(),
                                  unit = CompositeUnit(
                                      domain = ReciprocalSubstance::class,
                                      units = mapOf(unit.minor to -1)))
{
    val substance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ReciprocalSubstance>) =
            ReciprocalSubstanceAmount(amount = amount * unit.scale,
                                      unit = NUMERIC to MOLE)
    }

    // region -> ReciprocalSubstance

    override operator fun times(that: Number) =
        ReciprocalSubstanceAmount(amount = amount * that.toBigDecimal(),
                                  unit = NUMERIC to substance)

    override operator fun div(that: Number) =
        ReciprocalSubstanceAmount(amount = amount / that.toBigDecimal(),
                                  unit = NUMERIC to substance)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ReciprocalSubstanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = NUMERIC to substance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ReciprocalSubstanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = NUMERIC to substance)

    @JvmName("plusReciprocalSubstance")
    operator fun <N : Unit<Substance>> plus(that: ReciprocalSubstanceAmount<N>) =
        ReciprocalSubstanceAmount(amount = amount + that.amount
                                           / convert(that.substance to substance),
                                  unit = NUMERIC to substance)

    operator fun plus(that: ReciprocalSubstanceAmount<N>) =
        ReciprocalSubstanceAmount(amount = amount + that.amount,
                                  unit = NUMERIC to substance)

    @JvmName("minusReciprocalSubstance")
    operator fun <N : Unit<Substance>> minus(that: ReciprocalSubstanceAmount<N>) =
        ReciprocalSubstanceAmount(amount = amount - that.amount
                                           / convert(that.substance to substance),
                                  unit = NUMERIC to substance)

    operator fun minus(that: ReciprocalSubstanceAmount<N>) =
        ReciprocalSubstanceAmount(amount = amount - that.amount,
                                  unit = NUMERIC to substance)

    // endregion

    // region -> Scalar

    @JvmName("divReciprocalSubstance")
    operator fun <N : Unit<Substance>> div(that: ReciprocalSubstanceAmount<N>) =
        ScalarAmount(amount = amount / that.amount
                              * convert(that.substance to substance),
                     unit = NUMERIC)

    operator fun div(that: ReciprocalSubstanceAmount<N>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesSubstance")
    operator fun <N : Unit<Substance>> times(that: SubstanceAmount<N>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.substance to substance),
                     unit = NUMERIC)

    operator fun times(that: SubstanceAmount<N>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> CatalyticEfficiency

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        CatalyticEfficiencyAmount(amount = amount * that.amount,
                                  unit = that.distance to U2(substance, that.time))

    // endregion

    // region -> MolarEnergy

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<L, M, T>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(that.distance, that.mass) to U2(substance, that.time))

    // endregion

    // region -> MolarHeatCapacity

    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: HeatCapacityAmount<L, M, K, T>) =
        MolarHeatCapacityAmount(amount = amount * that.amount,
                                unit = U2(that.distance, that.mass) to U3(substance, that.temperature, that.time))

    // endregion

    // region -> MolarMass

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        MolarMassAmount(amount = amount * that.amount,
                        unit = that.mass to substance)

    // endregion

    // region -> MolarVolume

    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        MolarVolumeAmount(amount = amount * that.amount,
                          unit = that.distance to substance)

    // endregion
}
