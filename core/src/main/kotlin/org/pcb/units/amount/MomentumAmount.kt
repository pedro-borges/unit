package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Momentum
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class MomentumAmount<M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>>(
        amount: Number,
        val mass: M,
        val space: S,
        val time: T)
    : Amount<Momentum>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Momentum::class,
                           units = listOf(mass to 1,
                                          space to 1,
                                          time to -1)))
{
    // region Momentum

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount * that.amount * that.unit.scale,
                       mass = mass,
                       space = space,
                       time = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount / that.amount / that.unit.scale,
                       mass = mass,
                       space = space,
                       time = time)

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> plus(that: MomentumAmount<M, S, T>) =
        MomentumAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                               that.space to space,
                                                               time to that.time),
                       mass = mass,
                       space = space,
                       time = time)

    @JvmName("plusMomentum")
    operator fun plus(that: MomentumAmount<M, S, T>) =
        MomentumAmount(amount = amount + that.amount,
                       mass = mass,
                       space = space,
                       time = time)

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> minus(that: MomentumAmount<M, S, T>) =
        MomentumAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                               that.space to space,
                                                               time to that.time),
                       mass = mass,
                       space = space,
                       time = time)

    @JvmName("minusMomentum")
    operator fun minus(that: MomentumAmount<M, S, T>) =
        MomentumAmount(amount = amount - that.amount,
                       mass = mass,
                       space = space,
                       time = time)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> div(that: MomentumAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                              that.space to space,
                                                              time to that.time))

    @JvmName("divMomentum")
    operator fun div(that: MomentumAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Speed

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount / convert(that.mass to mass),
                    space = space,
                    time = time)

    @JvmName("divMass")
    operator fun div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount,
                    space = space,
                    time = time)

    // endregion

    // region Mass

    operator fun <S : Unit<Space>, T : Unit<Time>> div(that: SpeedAmount<S, T>) =
        MassAmount(amount = amount / that.amount / convert(that.space to space,
                                                           time to that.time),
                   mass = mass)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<S, T>) =
        MassAmount(amount = amount / that.amount,
                   mass = mass)

    // endregion

    // region Force

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount / convert(that.time to time),
                    mass = mass,
                    space = space,
                    time1 = time,
                    time2 = time)

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount,
                    mass = mass,
                    space = space,
                    time1 = time,
                    time2 = time)

    // endregion
}
