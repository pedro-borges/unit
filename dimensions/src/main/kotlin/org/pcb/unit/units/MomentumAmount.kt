package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Momentum
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount

open class MomentumAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        val mass: M,
        val length: L,
        val time: T)
    : Amount<Momentum>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Momentum::class,
                           units = listOf(mass to 1,
                                          length to 1,
                                          time to -1)))
{
    constructor(amount: Number, pair: Pair<Tuple<M, L>, T>) : this(amount, pair.first.first, pair.first.second, pair.second)

    // region Momentum

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount * that.amount * that.unit.scale,
                       mass = mass,
                       length = length,
                       time = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount / that.amount / that.unit.scale,
                       mass = mass,
                       length = length,
                       time = time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount + that.amount
                                * convert(that.mass to mass,
                                          that.length to length)
                                / convert(that.time to time),
                       mass = mass,
                       length = length,
                       time = time)

    @JvmName("plusMomentum")
    operator fun plus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount + that.amount,
                       mass = mass,
                       length = length,
                       time = time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount - that.amount
                                * convert(that.mass to mass,
                                          that.length to length)
                                / convert(that.time to time),
                       mass = mass,
                       length = length,
                       time = time)

    @JvmName("minusMomentum")
    operator fun minus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount - that.amount,
                       mass = mass,
                       length = length,
                       time = time)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: MomentumAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.mass to mass,
                                         that.length to length)
                               * convert(that.time to time))

    @JvmName("divMomentum")
    operator fun div(that: MomentumAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Speed

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount / convert(that.mass to mass),
                    length = length,
                    time = time)

    @JvmName("divMass")
    operator fun div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount,
                    length = length,
                    time = time)

    // endregion

    // region Mass

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.length to length)
                            * convert(that.time to time),
                   major = mass)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        MassAmount(amount = amount / that.amount,
                   major = mass)

    // endregion

    // region Force

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount / convert(that.time to time),
                    major = mass * length,
                    minor = time * time)

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount,
                    major = mass * length,
                    minor = time * time)

    // endregion

    // region Energy

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.length to length)
                              / convert(that.time to time),
                     major = mass * length * length,
                     minor = time * time)

    @JvmName("timesSpeed")
    operator fun times(that: SpeedAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     major = mass * length * length,
                     minor = time * time)

    // endregion
}
