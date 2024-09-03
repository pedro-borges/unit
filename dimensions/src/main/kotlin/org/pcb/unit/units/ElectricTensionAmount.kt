package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Quadruple
import org.pcb.unit.Triple
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
        major: Triple<M, L, L>,
        minor: Quadruple<T, T, T, EC>)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal()
                                       * convert(major.third to major.second)
                                       / convert(minor.second to minor.first,
                                                 minor.third to minor.first),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(major.first to 1,
                                                 major.second to 2,
                                                 minor.first to -3,
                                                 minor.fourth to -1)))
{
    val mass = major.first
    val length = major.second
    val time = minor.first
    val electricCurrent = minor.fourth

    companion object
    {
        operator fun invoke(amount: Number, electricTension: Unit<ElectricTension>) =
            ElectricTensionAmount(amount = amount.toBigBigDecimal() * electricTension.scale,
                                  major = KILOGRAM * METER * METER,
                                  minor = SECOND * SECOND * SECOND * AMPERE)
    }

    // region ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount * that.amount * that.unit.scale,
                              major = mass * length * length,
                              minor = time * time * time * electricCurrent)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount / that.amount / that.unit.scale,
                              major = mass * length * length,
                              minor = time * time * time * electricCurrent)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount + that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              major = mass * length * length,
                              minor = time * time * time * electricCurrent)

    @JvmName("plusElectricPotential")
    operator fun plus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount + that.amount,
                              major = mass * length * length,
                              minor = time * time * time * electricCurrent)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount - that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              major = mass * length * length,
                              minor = time * time * time * electricCurrent)

    @JvmName("minusElectricCurrent")
    operator fun minus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount - that.amount,
                              major = mass * length * length,
                              minor = time * time * time * electricCurrent)

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
                     major = mass * length * length,
                     minor = time * time)

    @JvmName("timesElectricCharge")
    operator fun times(that: ElectricChargeAmount<EC, T>) =
        EnergyAmount(amount = amount * that.amount,
                     major = mass * length * length,
                     minor = time * time)

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
                              major = electricCurrent)

    @JvmName("divElectricResistance")
    operator fun div(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              major = electricCurrent)

    // endregion

    // region ElectricResistance

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent),
                                 major = mass * length * length,
                                 minor = time * time * time * electricCurrent * electricCurrent)

    @JvmName("divElectricCurrent")
    operator fun div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 major = mass * length * length,
                                 minor = time * time * time * electricCurrent * electricCurrent)

    // endregion

    // region Power

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount * convert(that.electricCurrent to electricCurrent),
                    major = mass * length * length,
                    minor = time * time * time)

    @JvmName("timesElectricCurrent")
    operator fun times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount,
                    major = mass * length * length,
                    minor = time * time * time)

    // endregion
}
