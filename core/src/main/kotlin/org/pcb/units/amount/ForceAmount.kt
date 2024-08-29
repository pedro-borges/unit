package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Force
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

open class ForceAmount<M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>>(
        amount: Number,
        val mass: M,
        val space: S,
        val time: T)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(mass to 1,
                                       space to 1,
                                       time to -2)))
{
    // region Force

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> plus(that: ForceAmount<M, S, T>) =
        ForceAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                            that.space to space,
                                                            time to that.time,
                                                            time to that.time),
                    mass = mass,
                    space = space,
                    time = time)

    @JvmName("plusForce")
    operator fun plus(that: ForceAmount<M, S, T>) =
        ForceAmount(amount = amount + that.amount,
                    mass = mass,
                    space = space,
                    time = time)

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> minus(that: ForceAmount<M, S, T>) =
        ForceAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                            that.space to space,
                                                            time to that.time,
                                                            time to that.time),
                    mass = mass,
                    space = space,
                    time = time)

    @JvmName("minusForce")
    operator fun minus(that: ForceAmount<M, S, T>) =
        ForceAmount(amount = amount - that.amount,
                    mass = mass,
                    space = space,
                    time = time)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> div(that: ForceAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                              that.space to space,
                                                              time to that.time,
                                                              time to that.time))

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Acceleration

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.mass to mass),
                           space = space,
                           time = time)

    @JvmName("divMass")
    operator fun div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount,
                           space = space,
                           time = time)

    // endregion

    // region Mass

    operator fun <S : Unit<Space>, T : Unit<Time>> div(that: AccelerationAmount<S, T>) =
        MassAmount(amount = amount / that.amount / convert(that.space to space,
                                                           time to that.time,
                                                           time to that.time),
                   mass = mass)

    @JvmName("divAcceleration")
    operator fun div(that: AccelerationAmount<S, T>) =
        MassAmount(amount = amount / that.amount,
                   mass = mass)

    // endregion

    // region Momentum

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount * convert(that.time to time),
                       mass = mass,
                       space = space,
                       time = time)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount,
                       mass = mass,
                       space = space,
                       time = time)

    // endregion

    // region Energy

    operator fun <S : Unit<Space>> times(that: SpaceAmount<S>) =
        EnergyAmount(amount = amount * that.amount * convert(that.space to space),
                     mass = mass,
                     space = space,
                     time = time)

    @JvmName("timesSpace")
    operator fun times(that: SpaceAmount<S>) =
        EnergyAmount(amount = amount * that.amount,
                     mass = mass,
                     space = space,
                     time = time)

    // endregion
}