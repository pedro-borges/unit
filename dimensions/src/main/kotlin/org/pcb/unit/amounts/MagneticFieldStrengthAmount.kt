package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.MagneticFieldStrength
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MagneticFieldStrengthAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<I, L>)
    : Amount<MagneticFieldStrength>(amount = amount.toBigDecimal(),
                                    unit = CompositeUnit(
                                        domain = MagneticFieldStrength::class,
                                        units = mapOf(unit.major to 1,
                                                      unit.minor to -1)))
{
    val electricCurrent = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MagneticFieldStrength>) =
            MagneticFieldStrengthAmount(amount = amount * unit.scale,
                                        unit = AMPERE to METER)
    }

    // region -> MagneticFieldStrength

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                    unit = electricCurrent to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                    unit = electricCurrent to distance)

    @JvmName("plusMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> plus(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticFieldStrengthAmount(amount = amount + that.amount
                                             * convert(that.electricCurrent to electricCurrent)
                                             / convert(that.distance to distance),
                                    unit = electricCurrent to distance)

    operator fun plus(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticFieldStrengthAmount(amount = amount + that.amount,
                                    unit = electricCurrent to distance)

    @JvmName("minusMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> minus(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticFieldStrengthAmount(amount = amount - that.amount
                                             * convert(that.electricCurrent to electricCurrent)
                                             / convert(that.distance to distance),
                                    unit = electricCurrent to distance)

    operator fun minus(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticFieldStrengthAmount(amount = amount - that.amount,
                                    unit = electricCurrent to distance)

    // endregion

    // region -> Scalar

    @JvmName("divMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent)
                              * convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: MagneticFieldStrengthAmount<L, I>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Distance

    @JvmName("divElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: ElectricCurrentDensityAmount<L, I>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.electricCurrent to electricCurrent)
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun div(that: ElectricCurrentDensityAmount<L, I>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCharge

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      * convert(that.distance to distance),
                             unit = U2(electricCurrent, that.time))

    operator fun <T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, that.time))

    // endregion

    // region -> ElectricConductance

    @JvmName("divElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           / convert(that.distance to distance)
                                           * convert(that.electricCurrent to electricCurrent),
                                  unit = U2(that.time, electricCurrent) to U2(that.mass, distance))

    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(that.time, electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> ElectricConductivity

    @JvmName("divElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount / that.amount
                                            / convert(that.distance to distance,
                                                      that.distance to distance)
                                            * convert(that.electricCurrent to electricCurrent),
                                   unit = U2(that.time, electricCurrent) to U2(that.mass, distance))

    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount / that.amount,
                                   unit = U2(that.time, electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.distance to distance),
                              unit = electricCurrent)

    operator fun times(that: DistanceAmount<L>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       * convert(that.distance to distance),
                              unit = electricCurrent)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricCurrentDensity

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount
                                              / convert(that.distance to distance),
                                     unit = electricCurrent to distance)

    operator fun div(that: DistanceAmount<L>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount,
                                     unit = electricCurrent to distance)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount
                                              / convert(that.distance to distance),
                                     unit = electricCurrent to distance)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount,
                                     unit = electricCurrent to distance)

    // endregion

    // region -> ElectricDisplacementField

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount
                                                 / convert(that.distance to distance),
                                        unit = U2(electricCurrent, that.time) to distance)

    operator fun <T : Unit<Time>> times(that: PaceAmount<L, T>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount,
                                        unit = U2(electricCurrent, that.time) to distance)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount
                                                 / convert(that.distance to distance),
                                        unit = U2(electricCurrent, that.time) to distance)

    operator fun <T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount,
                                        unit = U2(electricCurrent, that.time) to distance)

    // endregion

    // region -> ElectricFieldStrength

    @JvmName("divElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.electricCurrent to electricCurrent,
                                                       that.electricCurrent to electricCurrent)
                                             * convert(that.distance to distance,
                                                       that.distance to distance),
                                    unit = U2(distance, that.mass) to U2(electricCurrent, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount,
                                    unit = U2(distance, that.mass) to U2(electricCurrent, that.time))

    @JvmName("timesElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount * that.amount
                                             * convert(that.distance to distance,
                                                       that.distance to distance)
                                             / convert(that.electricCurrent to electricCurrent,
                                                       that.electricCurrent to electricCurrent),
                                    unit = U2(distance, that.mass) to U2(electricCurrent, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount * that.amount,
                                    unit = U2(distance, that.mass) to U2(electricCurrent, that.time))

    // endregion

    // region -> ElectricTension

    @JvmName("divElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount / that.amount
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent)
                                       * convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.distance to distance),
                              unit = U2(distance, that.mass) to U2(electricCurrent, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(distance, that.mass) to U2(electricCurrent, that.time))

    // endregion

    // region -> EnergyDensity

    @JvmName("timesMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     / convert(that.electricCurrent to electricCurrent),
                            unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> Force

    @JvmName("timesMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance)
                             / convert(that.electricCurrent to electricCurrent),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Intensity

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        IntensityAmount(amount = amount * that.amount
                                 * convert(that.distance to distance)
                                 / convert(that.electricCurrent to electricCurrent),
                        unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = that.mass to that.time)

    // endregion

    // region -> MagneticDipoleMoment

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount
                                            * convert(that.distance to distance,
                                                      that.distance to distance,
                                                      that.distance to distance),
                                   unit = U2(distance, electricCurrent))

    operator fun times(that: VolumeAmount<L>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U2(distance, electricCurrent))

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("timesPermeability")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: PermeabilityAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount
                                           * convert(that.distance to distance)
                                           / convert(that.electricCurrent to electricCurrent,
                                                     that.electricCurrent to electricCurrent),
                                  unit = that.mass to U2(electricCurrent, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PermeabilityAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount,
                                  unit = that.mass to U2(electricCurrent, that.time))

    // endregion

    // region -> SpacialFrequency

    @JvmName("divElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.electricCurrent to electricCurrent),
                               unit = NUMERIC to distance)

    operator fun div(that: ElectricCurrentAmount<I>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Speed

    @JvmName("divElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.electricCurrent to electricCurrent)
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = distance to that.time)

    operator fun <T : Unit<Time>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to that.time)

    // endregion

    // region -> Yank

    @JvmName("timesElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        YankAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance)
                            / convert(that.electricCurrent to electricCurrent),
                   unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to that.time)

    // endregion
}
