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
import org.pcb.unit.units.scalar.NaturalAmount

open class ElectricCurrentAmount<EC : Unit<ElectricCurrent>>(
        amount: Number,
        major: EC)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(major to 1)))
{
    val electricCurrent = major

    // region ElectricCurrent

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount * that.amount * that.unit.scale,
                              major = electricCurrent)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount / that.amount / that.unit.scale,
                              major = electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> plus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount + that.amount * convert(that.electricCurrent to electricCurrent),
                              major = electricCurrent)

    @JvmName("plusElectricCurrent")
    operator fun plus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount + that.amount,
                              major = electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> minus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount - that.amount * convert(that.electricCurrent to electricCurrent),
                              major = electricCurrent)

    @JvmName("minusElectricCurrent")
    operator fun minus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount - that.amount,
                              major = electricCurrent)

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
                             major = electricCurrent * that.time)

    // endregion

    // region ElectricTension

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent,
                                                                      that.electricCurrent to electricCurrent),
                              major = that.mass * that.length * that.length,
                              minor = that.time * that.time * that.time * electricCurrent)

    @JvmName("timesElectricResistance")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              major = that.mass * that.length * that.length,
                              minor = that.time * that.time * that.time * electricCurrent)

    // endregion

    // region Power

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        PowerAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent),
                    major = that.mass * that.length * that.length,
                    minor = that.time * that.time * that.time)

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        PowerAmount(amount = amount * that.amount,
                    major = that.mass * that.length * that.length,
                    minor = that.time * that.time * that.time)

    // endregion
}
