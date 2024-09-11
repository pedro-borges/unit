package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Inductance
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
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class InductanceAmount<M : Unit<Mass>, D2 : Unit<Distance>, T2 : Unit<Time>, EC2 : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<U2<M, D2>, U2<T2, EC2>>)
    : Amount<Inductance>(amount = amount.toBigBigDecimal(),
                         unit = CompositeUnit(
                             domain = Inductance::class,
                             units = mapOf(unit.major.first to 1,
                                           unit.major.second to 2,
                                           unit.minor.first to -2,
                                           unit.minor.second to -2)))
{
    val mass = unit.major.first
    val distance = unit.major.second
    val time = unit.minor.first
    val electricCurrent = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Inductance>) =
            InductanceAmount(amount = amount.toBigBigDecimal() * unit.scale,
                             unit = U2(kilo(GRAM), METER) to U2(SECOND, AMPERE))
    }

    // region -> Inductance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        InductanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                         unit = U2(mass, distance) to U2(time, electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        InductanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                         unit = U2(mass, distance) to U2(time, electricCurrent))

    @JvmName("plusInductance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: InductanceAmount<M, D, T, EC>) =
        InductanceAmount(amount = amount + that.amount
                                  * convert(that.mass to mass,
                                            that.distance to distance,
                                            that.distance to distance)
                                  / convert(that.time to time,
                                            that.time to time,
                                            that.electricCurrent to electricCurrent,
                                            that.electricCurrent to electricCurrent),
                         unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun plus(that: InductanceAmount<M, D2, T2, EC2>) =
        InductanceAmount(amount = amount + that.amount,
                         unit = U2(mass, distance) to U2(time, electricCurrent))

    @JvmName("minusInductance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: InductanceAmount<M, D, T, EC>) =
        InductanceAmount(amount = amount - that.amount
                                  * convert(that.mass to mass,
                                            that.distance to distance,
                                            that.distance to distance)
                                  / convert(that.time to time,
                                            that.time to time,
                                            that.electricCurrent to electricCurrent,
                                            that.electricCurrent to electricCurrent),
                         unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun minus(that: InductanceAmount<M, D2, T2, EC2>) =
        InductanceAmount(amount = amount - that.amount,
                         unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion

    // region -> Natural

    @JvmName("divInductance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: InductanceAmount<M, D, T, EC>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass,
                                   that.distance to distance,
                                   that.distance to distance)
                         * convert(that.time to time,
                                   that.time to time,
                                   that.electricCurrent to electricCurrent,
                                   that.electricCurrent to electricCurrent))

    operator fun div(that: InductanceAmount<M, D2, T2, EC2>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> MagneticFlux

    @JvmName("timesElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        MagneticFluxAmount(amount = amount * that.amount * convert(that.electricCurrent to electricCurrent),
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun times(that: ElectricCurrentAmount<EC2>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion
}
