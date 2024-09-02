package org.pcb.unit.amount

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amount.scalar.NaturalAmount
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
        val electricCurrent: EC)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(electricCurrent to 1)))
{
    // region ElectricCurrent

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount * that.amount * that.unit.scale,
                              electricCurrent = electricCurrent)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount / that.amount / that.unit.scale,
                              electricCurrent = electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> plus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount + that.amount * convert(that.electricCurrent to electricCurrent),
                              electricCurrent = electricCurrent)

    @JvmName("plusElectricCurrent")
    operator fun plus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount + that.amount,
                              electricCurrent = electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> minus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount - that.amount * convert(that.electricCurrent to electricCurrent),
                              electricCurrent = electricCurrent)

    @JvmName("minusElectricCurrent")
    operator fun minus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount - that.amount,
                              electricCurrent = electricCurrent)

    // endregion

    // region Scalar

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        NaturalAmount(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent))

    @JvmName("divElectricCurrent")
    operator fun div(that: ElectricCurrentAmount<EC>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region ElectricCharge

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             electricCurrent = electricCurrent,
                             time = that.time)

    // endregion

    // region ElectricTension

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent,
                                                                      that.electricCurrent to electricCurrent),
                              mass = that.mass,
                              length = that.length,
                              length2 = that.length,
                              time = that.time,
                              time2 = that.time,
                              time3 = that.time,
                              electricCurrent = electricCurrent)

    @JvmName("timesElectricResistance")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              mass = that.mass,
                              length = that.length,
                              length2 = that.length,
                              time = that.time,
                              time2 = that.time,
                              time3 = that.time,
                              electricCurrent = electricCurrent)

    // endregion

    // region Power

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        PowerAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent),
                    mass = that.mass,
                    length = that.length,
                    length2 = that.length,
                    time = that.time,
                    time2 = that.time,
                    time3 = that.time)

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        PowerAmount(amount = amount * that.amount,
                    mass = that.mass,
                    length = that.length,
                    length2 = that.length,
                    time = that.time,
                    time2 = that.time,
                    time3 = that.time)

    // endregion
}
