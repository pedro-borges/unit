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
import org.pcb.units.toBigBigDecimal

open class EnergyAmount<M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>>(
        amount: Number,
        val mass: M,
        val space: S,
        val time: T)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(mass to 1,
                                       space to 2,
                                       time to -2)))
{
    // region Energy

    operator fun plus(that: EnergyAmount<*, *, *>) =
        EnergyAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                             that.space to space,
                                                             that.space to space,
                                                             time to that.time,
                                                             time to that.time),
                     mass = mass,
                     space = space,
                     time = time)

    @JvmName("plusEnergy")
    operator fun plus(that: EnergyAmount<M, S, T>) =
        EnergyAmount(amount = amount + that.amount,
                     mass = mass,
                     space = space,
                     time = time)


    operator fun minus(that: EnergyAmount<*, *, *>) =
        EnergyAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                             that.space to space,
                                                             that.space to space,
                                                             time to that.time,
                                                             time to that.time),
                     mass = mass,
                     space = space,
                     time = time)

    @JvmName("minusEnergy")
    operator fun minus(that: EnergyAmount<M, S, T>) =
        EnergyAmount(amount = amount - that.amount,
                     mass = mass,
                     space = space,
                     time = time)

    // endregion

    // region Scalar

    operator fun div(that: EnergyAmount<*, *, *>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                              that.space to space,
                                                              that.space to space,
                                                              time to that.time,
                                                              time to that.time))

    @JvmName("divEnergy")
    operator fun div(that: EnergyAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Force

    operator fun div(that: SpaceAmount<*>) =
        ForceAmount(amount = amount / that.amount / convert(that.space to space),
                    mass = mass,
                    space = space,
                    time = time)

    @JvmName("divSpace")
    operator fun div(that: SpaceAmount<S>) =
        ForceAmount(amount = amount / that.amount,
                    mass = mass,
                    space = space,
                    time = time)

    // endregion

    // region Space

    operator fun div(that: ForceAmount<*, *, *>) =
        SpaceAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                            that.space to space,
                                                            time to that.time,
                                                            time to that.time),
                    space = space)

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, S, T>) =
        SpaceAmount(amount = amount / that.amount,
                    space = space)

    // endregion
}