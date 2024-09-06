package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.T2
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
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class ElectricConductivityAmount<T3 : Unit<Time>, EC2 : Unit<ElectricCurrent>, M : Unit<Mass>, L2 : Unit<Length>>(
        amount: Number,
        unit: Pair<T2<T3, EC2>, T2<M, L2>>)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(unit.major.first to 3,
                                                 unit.major.second to 2,
                                                 unit.minor.first to -1,
                                                 unit.minor.second to -2)))
{
    val time = unit.major.first
    val electricCurrent = unit.major.second
    val mass = unit.minor.first
    val length = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricConductivity>) =
            ElectricConductivityAmount(amount = amount.toBigBigDecimal() * unit.scale,
                                       unit = T2(SECOND, AMPERE) to T2(KILOGRAM, METER))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricConductivityAmount(amount = amount * that.amount * that.unit.scale,
                                   unit = T2(time, electricCurrent) to T2(mass, length))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricConductivityAmount(amount = amount / that.amount / that.unit.scale,
                                   unit = T2(time, electricCurrent) to T2(mass, length))

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
                                   unit = T2(time, electricCurrent) to T2(mass, length))

    @JvmName("plusElectricConductivity")
    operator fun plus(that: ElectricConductivityAmount<T3, EC2, M, L2>) =
        ElectricConductivityAmount(amount = amount + that.amount,
                                   unit = T2(time, electricCurrent) to T2(mass, length))

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
                                   unit = T2(time, electricCurrent) to T2(mass, length))

    @JvmName("minusElectricConductivity")
    operator fun minus(that: ElectricConductivityAmount<T3, EC2, M, L2>) =
        ElectricConductivityAmount(amount = amount - that.amount,
                                   unit = T2(time, electricCurrent) to T2(mass, length))

    // endregion

    // region -> Natural

    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> div(that: ElectricConductivityAmount<T, EC, M, L>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent)
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length),
                     unit = NUMERIC)

    @JvmName("divElectricConductivity")
    operator fun div(that: ElectricConductivityAmount<T3, EC2, M, L2>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        ScalarAmount(amount = that.amount / amount
                              / convert(that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent)
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length),
                     unit = NUMERIC)

    @JvmName("timesElectricResistance")
    operator fun div(that: ElectricResistanceAmount<M, L2, T3, EC2>) =
        amount / that.amount

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
    operator fun times(that: ElectricTensionAmount<M, L2, T3, EC2>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion
}
