package org.pcb.unit.amount

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amount.base.Ampere.AMPERE
import org.pcb.unit.amount.base.Kilogram.KILOGRAM
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.amount.base.Second.SECOND
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.ElectricResistance
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal

open class ElectricResistanceAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>>(
        amount: Number,
        val mass: M,
        val length: L,
        length2: L,
        val time: T,
        time2: T,
        time3: T,
        val electricCurrent: EC,
        electricCurrent2: EC)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal() * convert(length2 to length,
                                                                          time to time2,
                                                                          time to time3,
                                                                          electricCurrent to electricCurrent2),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(mass to 1,
                                                 length to 2,
                                                 time to -3,
                                                 electricCurrent to -2)))
{
    companion object
    {
        operator fun invoke(amount: Number, electricResistance: Unit<ElectricResistance>) =
            ElectricResistanceAmount(amount = amount.toBigBigDecimal() * electricResistance.scale,
                                     mass = KILOGRAM,
                                     length = METER,
                                     length2 = METER,
                                     time = SECOND,
                                     time2 = SECOND,
                                     time3 = SECOND,
                                     electricCurrent = AMPERE,
                                     electricCurrent2 = AMPERE)
    }

    // region ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount * that.amount * that.unit.scale,
                                 mass = mass,
                                 length = length,
                                 length2 = length,
                                 time = time,
                                 time2 = time,
                                 time3 = time,
                                 electricCurrent = electricCurrent,
                                 electricCurrent2 = electricCurrent)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount / that.amount / that.unit.scale,
                                 mass = mass,
                                 length = length,
                                 length2 = length,
                                 time = time,
                                 time2 = time,
                                 time3 = time,
                                 electricCurrent = electricCurrent,
                                 electricCurrent2 = electricCurrent)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricResistanceAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                                         that.length to length,
                                                                         that.length to length,
                                                                         time to that.time,
                                                                         time to that.time,
                                                                         time to that.time,
                                                                         electricCurrent to that.electricCurrent,
                                                                         electricCurrent to that.electricCurrent),
                                 mass = mass,
                                 length = length,
                                 length2 = length,
                                 time = time,
                                 time2 = time,
                                 time3 = time,
                                 electricCurrent = electricCurrent,
                                 electricCurrent2 = electricCurrent)

    @JvmName("plusElectricResistance")
    operator fun plus(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricResistanceAmount(amount = amount + that.amount,
                                 mass = mass,
                                 length = length,
                                 length2 = length,
                                 time = time,
                                 time2 = time,
                                 time3 = time,
                                 electricCurrent = electricCurrent,
                                 electricCurrent2 = electricCurrent)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricResistanceAmount(amount = amount - that.amount
                                          * convert(that.mass to mass,
                                                    that.length to length,
                                                    that.length to length)
                                          / convert(that.time to time,
                                                    that.time to time,
                                                    that.time to time,
                                                    that.electricCurrent to electricCurrent,
                                                    that.electricCurrent to electricCurrent),
                                 mass = mass,
                                 length = length,
                                 length2 = length,
                                 time = time,
                                 time2 = time,
                                 time3 = time,
                                 electricCurrent = electricCurrent,
                                 electricCurrent2 = electricCurrent)

    @JvmName("minusElectricCurrent")
    operator fun minus(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricResistanceAmount(amount = amount - that.amount,
                                 mass = mass,
                                 length = length,
                                 length2 = length,
                                 time = time,
                                 time2 = time,
                                 time3 = time,
                                 electricCurrent = electricCurrent,
                                 electricCurrent2 = electricCurrent)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, L, T, EC>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.mass to mass,
                                         that.length to length,
                                         that.length to length)
                               * convert(that.time to time,
                                         that.time to time,
                                         that.time to time,
                                         that.electricCurrent to electricCurrent,
                                         that.electricCurrent to electricCurrent))

    @JvmName("divElectricCurrent")
    operator fun div(that: ElectricResistanceAmount<M, L, T, EC>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount * that.amount * convert(that.electricCurrent to electricCurrent),
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = electricCurrent)

    @JvmName("timesElectricCurrent")
    operator fun times(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = electricCurrent)

    // endregion
}
