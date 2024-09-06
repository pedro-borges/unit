package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.T2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCharge
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.times
import org.pcb.unit.to
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Second.SECOND

open class ElectricChargeAmount<EC : Unit<ElectricCurrent>, T : Unit<Time>>(
        amount: Number,
        unit: T2<EC, T>)
    : Amount<ElectricCharge>(amount = amount.toBigBigDecimal(),
                             unit = CompositeUnit(
                                 domain = ElectricCharge::class,
                                 units = listOf(unit.first to 1,
                                                unit.second to 1)))
{
    val electricCurrent = unit.first
    val time = unit.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricCharge>) =
            ElectricChargeAmount(amount = amount.toBigBigDecimal() * unit.scale,
                                 unit = T2(AMPERE, SECOND))
    }

    // region -> ElectricCharge

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricChargeAmount(amount = amount * that.amount * that.unit.scale,
                             unit = T2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricChargeAmount(amount = amount.to(that.amount).to(that.unit.scale),
                             unit = T2(electricCurrent, time))

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> plus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount + that.amount * convert(that.electricCurrent to electricCurrent,
                                                                     that.time to time),
                             unit = T2(electricCurrent, time))

    @JvmName("plusElectricCharge")
    operator fun plus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount + that.amount,
                             unit = T2(electricCurrent, time))

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> minus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount - that.amount * convert(that.electricCurrent to electricCurrent,
                                                                     that.time to time),
                             unit = T2(electricCurrent, time))

    @JvmName("minusElectricCharge")
    operator fun minus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount - that.amount,
                             unit = T2(electricCurrent, time))

    // endregion

    // region -> Natural

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        amount.to(that.amount).to(convert(that.electricCurrent to electricCurrent, that.time to time))

    @JvmName("divElectricCharge")
    operator fun div(that: ElectricChargeAmount<EC, T>) =
        amount.to(that.amount)

    // endregion

    // region -> ElectricCurrent

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricCurrentAmount(amount = amount.to(that.amount).to(that.unit.scale),
                              unit = electricCurrent)

    // endregion

    // region -> ElectricCapacitance

    operator fun <M : Unit<Mass>, L : Unit<Length>, EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCapacitanceAmount(amount = amount.to(that.amount) * convert(that.electricCurrent to electricCurrent,
                                                                            that.time to time,
                                                                            that.time to time,
                                                                            that.time to time),
                                  unit = Pair(T2(time, electricCurrent),
                                              T2(that.mass, that.length)))

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCapacitanceAmount(amount = amount.to(that.amount),
                                  unit = Pair(T2(time, electricCurrent),
                                              T2(that.mass, that.length)))

    // endregion

    // region -> ElectricTension

    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> div(that: ElectricCapacitanceAmount<T, EC, M, L>) =
        ElectricTensionAmount(amount = amount.to(that.amount).to(convert(that.electricCurrent to electricCurrent,
                                                                         that.electricCurrent to electricCurrent,
                                                                         that.time to time,
                                                                         that.time to time,
                                                                         that.time to time,
                                                                         that.time to time)),
                              unit = Pair(T2(that.mass, that.length),
                                          T2(time, electricCurrent)))

    @JvmName("divElectricCapacitance")
    operator fun <M : Unit<Mass>, L : Unit<Length>> div(that: ElectricCapacitanceAmount<T, EC, M, L>) =
        ElectricTensionAmount(amount = amount.to(that.amount),
                              unit = Pair(T2(that.mass, that.length),
                                          T2(time, electricCurrent)))

    // endregion

    // region -> Energy

    operator fun <M : Unit<Mass>, L : Unit<Length>, EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        EnergyAmount(amount = (amount * that.amount).to(convert(that.electricCurrent to electricCurrent,
                                                                that.time to time,
                                                                that.time to time,
                                                                that.time to time)),
                     unit = Pair(T2(that.mass, that.length),
                                 time))

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = Pair(T2(that.mass, that.length),
                                 time))

    // endregion

    // region -> MagneticFlux

    operator fun <M : Unit<Mass>, L : Unit<Length>, EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = (amount * that.amount).to(convert(that.electricCurrent to electricCurrent,
                                                                      that.electricCurrent to electricCurrent,
                                                                      that.time to time,
                                                                      that.time to time,
                                                                      that.time to time)),
                           unit = Pair(T2(that.mass, that.length),
                                       T2(time, electricCurrent)))

    @JvmName("timesElectricResistance")
    operator fun <M : Unit<Mass>, L : Unit<Length>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = Pair(T2(that.mass, that.length),
                                       T2(time, electricCurrent)))

    // endregion
}
