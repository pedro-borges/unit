package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Momentum
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
                                          time to 1)))
{
    // region Momentum

    operator fun plus(that: MomentumAmount<*, *, *>) =
        MomentumAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                               that.space to space,
                                                               that.time to time),
                       mass = mass,
                       space = space,
                       time = time)

    @JvmName("plusMST")
    operator fun plus(that: MomentumAmount<M, S, T>) =
        MomentumAmount(amount = amount + that.amount,
                       mass = mass,
                       space = space,
                       time = time)

    operator fun minus(that: MomentumAmount<*, *, *>) =
        MomentumAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                               that.space to space,
                                                               that.time to time),
                       mass = mass,
                       space = space,
                       time = time)

    @JvmName("minusMST")
    operator fun minus(that: MomentumAmount<M, S, T>) =
        MomentumAmount(amount = amount - that.amount,
                       mass = mass,
                       space = space,
                       time = time)

    // endregion

    // region Scalar

    operator fun div(that: MomentumAmount<*, *, *>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                             that.space to space,
                                                             that.time to time))

    @JvmName("divMST")
    operator fun div(that: MomentumAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Speed

    operator fun div(that: MassAmount<*>) =
        SpeedAmount(amount = amount / that.amount / convert(that.mass to mass),
                    space = space,
                    time = time)

    @JvmName("divM")
    operator fun div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount,
                    space = space,
                    time = time)

    // endregion

    operator fun div(that: SpeedAmount<*, *>) =
        MassAmount(amount = amount / that.amount / convert(that.space to space,
                                                           that.time to time),
                   mass = mass)

    @JvmName("divST")
    operator fun div(that: SpeedAmount<S, T>) =
        MassAmount(amount = amount / that.amount,
                   mass = mass)

    operator fun div(that: TimeAmount<*>) =
        ForceAmount(amount = amount / that.amount,
                    mass = mass,
                    space = space,
                    time1 = time,
                    time2 = that.time)
}
