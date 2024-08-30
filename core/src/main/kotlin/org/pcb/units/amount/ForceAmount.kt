package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Force
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.or
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

open class ForceAmount<M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>>(
        amount: Number,
        val mass: M,
        val space: S,
        val time1: T,
        val time2: T)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(mass to 1,
                                       space to 1,
                                       time1 to -1,
                                       time2 to -1)))
{
    // region Force

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ForceAmount(amount = amount * that.amount * that.unit.scale,
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ForceAmount(amount = amount / that.amount / that.unit.scale,
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> plus(that: ForceAmount<M, S, T>) =
        ForceAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                            that.space to space,
                                                            time1 to that.time1,
                                                            time2 to that.time2),
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    @JvmName("plusForce")
    operator fun plus(that: ForceAmount<M, S, T>) =
        ForceAmount(amount = amount + that.amount,
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> minus(that: ForceAmount<M, S, T>) =
        ForceAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                            that.space to space,
                                                            time1 to that.time1,
                                                            time2 to that.time2),
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    @JvmName("minusForce")
    operator fun minus(that: ForceAmount<M, S, T>) =
        ForceAmount(amount = amount - that.amount,
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> div(that: ForceAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                              that.space to space,
                                                              time1 to that.time1,
                                                              time2 to that.time2))

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Acceleration

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.mass to mass),
                           space = space,
                           time1 = time1,
                           time2 = time2)

    @JvmName("divMass")
    operator fun div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    // endregion

    // region Mass

    operator fun <S : Unit<Space>, T : Unit<Time>> div(that: AccelerationAmount<S, T>) =
        MassAmount(amount = amount / that.amount / convert(that.space to space,
                                                           time1 to that.time1,
                                                           time2 to that.time2),
                   mass = mass)

    @JvmName("divAcceleration")
    operator fun div(that: AccelerationAmount<S, T>) =
        MassAmount(amount = amount / that.amount,
                   mass = mass)

    // endregion

    // region Momentum

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount * convert(that.time to (time1 or time2)),
                       mass = mass,
                       space = space,
                       time = time1 or time2)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount,
                       mass = mass,
                       space = space,
                       time = time1 or time2)


    // endregion

    // region Energy

    operator fun <S : Unit<Space>> times(that: SpaceAmount<S>) =
        EnergyAmount(amount = amount * that.amount * convert(that.space to space),
                     mass = mass,
                     space1 = space,
                     space2 = space,
                     time1 = time1,
                     time2 = time2)

    @JvmName("timesSpace")
    operator fun times(that: SpaceAmount<S>) =
        EnergyAmount(amount = amount * that.amount,
                     mass = mass,
                     space1 = space,
                     space2 = space,
                     time1 = time1,
                     time2 = time2)

    // endregion
}