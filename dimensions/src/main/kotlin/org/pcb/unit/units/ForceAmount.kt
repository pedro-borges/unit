package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Force
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class ForceAmount<M : Unit<Mass>, L2 : Unit<Length>, T2 : Unit<Time>>(
        amount: Number,
        unit: Pair<org.pcb.unit.U2<M, L2>, T2>)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(unit.major.first to 1,
                                       unit.major.second to 1,
                                       unit.minor to -2)))
{
    val mass = unit.major.first
    val length = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Force>) =
            ForceAmount(amount = amount.toBigBigDecimal() * unit.scale,
                        unit = U2(KILOGRAM, METER) to SECOND)
    }

    // region -> Force

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ForceAmount(amount = amount * that.amount * that.unit.scale,
                    unit = U2(mass, length) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ForceAmount(amount = amount / that.amount / that.unit.scale,
                    unit = U2(mass, length) to time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: ForceAmount<M, L, T>) =
        ForceAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(mass, length) to time)

    @JvmName("plusForce")
    operator fun plus(that: ForceAmount<M, L2, T2>) =
        ForceAmount(amount = amount + that.amount,
                    unit = U2(mass, length) to time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: ForceAmount<M, L, T>) =
        ForceAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(mass, length) to time)

    @JvmName("minusForce")
    operator fun minus(that: ForceAmount<M, L2, T2>) =
        ForceAmount(amount = amount - that.amount,
                    unit = U2(mass, length) to time)

    // endregion

    // region -> Natural

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: ForceAmount<M, L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, L2, T2>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.mass to mass),
                           unit = length to time)

    @JvmName("divMass")
    operator fun div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = length to time)

    // endregion

    // region -> Mass

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.length to length)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = mass)

    @JvmName("divAcceleration")
    operator fun div(that: AccelerationAmount<L2, T2>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> Momentum

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount * convert(that.time to time),
                       unit = U2(mass, length) to time)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T2>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(mass, length) to time)


    // endregion

    // region -> Energy

    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        EnergyAmount(amount = amount * that.amount * convert(that.length to length),
                     unit = U2(mass, length) to time)

    @JvmName("timesLength")
    operator fun times(that: LengthAmount<L2>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, length) to time)

    // endregion
}