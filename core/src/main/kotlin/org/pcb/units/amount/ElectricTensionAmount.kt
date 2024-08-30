package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.cannon.Ampere.AMPERE
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.div
import org.pcb.units.domain.ElectricCurrent
import org.pcb.units.domain.ElectricTension
import org.pcb.units.domain.Length
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

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
        ElectricTensionAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                                      that.length to length,
                                                                      that.length to length,
                                                                      time to that.time,
                                                                      time to that.time,
                                                                      time to that.time,
                                                                      electricCurrent to that.electricCurrent),
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
        ElectricTensionAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                                      that.length to length,
                                                                      that.length to length,
                                                                      time to that.time,
                                                                      time to that.time,
                                                                      time to that.time,
                                                                      electricCurrent to that.electricCurrent),
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
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                              that.length to length,
                                                              that.length to length,
                                                              time to that.time,
                                                              time to that.time,
                                                              time to that.time,
                                                              electricCurrent to that.electricCurrent))

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
        ElectricCurrentAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                                      that.length to length,
                                                                      that.length to length,
                                                                      time to that.time,
                                                                      time to that.time,
                                                                      time to that.time,
                                                                      electricCurrent to that.electricCurrent),
                              electricCurrent = electricCurrent)

    @JvmName("divElectricResistance")
    operator fun div(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              electricCurrent = electricCurrent)

    // endregion

    // region ElectricResistance

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(electricCurrent to that.electricCurrent),
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
}
