package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.ElectricTension
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount

open class ElectricTensionAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>>(
        amount: Number,
        val mass: M,
        val length: L,
        length2: L,
        val time: T,
        time2: T,
        time3: T,
        val electricCurrent: EC)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal() * convert(length2 to length,
                                                                          time to time2,
                                                                          time to time3),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(mass to 1,
                                                 length to 2,
                                                 time to -3,
                                                 electricCurrent to -1)))
{
    companion object
    {
        operator fun invoke(amount: Number, electricTension: Unit<ElectricTension>) =
            ElectricTensionAmount(amount = amount.toBigBigDecimal() * electricTension.scale,
                                  mass = KILOGRAM,
                                  length = METER,
                                  length2 = METER,
                                  time = SECOND,
                                  time2 = SECOND,
                                  time3 = SECOND,
                                  electricCurrent = AMPERE)
    }

    // region ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount * that.amount * that.unit.scale,
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = electricCurrent)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount / that.amount / that.unit.scale,
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = electricCurrent)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount + that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = electricCurrent)

    @JvmName("plusElectricPotential")
    operator fun plus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount + that.amount,
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = electricCurrent)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount - that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = electricCurrent)

    @JvmName("minusElectricCurrent")
    operator fun minus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount - that.amount,
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = electricCurrent)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.mass to mass,
                                         that.length to length,
                                         that.length to length)
                               * convert(that.time to time,
                                         that.time to time,
                                         that.time to time,
                                         that.electricCurrent to electricCurrent))

    @JvmName("divElectricCurrent")
    operator fun div(that: ElectricTensionAmount<M, L, T, EC>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Energy

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time,
                                                             that.electricCurrent to electricCurrent),
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)

    @JvmName("timesElectricCharge")
    operator fun times(that: ElectricChargeAmount<EC, T>) =
        EnergyAmount(amount = amount * that.amount,
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)

    // endregion

    // region ElectricCurrent

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent),
                              electricCurrent = electricCurrent)

    @JvmName("divElectricResistance")
    operator fun div(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              electricCurrent = electricCurrent)

    // endregion

    // region ElectricResistance

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent),
                                 mass = mass,
                                 length = length,
                                 length2 = length,
                                 time = time,
                                 time2 = time,
                                 time3 = time,
                                 electricCurrent = electricCurrent,
                                 electricCurrent2 = electricCurrent)

    @JvmName("divElectricCurrent")
    operator fun div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 mass = mass,
                                 length = length,
                                 length2 = length,
                                 time = time,
                                 time2 = time,
                                 time3 = time,
                                 electricCurrent = electricCurrent,
                                 electricCurrent2 = electricCurrent)

    // endregion

    // region Power

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount * convert(that.electricCurrent to electricCurrent),
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)

    @JvmName("timesElectricCurrent")
    operator fun times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount,
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)

    // endregion
}
