package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Force
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount

open class ForceAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<Tuple<M, L>, Tuple<T, T>>)
    : Amount<Force>(amount = amount.toBigBigDecimal() / convert(unit.second.second to unit.second.first),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(unit.first.first to 1,
                                       unit.first.second to 1,
                                       unit.second.first to -2)))
{
    val mass = unit.first.first
    val length = unit.first.second
    val time = unit.second.first

    companion object
    {
        operator fun invoke(amount: Number, force: Unit<Force>) =
            ForceAmount(amount = amount.toBigBigDecimal() * force.scale,
                        unit = (KILOGRAM * METER) / (SECOND * SECOND))
    }

    // region Force

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ForceAmount(amount = amount * that.amount * that.unit.scale,
                    unit = (mass * length) / (time * time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ForceAmount(amount = amount / that.amount / that.unit.scale,
                    unit = (mass * length) / (time * time))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: ForceAmount<M, L, T>) =
        ForceAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = (mass * length) / (time * time))

    @JvmName("plusForce")
    operator fun plus(that: ForceAmount<M, L, T>) =
        ForceAmount(amount = amount + that.amount,
                    unit = (mass * length) / (time * time))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: ForceAmount<M, L, T>) =
        ForceAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = (mass * length) / (time * time))

    @JvmName("minusForce")
    operator fun minus(that: ForceAmount<M, L, T>) =
        ForceAmount(amount = amount - that.amount,
                    unit = (mass * length) / (time * time))

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: ForceAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.mass to mass,
                                         that.length to length)
                               * convert(that.time to time,
                                         that.time to time))

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Acceleration

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.mass to mass),
                           unit = length / (time * time))

    @JvmName("divMass")
    operator fun div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = length / (time * time))

    // endregion

    // region Mass

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.length to length)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = mass)

    @JvmName("divAcceleration")
    operator fun div(that: AccelerationAmount<L, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region Momentum

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount * convert(that.time to time),
                       unit = (mass * length) / time)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = (mass * length) / time)


    // endregion

    // region Energy

    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        EnergyAmount(amount = amount * that.amount * convert(that.length to length),
                     unit = (mass * length * length) / (time * time))

    @JvmName("timesLength")
    operator fun times(that: LengthAmount<L>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = (mass * length * length) / (time * time))

    // endregion
}