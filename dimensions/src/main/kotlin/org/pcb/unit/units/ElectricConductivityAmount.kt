package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Fraction
import org.pcb.unit.Quintuple
import org.pcb.unit.Triple
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricConductivity
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount

open class ElectricConductivityAmount<T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>>(
        amount: Number,
        unit: Fraction<Quintuple<T, T, T, EC, EC>, Triple<M, L, L>>)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal()
                                       * convert(unit.major.second to unit.major.first,
                                                 unit.major.third to unit.major.first,
                                                 unit.major.fifth to unit.major.fourth)
                                       / convert(unit.minor.third to unit.minor.second),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(unit.major.first to 3,
                                                 unit.major.fourth to 2,
                                                 unit.minor.first to -1,
                                                 unit.minor.second to -2)))
{
    val mass = unit.minor.first
    val length = unit.minor.second
    val time = unit.major.first
    val electricCurrent = unit.major.fourth

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricConductivity>) =
            ElectricConductivityAmount(amount = amount.toBigBigDecimal() * unit.scale,
                                       unit = (SECOND * SECOND * SECOND * AMPERE * AMPERE) /
                                              (KILOGRAM * METER * METER))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricConductivityAmount(amount = amount * that.amount * that.unit.scale,
                                   unit = (time * time * time * electricCurrent * electricCurrent) /
                                          (mass * length * length))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricConductivityAmount(amount = amount / that.amount / that.unit.scale,
                                   unit = (time * time * time * electricCurrent * electricCurrent) /
                                          (mass * length * length))

    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> plus(that: ElectricConductivityAmount<T, EC, M, L>) =
        ElectricConductivityAmount(amount = amount + that.amount
                                            * convert(that.time to time,
                                                      that.time to time,
                                                      that.time to time,
                                                      that.electricCurrent to electricCurrent,
                                                      that.electricCurrent to electricCurrent)
                                            / convert(that.mass to mass,
                                                      that.length to length,
                                                      that.length to length),
                                   unit = (time * time * time * electricCurrent * electricCurrent) /
                                          (mass * length * length))

    @JvmName("plusElectricConductivity")
    operator fun plus(that: ElectricConductivityAmount<T, EC, M, L>) =
        ElectricConductivityAmount(amount = amount + that.amount,
                                   unit = (time * time * time * electricCurrent * electricCurrent) /
                                          (mass * length * length))

    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> minus(that: ElectricConductivityAmount<T, EC, M, L>) =
        ElectricConductivityAmount(amount = amount - that.amount
                                            * convert(that.time to time,
                                                      that.time to time,
                                                      that.time to time,
                                                      that.electricCurrent to electricCurrent,
                                                      that.electricCurrent to electricCurrent)
                                            / convert(that.mass to mass,
                                                      that.length to length,
                                                      that.length to length),
                                   unit = (time * time * time * electricCurrent * electricCurrent) /
                                          (mass * length * length))

    @JvmName("minusElectricConductivity")
    operator fun minus(that: ElectricConductivityAmount<T, EC, M, L>) =
        ElectricConductivityAmount(amount = amount - that.amount,
                                   unit = (time * time * time * electricCurrent * electricCurrent) /
                                          (mass * length * length))

    // endregion

    // region -> Scalar

    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> div(that: ElectricConductivityAmount<T, EC, M, L>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.time to time,
                                         that.time to time,
                                         that.time to time,
                                         that.electricCurrent to electricCurrent,
                                         that.electricCurrent to electricCurrent)
                               * convert(that.mass to mass,
                                         that.length to length,
                                         that.length to length))

    @JvmName("divElectricConductivity")
    operator fun div(that: ElectricConductivityAmount<T, EC, M, L>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region -> ElectricCurrent

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time)
                                       / convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length),
                              unit = electricCurrent)

    @JvmName("timesElectricTension")
    operator fun times(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion
}
