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
import org.pcb.unit.dimensions.MolarVolume
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MolarVolumeAmount<N : Unit<AmountOfSubstance>, D : Unit<Distance>>(
        amount: Number,
        unit: Pair<D, N>)
    : Amount<MolarVolume>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = MolarVolume::class,
                              units = mapOf(unit.major to 3,
                                            unit.minor to -1)))
{
    val distance = unit.major
    val amountOfSubstance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MolarVolume>) =
            MolarVolumeAmount(amount = amount * unit.scale,
                              unit = METER to MOLE)
    }

    // region -> MolarVolume

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MolarVolumeAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = distance to amountOfSubstance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolarVolumeAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = distance to amountOfSubstance)

    @JvmName("plusMolarVolume")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> plus(that: MolarVolumeAmount<N, D>) =
        MolarVolumeAmount(amount = amount + that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance)
                                   / convert(that.amountOfSubstance to amountOfSubstance),
                          unit = distance to amountOfSubstance)

    operator fun plus(that: MolarVolumeAmount<N, D>) =
        MolarVolumeAmount(amount = amount + that.amount,
                          unit = distance to amountOfSubstance)

    @JvmName("minusMolarVolume")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> minus(that: MolarVolumeAmount<N, D>) =
        MolarVolumeAmount(amount = amount - that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance)
                                   / convert(that.amountOfSubstance to amountOfSubstance),
                          unit = distance to amountOfSubstance)

    operator fun minus(that: MolarVolumeAmount<N, D>) =
        MolarVolumeAmount(amount = amount - that.amount,
                          unit = distance to amountOfSubstance)

    // endregion

    // region -> Scalar

    @JvmName("divMolarVolume")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> div(that: MolarVolumeAmount<N, D>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              * convert(that.amountOfSubstance to amountOfSubstance),
                     unit = NUMERIC)

    operator fun div(that: MolarVolumeAmount<N, D>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesMolarity")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> times(that: MolarityAmount<N, D>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.amountOfSubstance to amountOfSubstance)
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: MolarityAmount<N, D>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> CatalyticEfficiency

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        CatalyticEfficiencyAmount(amount = amount * that.amount,
                                  unit = distance to U2(amountOfSubstance, that.time))

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        CatalyticEfficiencyAmount(amount = amount / that.amount,
                                  unit = distance to U2(amountOfSubstance, that.time))

    // endregion

    // region -> MolarEnergy

    @JvmName("timesEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        MolarEnergyAmount(amount = amount * that.amount
                                   / convert(that.distance to distance),
                          unit = U2(distance, that.mass) to U2(amountOfSubstance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(distance, that.mass) to U2(amountOfSubstance, that.time))

    // endregion

    // region -> MolarMass

    @JvmName("timesMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<D, M>) =
        MolarMassAmount(amount = amount * that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = that.mass to amountOfSubstance)

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<D, M>) =
        MolarMassAmount(amount = amount * that.amount,
                        unit = that.mass to amountOfSubstance)

    @JvmName("divSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        MolarMassAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = that.mass to amountOfSubstance)

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        MolarMassAmount(amount = amount / that.amount,
                        unit = that.mass to amountOfSubstance)

    // endregion

    // region -> SpecificVolume

    @JvmName("timesMolality")
    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> times(that: MolalityAmount<N, M>) =
        SpecificVolumeAmount(amount = amount * that.amount
                                      * convert(that.amountOfSubstance to amountOfSubstance),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> times(that: MolalityAmount<N, M>) =
        SpecificVolumeAmount(amount = amount * that.amount,
                             unit = distance to that.mass)

    @JvmName("divMolarMass")
    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> div(that: MolarMassAmount<N, M>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      * convert(that.amountOfSubstance to amountOfSubstance),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> div(that: MolarMassAmount<N, M>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = distance to that.mass)

    // endregion

    // region -> Time

    @JvmName("divCatalyticEfficiency")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>, T : Unit<Time>> div(that: CatalyticEfficiencyAmount<N, D, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance)
                            * convert(that.amountOfSubstance to amountOfSubstance),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: CatalyticEfficiencyAmount<N, D, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> Volume

    @JvmName("timesAmountOfSubstance")
    operator fun <N : Unit<AmountOfSubstance>> times(that: AmountOfSubstanceAmount<N>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.amountOfSubstance to amountOfSubstance),
                     unit = distance)

    operator fun times(that: AmountOfSubstanceAmount<N>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion
}
