package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.AmountOfSubstance
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MassDensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MassDensityAmount<D : Unit<Distance>, M : Unit<Mass>>(
        amount: Number,
        unit: Pair<M, D>)
    : Amount<MassDensity>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = MassDensity::class,
                              units = mapOf(unit.major to 1,
                                            unit.minor to -3)))
{
    val mass = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MassDensity>) =
            MassDensityAmount(amount = amount * unit.scale,
                              unit = kilo(GRAM) to METER)
    }

    // region -> MassDensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = mass to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = mass to distance)

    @JvmName("plusMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> plus(that: MassDensityAmount<D, M>) =
        MassDensityAmount(amount = amount + that.amount
                                   * convert(that.mass to mass)
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun plus(that: MassDensityAmount<D, M>) =
        MassDensityAmount(amount = amount + that.amount,
                          unit = mass to distance)

    @JvmName("minusMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> minus(that: MassDensityAmount<D, M>) =
        MassDensityAmount(amount = amount - that.amount
                                   * convert(that.mass to mass)
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun minus(that: MassDensityAmount<D, M>) =
        MassDensityAmount(amount = amount - that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Scalar

    @JvmName("divMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<D, M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: MassDensityAmount<D, M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<D, M>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              / convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun times(that: SpecificVolumeAmount<D, M>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AreaDensity

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        AreaDensityAmount(amount = amount * that.amount
                                   * convert(that.distance to distance),
                          unit = mass to distance)

    operator fun times(that: DistanceAmount<D>) =
        AreaDensityAmount(amount = amount * that.amount,
                          unit = mass to distance)

    @JvmName("divSpacialFrequency")
    operator fun <D : Unit<Distance>> div(that: SpacialFrequencyAmount<D>) =
        AreaDensityAmount(amount = amount / that.amount
                                   * convert(that.distance to distance),
                          unit = mass to distance)

    operator fun div(that: SpacialFrequencyAmount<D>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesSpecificEnergy")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<D, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance,
                                               that.distance to distance),
                            unit = mass to U2(distance, that.time))

    operator fun <T : Unit<Time>> times(that: SpecificEnergyAmount<D, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(distance, that.time))

    // endregion

    // region -> Mass

    @JvmName("timesVolume")
    operator fun <D : Unit<Distance>> times(that: VolumeAmount<D>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = mass)

    operator fun times(that: VolumeAmount<D>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<D, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance),
                           unit = mass to that.time)

    operator fun <T : Unit<Time>> times(that: VolumetricFlowAmount<D, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to that.time)

    // endregion

    // region -> MolarMass

    @JvmName("timesMolarVolume")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> times(that: MolarVolumeAmount<N, D>) =
        MolarMassAmount(amount = amount * that.amount
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = mass to that.amountOfSubstance)

    operator fun <N : Unit<AmountOfSubstance>> times(that: MolarVolumeAmount<N, D>) =
        MolarMassAmount(amount = amount * that.amount,
                        unit = mass to that.amountOfSubstance)

    @JvmName("divMolarity")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>> div(that: MolarityAmount<N, D>) =
        MolarMassAmount(amount = amount / that.amount
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = mass to that.amountOfSubstance)

    operator fun <N : Unit<AmountOfSubstance>> div(that: MolarityAmount<N, D>) =
        MolarMassAmount(amount = amount / that.amount,
                        unit = mass to that.amountOfSubstance)

    // endregion

    // region -> Molarity

    @JvmName("timesMolality")
    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> times(that: MolalityAmount<N, M>) =
        MolarityAmount(amount = amount * that.amount
                                / convert(that.mass to mass),
                       unit = that.amountOfSubstance to distance)

    operator fun <N : Unit<AmountOfSubstance>> times(that: MolalityAmount<N, M>) =
        MolarityAmount(amount = amount * that.amount,
                       unit = that.amountOfSubstance to distance)

    @JvmName("divMolarMass")
    operator fun <N : Unit<AmountOfSubstance>, M : Unit<Mass>> div(that: MolarMassAmount<N, M>) =
        MolarityAmount(amount = amount / that.amount
                                / convert(that.mass to mass),
                       unit = that.amountOfSubstance to distance)

    operator fun <N : Unit<AmountOfSubstance>> div(that: MolarMassAmount<N, M>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = that.amountOfSubstance to distance)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<D, M>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.distance to distance,
                                                  that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun div(that: AreaDensityAmount<D, M>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion
}
