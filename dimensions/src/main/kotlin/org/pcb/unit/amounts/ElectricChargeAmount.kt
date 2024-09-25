package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCharge
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricChargeAmount<I : Unit<ElectricCurrent>, T : Unit<Time>>(
        amount: Number,
        unit: U2<I, T>)
    : Amount<ElectricCharge>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = ElectricCharge::class,
                                 units = mapOf(unit.first to 1,
                                               unit.second to 1)))
{
    val electricCurrent = unit.first
    val time = unit.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricCharge>) =
            ElectricChargeAmount(amount = amount * unit.scale,
                                 unit = U2(AMPERE, SECOND))
    }

    // region -> ElectricCharge

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricChargeAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                             unit = U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricChargeAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                             unit = U2(electricCurrent, time))

    @JvmName("plusElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> plus(that: ElectricChargeAmount<I, T>) =
        ElectricChargeAmount(amount = amount + that.amount
                                      * convert(that.electricCurrent to electricCurrent,
                                                that.time to time),
                             unit = U2(electricCurrent, time))

    operator fun plus(that: ElectricChargeAmount<I, T>) =
        ElectricChargeAmount(amount = amount + that.amount,
                             unit = U2(electricCurrent, time))

    @JvmName("minusElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> minus(that: ElectricChargeAmount<I, T>) =
        ElectricChargeAmount(amount = amount - that.amount
                                      * convert(that.electricCurrent to electricCurrent,
                                                that.time to time),
                             unit = U2(electricCurrent, time))

    operator fun minus(that: ElectricChargeAmount<I, T>) =
        ElectricChargeAmount(amount = amount - that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> Scalar

    @JvmName("divElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<I, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ElectricChargeAmount<I, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.electricCurrent to electricCurrent),
                       unit = U2(that.distance, time))

    operator fun <L : Unit<Distance>> div(that: MagneticFieldStrengthAmount<L, I>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(that.distance, time))

    // endregion

    // region -> Action

    @JvmName("timesMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        ActionAmount(amount = amount * that.amount
                              / convert(that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time),
                     unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MagneticFluxAmount<L, I, M, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> Area

    @JvmName("divElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent,
                                      that.time to time),
                   unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = that.distance)

    // endregion

    // region -> Capacitance

    @JvmName("divElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount / that.amount
                                   * convert(that.electricCurrent to electricCurrent,
                                             that.time to time,
                                             that.time to time,
                                             that.time to time),
                          unit = U2(time, electricCurrent) to U2(that.mass, that.distance))

    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: ElectricTensionAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount / that.amount,
                          unit = U2(time, electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricConductance

    @JvmName("divMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           * convert(that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = U2(time, electricCurrent) to U2(that.mass, that.distance))

    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(time, electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       / convert(that.time to time),
                              unit = electricCurrent)

    operator fun times(that: FrequencyAmount<T>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.time to time),
                              unit = electricCurrent)

    operator fun div(that: TimeAmount<T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricDipoleMoment

    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U3(that.distance, electricCurrent, time))

    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        ElectricDipoleMomentAmount(amount = amount / that.amount,
                                   unit = U3(that.distance, electricCurrent, time))

    // endregion

    // region -> ElectricDisplacementField

    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount,
                                        unit = U2(electricCurrent, time) to that.distance)

    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount,
                                        unit = U2(electricCurrent, time) to that.distance)

    // endregion

    // region -> ElectricTension

    @JvmName("divCapacitance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: CapacitanceAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount / that.amount
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: CapacitanceAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: ElectricTensionAmount<L, I, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> Force

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.electricCurrent to electricCurrent,
                                       that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.time to time),
                                    unit = electricCurrent to that.distance)

    operator fun <L : Unit<Distance>> div(that: AbsementAmount<L, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount,
                                    unit = electricCurrent to that.distance)

    // endregion

    // region -> MagneticFlux

    @JvmName("divElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount / that.amount
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    @JvmName("timesElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("timesMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divElectricDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDipoleMomentAmount<L, I, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.electricCurrent to electricCurrent,
                                                  that.time to time),
                               unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: ElectricDipoleMomentAmount<L, I, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to that.distance)

    // endregion

    // region -> Time

    @JvmName("divElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent),
                   unit = time)

    operator fun div(that: ElectricCurrentAmount<I>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
