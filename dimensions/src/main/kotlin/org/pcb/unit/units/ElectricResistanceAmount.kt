package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Quintuple
import org.pcb.unit.Triple
import org.pcb.unit.Unit
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
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount

open class ElectricResistanceAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<Triple<M, L, L>, Quintuple<T, T, T, EC, EC>>)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal()
                                       * convert(unit.first.third to unit.first.second)
                                       / convert(unit.second.second to unit.second.first,
                                                 unit.second.third to unit.second.first,
                                                 unit.second.fifth to unit.second.fourth),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(unit.first.first to 1,
                                                 unit.first.second to 2,
                                                 unit.second.first to -3,
                                                 unit.second.fourth to -2)))
{
    val mass = unit.first.first
    val length = unit.first.second
    val time = unit.second.first
    val electricCurrent = unit.second.fourth

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricResistance>) =
            ElectricResistanceAmount(amount = amount.toBigBigDecimal() * unit.scale,
                                     unit = (KILOGRAM * METER * METER) /
                                            (SECOND * SECOND * SECOND * AMPERE * AMPERE))
    }

    // region ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount * that.amount * that.unit.scale,
                                 unit = mass * length * length / (time * time * time * electricCurrent * electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount / that.amount / that.unit.scale,
                                 unit = mass * length * length / (time * time * time * electricCurrent * electricCurrent))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricResistanceAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                                         that.length to length,
                                                                         that.length to length,
                                                                         time to that.time,
                                                                         time to that.time,
                                                                         time to that.time,
                                                                         electricCurrent to that.electricCurrent,
                                                                         electricCurrent to that.electricCurrent),
                                 unit = mass * length * length / (time * time * time * electricCurrent * electricCurrent))

    @JvmName("plusElectricResistance")
    operator fun plus(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricResistanceAmount(amount = amount + that.amount,
                                 unit = mass * length * length / (time * time * time * electricCurrent * electricCurrent))

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
                                 unit = mass * length * length / (time * time * time * electricCurrent * electricCurrent))

    @JvmName("minusElectricCurrent")
    operator fun minus(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricResistanceAmount(amount = amount - that.amount,
                                 unit = mass * length * length / (time * time * time * electricCurrent * electricCurrent))

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
                              unit = (mass * length * length) /
                                     (time * time * time * electricCurrent))

    @JvmName("timesElectricCurrent")
    operator fun times(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = (mass * length * length) /
                                     (time * time * time * electricCurrent))

    // endregion
}
