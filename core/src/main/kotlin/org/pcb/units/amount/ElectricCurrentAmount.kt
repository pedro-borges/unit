package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.div
import org.pcb.units.domain.ElectricCurrent
import org.pcb.units.domain.Length
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

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
        ElectricTensionAmount(amount = amount * that.amount * convert(electricCurrent to that.electricCurrent),
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

    operator fun <M : Unit<Mass>, L : Unit<Length>, T: Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        PowerAmount(amount = amount * that.amount * convert(electricCurrent to that.electricCurrent),
                    mass = that.mass,
                    length = that.length,
                    length2 = that.length,
                    time = that.time,
                    time2 = that.time,
                    time3 = that.time)

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T: Unit<Time>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        PowerAmount(amount = amount * that.amount,
                                 mass = that.mass,
                                 length = that.length,
                                 length2 = that.length,
                                 time = that.time,
                                 time2 = that.time,
                                 time3 = that.time)

    // endregion
}
