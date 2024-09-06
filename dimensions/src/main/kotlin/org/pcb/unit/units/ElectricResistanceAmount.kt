package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.T2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.ElectricResistance
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.to
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

open class ElectricResistanceAmount<M : Unit<Mass>, L2 : Unit<Length>, T3 : Unit<Time>, EC2 : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<T2<M, L2>, T2<T3, EC2>>)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(unit.major.first to 1,
                                                 unit.major.second to 2,
                                                 unit.minor.first to -3,
                                                 unit.minor.second to -2)))
{
    val mass = unit.major.first
    val length = unit.major.second
    val time = unit.minor.first
    val electricCurrent = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricResistance>) =
            ElectricResistanceAmount(amount = amount.toBigBigDecimal() * unit.scale,
                                     unit = Pair(T2(KILOGRAM, METER),
                                                 T2(SECOND, AMPERE)))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount * that.amount * that.unit.scale,
                                 unit = Pair(T2(mass, length),
                                             T2(time, electricCurrent)))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount.to(that.amount).to(that.unit.scale),
                                 unit = Pair(T2(mass, length),
                                             T2(time, electricCurrent)))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricResistanceAmount(amount = amount + (that.amount
                                                    * convert(that.mass to mass,
                                                              that.length to length,
                                                              that.length to length)
                                                   ).to(convert(that.time to time,
                                                                that.time to time,
                                                                that.time to time,
                                                                that.electricCurrent to electricCurrent,
                                                                that.electricCurrent to electricCurrent)),
                                 unit = Pair(T2(mass, length),
                                             T2(time, electricCurrent)))

    @JvmName("plusElectricResistance")
    operator fun plus(that: ElectricResistanceAmount<M, L2, T3, EC2>) =
        ElectricResistanceAmount(amount = amount + that.amount,
                                 unit = Pair(T2(mass, length),
                                             T2(time, electricCurrent)))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricResistanceAmount(amount = amount - (that.amount
                                                    * convert(that.mass to mass,
                                                              that.length to length,
                                                              that.length to length)
                                                   ).to(convert(that.time to time,
                                                                that.time to time,
                                                                that.time to time,
                                                                that.electricCurrent to electricCurrent,
                                                                that.electricCurrent to electricCurrent)),
                                 unit = Pair(T2(mass, length),
                                             T2(time, electricCurrent)))

    @JvmName("minusElectricResistance")
    operator fun minus(that: ElectricResistanceAmount<M, L2, T3, EC2>) =
        ElectricResistanceAmount(amount = amount - that.amount,
                                 unit = Pair(T2(mass, length),
                                             T2(time, electricCurrent)))

    // endregion

    // region -> Natural

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, L, T, EC>) =
        amount.to(that.amount).to(convert(that.mass to mass,
                                          that.length to length,
                                          that.length to length)) *
        convert(that.time to time,
                that.time to time,
                that.time to time,
                that.electricCurrent to electricCurrent,
                that.electricCurrent to electricCurrent)

    @JvmName("divElectricResistance")
    operator fun div(that: ElectricResistanceAmount<M, L2, T3, EC2>) =
        amount.to(that.amount)

    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> times(that: ElectricConductivityAmount<T, EC, M, L>) =
        (amount.to(that.amount) *
         convert(that.time to time,
                 that.time to time,
                 that.time to time,
                 that.electricCurrent to electricCurrent,
                 that.electricCurrent to electricCurrent)).to(convert(that.mass to mass,
                                                                      that.length to length,
                                                                      that.length to length))

    @JvmName("timesElectricResistance")
    operator fun times(that: ElectricConductivityAmount<T3, EC2, M, L2>) =
        amount.to(that.amount)

    // endregion

    // region -> ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount * that.amount * convert(that.electricCurrent to electricCurrent),
                              unit = Pair(T2(mass, length),
                                          T2(time, electricCurrent)))

    @JvmName("timesElectricCurrent")
    operator fun times(that: ElectricCurrentAmount<EC2>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = Pair(T2(mass, length),
                                          T2(time, electricCurrent)))

    // endregion

    // region -> Time

    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> times(that: ElectricCapacitanceAmount<T, EC, M, L>) =
        TimeAmount(amount = (amount * that.amount
                             * convert(that.time to time,
                                       that.time to time,
                                       that.time to time,
                                       that.time to time,
                                       that.electricCurrent to electricCurrent,
                                       that.electricCurrent to electricCurrent)
                            ).to(convert(that.mass to mass,
                                         that.length to length,
                                         that.length to length)),
                   unit = time)

    @JvmName("timesElectricCapacitance")
    operator fun times(that: ElectricCapacitanceAmount<T3, EC2, M, L2>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    // endregion

    // region -> MagneticFlux

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        MagneticFluxAmount(amount = amount * that.amount * convert(that.time to time,
                                                                   that.electricCurrent to electricCurrent),
                           unit = Pair(T2(mass, length),
                                       T2(time, electricCurrent)))

    @JvmName("timesElectricCharge")
    operator fun times(that: ElectricChargeAmount<EC2, T3>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = Pair(T2(mass, length),
                                       T2(time, electricCurrent)))

    // endregion
}
