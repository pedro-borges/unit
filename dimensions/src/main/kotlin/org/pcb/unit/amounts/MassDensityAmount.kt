package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MassDensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MassDensityAmount<L : Unit<Distance>, M : Unit<Mass>>(
        amount: Number,
        unit: Pair<M, L>)
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

    override operator fun times(that: Number) =
        MassDensityAmount(amount = amount * that.toBigDecimal(),
                          unit = mass to distance)

    override operator fun div(that: Number) =
        MassDensityAmount(amount = amount / that.toBigDecimal(),
                          unit = mass to distance)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = mass to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = mass to distance)

    @JvmName("plusMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> plus(that: MassDensityAmount<L, M>) =
        MassDensityAmount(amount = amount + that.amount
                                   * convert(that.mass to mass)
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun plus(that: MassDensityAmount<L, M>) =
        MassDensityAmount(amount = amount + that.amount,
                          unit = mass to distance)

    @JvmName("minusMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> minus(that: MassDensityAmount<L, M>) =
        MassDensityAmount(amount = amount - that.amount
                                   * convert(that.mass to mass)
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun minus(that: MassDensityAmount<L, M>) =
        MassDensityAmount(amount = amount - that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Scalar

    @JvmName("divMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: MassDensityAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<L, M>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              / convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun times(that: SpecificVolumeAmount<L, M>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AreaDensity

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        AreaDensityAmount(amount = amount * that.amount
                                   * convert(that.distance to distance),
                          unit = mass to distance)

    operator fun times(that: DistanceAmount<L>) =
        AreaDensityAmount(amount = amount * that.amount,
                          unit = mass to distance)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        AreaDensityAmount(amount = amount / that.amount
                                   * convert(that.distance to distance),
                          unit = mass to distance)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance,
                                               that.distance to distance),
                            unit = mass to U2(distance, that.time))

    operator fun <T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(distance, that.time))

    // endregion

    // region -> EnergyDensityGradient

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        EnergyDensityGradientAmount(amount = amount * that.amount
                                             * convert(that.distance to distance),
                                    unit = mass to U2(distance, that.time))

    operator fun <T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        EnergyDensityGradientAmount(amount = amount * that.amount,
                                    unit = mass to U2(distance, that.time))

    // endregion

    // region -> FuelEfficiency

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance),
                             unit = NUMERIC to distance)

    operator fun div(that: LinearDensityAmount<L, M>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> LinearDensity

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        LinearDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance,
                                               that.distance to distance),
                            unit = mass to distance)

    operator fun times(that: AreaAmount<L>) =
        LinearDensityAmount(amount = amount * that.amount,
                            unit = mass to distance)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        LinearDensityAmount(amount = amount / that.amount
                                     * convert(that.distance to distance,
                                               that.distance to distance),
                            unit = mass to distance)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to distance)

    // endregion

    // region -> Mass

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = mass)

    operator fun times(that: VolumeAmount<L>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance),
                           unit = mass to that.time)

    operator fun <T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to that.time)

    // endregion

    // region -> MolarMass

    @JvmName("timesMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        MolarMassAmount(amount = amount * that.amount
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = mass to that.substance)

    operator fun <N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        MolarMassAmount(amount = amount * that.amount,
                        unit = mass to that.substance)

    @JvmName("divMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        MolarMassAmount(amount = amount / that.amount
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = mass to that.substance)

    operator fun <N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        MolarMassAmount(amount = amount / that.amount,
                        unit = mass to that.substance)

    // endregion

    // region -> Molarity

    @JvmName("timesMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> times(that: MolalityAmount<M, N>) =
        MolarityAmount(amount = amount * that.amount
                                / convert(that.mass to mass),
                       unit = that.substance to distance)

    operator fun <N : Unit<Substance>> times(that: MolalityAmount<M, N>) =
        MolarityAmount(amount = amount * that.amount,
                       unit = that.substance to distance)

    @JvmName("divMolarMass")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolarMassAmount<M, N>) =
        MolarityAmount(amount = amount / that.amount
                                / convert(that.mass to mass),
                       unit = that.substance to distance)

    operator fun <N : Unit<Substance>> div(that: MolarMassAmount<M, N>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = that.substance to distance)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.distance to distance,
                                                  that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun div(that: AreaDensityAmount<L, M>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion
}
