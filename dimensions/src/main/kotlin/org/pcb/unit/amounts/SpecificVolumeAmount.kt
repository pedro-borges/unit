package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.SpecificVolume
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SpecificVolumeAmount<L : Unit<Distance>, M : Unit<Mass>>(
        amount: Number,
        unit: Pair<L, M>)
    : Amount<SpecificVolume>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = SpecificVolume::class,
                                 units = mapOf(unit.major to 3,
                                               unit.minor to -1)))
{
    val distance = unit.major
    val mass = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<SpecificVolume>) =
            SpecificVolumeAmount(amount = amount * unit.scale,
                                 unit = METER to kilo(GRAM))
    }

    // region -> SpecificVolume

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpecificVolumeAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                             unit = distance to mass)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpecificVolumeAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                             unit = distance to mass)

    @JvmName("plusSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> plus(that: SpecificVolumeAmount<L, M>) =
        SpecificVolumeAmount(amount = amount + that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.mass to mass),
                             unit = distance to mass)

    operator fun plus(that: SpecificVolumeAmount<L, M>) =
        SpecificVolumeAmount(amount = amount + that.amount,
                             unit = distance to mass)

    @JvmName("minusSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> minus(that: SpecificVolumeAmount<L, M>) =
        SpecificVolumeAmount(amount = amount - that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.mass to mass),
                             unit = distance to mass)

    operator fun minus(that: SpecificVolumeAmount<L, M>) =
        SpecificVolumeAmount(amount = amount - that.amount,
                             unit = distance to mass)

    // endregion

    // region -> Scalar

    @JvmName("timesMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.mass to mass)
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun times(that: MassDensityAmount<L, M>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              * convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: SpecificVolumeAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Distance

    @JvmName("timesAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        DistanceAmount(amount = amount * that.amount
                                * convert(that.mass to mass)
                                / convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun times(that: AreaDensityAmount<L, M>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> Molality

    @JvmName("divMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarVolumeAmount<L, N>) =
        MolalityAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = that.substance to mass)

    operator fun <N : Unit<Substance>> div(that: MolarVolumeAmount<L, N>) =
        MolalityAmount(amount = amount / that.amount,
                       unit = that.substance to mass)

    @JvmName("timesMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarityAmount<L, N>) =
        MolalityAmount(amount = amount * that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = that.substance to mass)

    operator fun <N : Unit<Substance>> times(that: MolarityAmount<L, N>) =
        MolalityAmount(amount = amount * that.amount,
                       unit = that.substance to mass)

    // endregion

    // region -> MolarVolume

    @JvmName("divMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolalityAmount<M, N>) =
        MolarVolumeAmount(amount = amount / that.amount
                                   * convert(that.mass to mass),
                          unit = distance to that.substance)

    operator fun <N : Unit<Substance>> div(that: MolalityAmount<M, N>) =
        MolarVolumeAmount(amount = amount / that.amount,
                          unit = distance to that.substance)

    @JvmName("timesMolarMass")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> times(that: MolarMassAmount<M, N>) =
        MolarVolumeAmount(amount = amount * that.amount
                                   * convert(that.mass to mass),
                          unit = distance to that.substance)

    operator fun <N : Unit<Substance>> times(that: MolarMassAmount<M, N>) =
        MolarVolumeAmount(amount = amount * that.amount,
                          unit = distance to that.substance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.mass to mass)
                                      / convert(that.distance to distance),
                             unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to that.time)

    // endregion

    // region -> Volume

    @JvmName("timesMass")
    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.mass to mass),
                     unit = distance)

    operator fun times(that: MassAmount<M>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("timesMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      * convert(that.mass to mass),
                             unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = distance to that.time)

    // endregion
}
