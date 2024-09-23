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
import org.pcb.unit.dimensions.MolarVolume
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MolarVolumeAmount<L : Unit<Distance>, N : Unit<Substance>>(
        amount: Number,
        unit: Pair<L, N>)
    : Amount<MolarVolume>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = MolarVolume::class,
                              units = mapOf(unit.major to 3,
                                            unit.minor to -1)))
{
    val distance = unit.major
    val substance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MolarVolume>) =
            MolarVolumeAmount(amount = amount * unit.scale,
                              unit = METER to MOLE)
    }

    // region -> MolarVolume

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MolarVolumeAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = distance to substance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolarVolumeAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = distance to substance)

    @JvmName("plusMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> plus(that: MolarVolumeAmount<L, N>) =
        MolarVolumeAmount(amount = amount + that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance)
                                   / convert(that.substance to substance),
                          unit = distance to substance)

    operator fun plus(that: MolarVolumeAmount<L, N>) =
        MolarVolumeAmount(amount = amount + that.amount,
                          unit = distance to substance)

    @JvmName("minusMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> minus(that: MolarVolumeAmount<L, N>) =
        MolarVolumeAmount(amount = amount - that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance)
                                   / convert(that.substance to substance),
                          unit = distance to substance)

    operator fun minus(that: MolarVolumeAmount<L, N>) =
        MolarVolumeAmount(amount = amount - that.amount,
                          unit = distance to substance)

    // endregion

    // region -> Scalar

    @JvmName("divMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarVolumeAmount<L, N>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              * convert(that.substance to substance),
                     unit = NUMERIC)

    operator fun div(that: MolarVolumeAmount<L, N>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarityAmount<L, N>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.substance to substance)
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: MolarityAmount<L, N>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> CatalyticEfficiency

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        CatalyticEfficiencyAmount(amount = amount * that.amount,
                                  unit = distance to U2(substance, that.time))

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        CatalyticEfficiencyAmount(amount = amount / that.amount,
                                  unit = distance to U2(substance, that.time))

    // endregion

    // region -> MolarEnergy

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        MolarEnergyAmount(amount = amount * that.amount
                                   / convert(that.distance to distance),
                          unit = U2(distance, that.mass) to U2(substance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(distance, that.mass) to U2(substance, that.time))

    // endregion

    // region -> MolarMass

    @JvmName("timesMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        MolarMassAmount(amount = amount * that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = that.mass to substance)

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        MolarMassAmount(amount = amount * that.amount,
                        unit = that.mass to substance)

    @JvmName("divSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        MolarMassAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = that.mass to substance)

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        MolarMassAmount(amount = amount / that.amount,
                        unit = that.mass to substance)

    // endregion

    // region -> SpecificVolume

    @JvmName("timesMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> times(that: MolalityAmount<M, N>) =
        SpecificVolumeAmount(amount = amount * that.amount
                                      * convert(that.substance to substance),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> times(that: MolalityAmount<M, N>) =
        SpecificVolumeAmount(amount = amount * that.amount,
                             unit = distance to that.mass)

    @JvmName("divMolarMass")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolarMassAmount<M, N>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      * convert(that.substance to substance),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> div(that: MolarMassAmount<M, N>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = distance to that.mass)

    // endregion

    // region -> Time

    @JvmName("divCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> div(that: CatalyticEfficiencyAmount<L, N, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance)
                            * convert(that.substance to substance),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: CatalyticEfficiencyAmount<L, N, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> Volume

    @JvmName("timesSubstance")
    operator fun <N : Unit<Substance>> times(that: SubstanceAmount<N>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.substance to substance),
                     unit = distance)

    operator fun times(that: SubstanceAmount<N>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion
}
