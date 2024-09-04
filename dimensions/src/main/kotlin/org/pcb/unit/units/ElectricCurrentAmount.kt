package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal

open class ElectricCurrentAmount<EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: EC)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(unit to 1)))
{
    val electricCurrent = unit

    // region -> ElectricCurrent

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount * that.amount * that.unit.scale,
                              unit = electricCurrent)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount / that.amount / that.unit.scale,
                              unit = electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> plus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount + that.amount * convert(that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    @JvmName("plusElectricCurrent")
    operator fun plus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount + that.amount,
                              unit = electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> minus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount - that.amount * convert(that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    @JvmName("minusElectricCurrent")
    operator fun minus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount - that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> Natural

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        amount / that.amount / convert(that.electricCurrent to electricCurrent)

    @JvmName("divElectricCurrent")
    operator fun div(that: ElectricCurrentAmount<EC>) =
        amount / that.amount

    // endregion

    // region -> ElectricCharge

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = electricCurrent * that.time)

    // endregion

    // region -> ElectricTension

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent,
                                                                      that.electricCurrent to electricCurrent),
                              unit = (that.mass * that.length * that.length) /
                                     (that.time * that.time * that.time * electricCurrent))

    @JvmName("timesElectricResistance")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = (that.mass * that.length * that.length) /
                                     (that.time * that.time * that.time * electricCurrent))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricConductivityAmount<T, EC, M, L>) =
        ElectricTensionAmount(amount = amount / that.amount * convert(that.electricCurrent to electricCurrent,
                                                                      that.electricCurrent to electricCurrent),
                              unit = (that.mass * that.length * that.length) /
                                     (that.time * that.time * that.time * electricCurrent))

    @JvmName("divElectricConductivity")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: ElectricConductivityAmount<T, EC, M, L>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = (that.mass * that.length * that.length) /
                                     (that.time * that.time * that.time * electricCurrent))

    // endregion

    // region -> ElectricConductivity

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricConductivityAmount(amount = amount / that.amount * convert(that.electricCurrent to electricCurrent),
                                   unit = (that.time * that.time * that.time * electricCurrent * electricCurrent) /
                                          (that.mass * that.length * that.length))

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricConductivityAmount(amount = amount / that.amount,
                                   unit = (that.time * that.time * that.time * electricCurrent * electricCurrent) /
                                          (that.mass * that.length * that.length))

    // endregion

    // region -> Power

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        PowerAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent),
                    unit = (that.mass * that.length * that.length) /
                           (that.time * that.time * that.time))

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        PowerAmount(amount = amount * that.amount,
                    unit = (that.mass * that.length * that.length) /
                           (that.time * that.time * that.time))

    // endregion
}
