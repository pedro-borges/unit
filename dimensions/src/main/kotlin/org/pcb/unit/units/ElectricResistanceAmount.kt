package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.ElectricResistance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Gram.GRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric

open class ElectricResistanceAmount<M : Unit<Mass>, D2 : Unit<Distance>, T3 : Unit<Time>, EC2 : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<U2<M, D2>, U2<T3, EC2>>)
    : Amount<ElectricResistance>(amount = amount.toBigBigDecimal(),
                                 unit = CompositeUnit(
                                     domain = ElectricResistance::class,
                                     units = mapOf(unit.major.first to 1,
                                                   unit.major.second to 2,
                                                   unit.minor.first to -3,
                                                   unit.minor.second to -2)))
{
    val mass = unit.major.first
    val distance = unit.major.second
    val time = unit.minor.first
    val electricCurrent = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricResistance>) =
            ElectricResistanceAmount(amount = amount.toBigBigDecimal() * unit.scale,
                                     unit = U2(kilo(GRAM), METER) to U2(SECOND, AMPERE))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount * that.amount * that.unit.scale,
                                 unit = U2(mass, distance) to U2(time, electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount / that.amount / that.unit.scale,
                                 unit = U2(mass, distance) to U2(time, electricCurrent))

    @JvmName("plusElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: ElectricResistanceAmount<M, D, T, EC>) =
        ElectricResistanceAmount(amount = amount + that.amount
                                          * convert(that.mass to mass,
                                                    that.distance to distance,
                                                    that.distance to distance)
                                          / convert(that.time to time,
                                                    that.time to time,
                                                    that.time to time,
                                                    that.electricCurrent to electricCurrent,
                                                    that.electricCurrent to electricCurrent),
                                 unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun plus(that: ElectricResistanceAmount<M, D2, T3, EC2>) =
        ElectricResistanceAmount(amount = amount + that.amount,
                                 unit = U2(mass, distance) to U2(time, electricCurrent))

    @JvmName("minusElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: ElectricResistanceAmount<M, D, T, EC>) =
        ElectricResistanceAmount(amount = amount - that.amount
                                          * convert(that.mass to mass,
                                                    that.distance to distance,
                                                    that.distance to distance)
                                          / convert(that.time to time,
                                                    that.time to time,
                                                    that.time to time,
                                                    that.electricCurrent to electricCurrent,
                                                    that.electricCurrent to electricCurrent),
                                 unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun minus(that: ElectricResistanceAmount<M, D2, T3, EC2>) =
        ElectricResistanceAmount(amount = amount - that.amount,
                                 unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion

    // region -> Natural

    @JvmName("divElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, D, T, EC>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass,
                                   that.distance to distance,
                                   that.distance to distance)
                         * convert(that.time to time,
                                   that.time to time,
                                   that.time to time,
                                   that.electricCurrent to electricCurrent,
                                   that.electricCurrent to electricCurrent))

    operator fun div(that: ElectricResistanceAmount<M, D2, T3, EC2>) =
        Numeric(amount = amount / that.amount)

    @JvmName("timesElectricConductance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: ElectricConductanceAmount<T, EC, M, D>) =
        Numeric(amount = amount * that.amount
                         * convert(that.mass to mass,
                                   that.distance to distance,
                                   that.distance to distance)
                         / convert(that.time to time,
                                   that.time to time,
                                   that.time to time,
                                   that.electricCurrent to electricCurrent,
                                   that.electricCurrent to electricCurrent))

    operator fun times(that: ElectricConductanceAmount<T3, EC2, M, D2>) =
        Numeric(amount = amount * that.amount)

    // endregion

    // region -> ElectricTension

    @JvmName("timesElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount * that.amount * convert(that.electricCurrent to electricCurrent),
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun times(that: ElectricCurrentAmount<EC2>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion

    // region -> Time

    @JvmName("timesCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: CapacitanceAmount<T, EC, M, D>) =
        TimeAmount(amount = amount * that.amount
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent)
                            / convert(that.mass to mass,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = time)

    operator fun times(that: CapacitanceAmount<T3, EC2, M, D2>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    // endregion

    // region -> MagneticFlux

    @JvmName("timesElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        MagneticFluxAmount(amount = amount * that.amount * convert(that.time to time,
                                                                   that.electricCurrent to electricCurrent),
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun times(that: ElectricChargeAmount<EC2, T3>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion
}
