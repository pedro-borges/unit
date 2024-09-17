package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
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

open class ElectricChargeAmount<EC : Unit<ElectricCurrent>, T : Unit<Time>>(
        amount: Number,
        unit: U2<EC, T>)
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
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> plus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount + that.amount * convert(that.electricCurrent to electricCurrent,
                                                                     that.time to time),
                             unit = U2(electricCurrent, time))

    operator fun plus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount + that.amount,
                             unit = U2(electricCurrent, time))

    @JvmName("minusElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> minus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount - that.amount * convert(that.electricCurrent to electricCurrent,
                                                                     that.time to time),
                             unit = U2(electricCurrent, time))

    operator fun minus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount - that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> Capacitance

    @JvmName("divElectricTension")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount / that.amount
                                   * convert(that.electricCurrent to electricCurrent,
                                             that.time to time,
                                             that.time to time,
                                             that.time to time),
                          unit = U2(time, electricCurrent) to U2(that.mass, that.distance))

    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: ElectricTensionAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount / that.amount,
                          unit = U2(time, electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricConductance

    @JvmName("divMagneticFlux")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxAmount<D, EC, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           * convert(that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = U2(time, electricCurrent) to U2(that.mass, that.distance))

    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: MagneticFluxAmount<D, EC, M, T>) =
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

    // region -> ElectricTension

    @JvmName("divCapacitance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricTensionAmount(amount = amount / that.amount
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesElectricTension")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<D, EC, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = U2(that.distance, that.mass) to time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: ElectricTensionAmount<D, EC, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> MagneticFlux

    @JvmName("divElectricConductance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount / that.amount
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: ElectricConductanceAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    @JvmName("timesElectricResistance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: ElectricResistanceAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.distance, that.mass) to U2(electricCurrent, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("timesMagneticFluxDensity")
    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<EC, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: MagneticFluxDensityAmount<EC, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> Scalar

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.electricCurrent to electricCurrent,
                                   that.time to time))

    operator fun div(that: ElectricChargeAmount<EC, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Time

    @JvmName("divElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent),
                   unit = time)

    operator fun div(that: ElectricCurrentAmount<EC>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
