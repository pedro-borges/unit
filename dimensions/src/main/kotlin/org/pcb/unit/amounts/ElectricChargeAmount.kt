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

    // region -> Numeric

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        Numeric(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent,
                                                        that.time to time))

    operator fun div(that: ElectricChargeAmount<EC, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> ElectricCurrent

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricCurrentAmount(amount = amount / that.amount / convert(that.time to time),
                              unit = electricCurrent)

    // endregion

    // region -> Capacitance

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricTensionAmount<M, D, T, EC>) =
        CapacitanceAmount(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent,
                                                                  that.time to time,
                                                                  that.time to time,
                                                                  that.time to time),
                          unit = U2(time, electricCurrent) to U2(that.mass, that.distance))

    operator fun <M : Unit<Mass>, D : Unit<Distance>> div(that: ElectricTensionAmount<M, D, T, EC>) =
        CapacitanceAmount(amount = amount / that.amount,
                          unit = U2(time, electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricTension

    @JvmName("divCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> div(that: CapacitanceAmount<T, EC, M, D>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent,
                                                                      that.electricCurrent to electricCurrent,
                                                                      that.time to time,
                                                                      that.time to time,
                                                                      that.time to time,
                                                                      that.time to time),
                              unit = U2(that.mass, that.distance) to U2(time, electricCurrent))

    operator fun <M : Unit<Mass>, D : Unit<Distance>> div(that: CapacitanceAmount<T, EC, M, D>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(that.mass, that.distance) to U2(time, electricCurrent))

    // endregion

    // region -> Energy

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricTensionAmount<M, D, T, EC>) =
        EnergyAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent,
                                                             that.time to time,
                                                             that.time to time,
                                                             that.time to time),
                     unit = U2(that.mass, that.distance) to time)

    operator fun <M : Unit<Mass>, D : Unit<Distance>> times(that: ElectricTensionAmount<M, D, T, EC>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.mass, that.distance) to time)

    // endregion

    // region -> MagneticFlux

    @JvmName("timesElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricResistanceAmount<M, D, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent,
                                                                   that.electricCurrent to electricCurrent,
                                                                   that.time to time,
                                                                   that.time to time,
                                                                   that.time to time),
                           unit = U2(that.mass, that.distance) to U2(time, electricCurrent))

    operator fun <M : Unit<Mass>, D : Unit<Distance>> times(that: ElectricResistanceAmount<M, D, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.mass, that.distance) to U2(time, electricCurrent))

    // endregion
}
