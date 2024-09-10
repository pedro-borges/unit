package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Force
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

open class ForceAmount<M : Unit<Mass>, D2: Unit<Distance>, T2 : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, D2>, T2>)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(unit.major.first to 1,
                                       unit.major.second to 1,
                                       unit.minor to -2)))
{
    val mass = unit.major.first
    val distance = unit.major.second
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
                    unit = U2(mass, distance) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ForceAmount(amount = amount / that.amount / that.unit.scale,
                    unit = U2(mass, distance) to time)

    @JvmName("plusForce")
    operator fun <M : Unit<Mass>, D: Unit<Distance>, T : Unit<Time>> plus(that: ForceAmount<M, D, T>) =
        ForceAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun plus(that: ForceAmount<M, D2, T2>) =
        ForceAmount(amount = amount + that.amount,
                    unit = U2(mass, distance) to time)

    @JvmName("minusForce")
    operator fun <M : Unit<Mass>, D: Unit<Distance>, T : Unit<Time>> minus(that: ForceAmount<M, D, T>) =
        ForceAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun minus(that: ForceAmount<M, D2, T2>) =
        ForceAmount(amount = amount - that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> Natural

    @JvmName("divForce")
    operator fun <M : Unit<Mass>, D: Unit<Distance>, T : Unit<Time>> div(that: ForceAmount<M, D, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.distance to distance)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ForceAmount<M, D2, T2>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.mass to mass),
                           unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> Mass

    @JvmName("divAcceleration")
    operator fun <D: Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = mass)

    operator fun div(that: AccelerationAmount<D2, T2>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> Momentum

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount * convert(that.time to time),
                       unit = U2(mass, distance) to time)

    operator fun times(that: TimeAmount<T2>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(mass, distance) to time)


    // endregion

    // region -> Energy

    @JvmName("timesDistance")
    operator fun <D: Unit<Distance>> times(that: DistanceAmount<D>) =
        EnergyAmount(amount = amount * that.amount * convert(that.distance to distance),
                     unit = U2(mass, distance) to time)

    operator fun times(that: DistanceAmount<D2>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, distance) to time)

    // endregion

    // region -> Power

    @JvmName("timesSpeed")
    operator fun <D: Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        PowerAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.time to time),
                     unit = U2(mass, distance) to time)

    operator fun times(that: SpeedAmount<D2, T2>) =
        PowerAmount(amount = amount * that.amount,
                     unit = U2(mass, distance) to time)

    // endregion
}