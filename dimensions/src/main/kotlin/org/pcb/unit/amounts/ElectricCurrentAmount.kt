package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricCurrentAmount<I : Unit<ElectricCurrent>>(
        amount: Number,
        unit: I)
    : Amount<ElectricCurrent>(amount = amount.toBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = mapOf(unit to 1)))
{
    val electricCurrent = unit

    // region -> ElectricCurrent

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                              unit = electricCurrent)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                              unit = electricCurrent)

    @JvmName("plusElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> plus(that: ElectricCurrentAmount<I>) =
        ElectricCurrentAmount(amount = amount + that.amount
                                       * convert(that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    operator fun plus(that: ElectricCurrentAmount<I>) =
        ElectricCurrentAmount(amount = amount + that.amount,
                              unit = electricCurrent)

    @JvmName("minusElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> minus(that: ElectricCurrentAmount<I>) =
        ElectricCurrentAmount(amount = amount - that.amount
                                       * convert(that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    operator fun minus(that: ElectricCurrentAmount<I>) =
        ElectricCurrentAmount(amount = amount - that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> Scalar

    @JvmName("divElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent),
                     unit = NUMERIC)

    operator fun div(that: ElectricCurrentAmount<I>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Area

    @JvmName("divElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: ElectricCurrentDensityAmount<L, I>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent),
                   unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: ElectricCurrentDensityAmount<L, I>) =
        AreaAmount(amount = amount / that.amount,
                   unit = that.distance)

    // endregion

    // region -> Distance

    @JvmName("divMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.electricCurrent to electricCurrent),
                       unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: MagneticFieldStrengthAmount<L, I>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = that.distance)

    // endregion

    // region -> ElectricCharge

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(electricCurrent, that.time))

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, that.time))

    // endregion

    // region -> ElectricConductance

    @JvmName("divElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           * convert(that.electricCurrent to electricCurrent),
                                  unit = U2(that.time, electricCurrent) to U2(that.mass, that.distance))

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(that.time, electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricCurrentDensity

    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount,
                                     unit = electricCurrent to that.distance)

    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount,
                                     unit = electricCurrent to that.distance)

    // endregion

    // region -> ElectricDipoleMoment

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U3(that.distance, electricCurrent, that.time))

    // endregion

    // region -> ElectricTension

    @JvmName("divElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount / that.amount
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent),
                              unit = U2(that.distance, that.mass) to U2(electricCurrent, that.time))

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(that.distance, that.mass) to U2(electricCurrent, that.time))

    @JvmName("timesElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount * that.amount
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent),
                              unit = U2(that.distance, that.mass) to U2(electricCurrent, that.time))

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = U2(that.distance, that.mass) to U2(electricCurrent, that.time))

    // endregion

    // region -> Energy

    @JvmName("timesMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.electricCurrent to electricCurrent),
                     unit = U2(that.distance, that.mass) to that.time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> Frequency

    @JvmName("divElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<I, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.electricCurrent to electricCurrent),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> div(that: ElectricChargeAmount<I, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divMagneticDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticDipoleMomentAmount<L, I>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.electricCurrent to electricCurrent),
                             unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: MagneticDipoleMomentAmount<L, I>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to that.distance)

    // endregion

    // region -> MagneticDipoleMoment

    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U2(that.distance, electricCurrent))

    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        MagneticDipoleMomentAmount(amount = amount / that.amount,
                                   unit = U2(that.distance, electricCurrent))

    // endregion

    // region -> MagneticFieldStrength

    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount,
                                    unit = electricCurrent to that.distance)

    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount,
                                    unit = electricCurrent to that.distance)

    // endregion

    // region -> MagneticFlux

    @JvmName("timesInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.electricCurrent to electricCurrent),
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, that.time))

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, that.time))

    // endregion

    // region -> Power

    @JvmName("timesElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.electricCurrent to electricCurrent),
                    unit = U2(that.distance, that.mass) to that.time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.electricCurrent to electricCurrent),
                              unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to that.time)

    // endregion

    // region -> Yank

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.electricCurrent to electricCurrent),
                   unit = U2(that.distance, that.mass) to that.time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, that.mass) to that.time)

    // endregion
}
