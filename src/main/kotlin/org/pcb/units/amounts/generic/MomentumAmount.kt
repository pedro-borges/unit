package org.pcb.units.amounts.generic

import org.pcb.units.domain.Mass
import org.pcb.units.domain.Momentum
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class MomentumAmount<M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>>(
        amount: Number,
        val mass: M,
        val space: S,
        val time: T)
    : Amount<Momentum>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           units = mapOf(mass to 1,
                                         space to 1,
                                         time to 1),
                           domain = Momentum::class))
{
    operator fun div(that: MassAmount<*>) =
        SpeedAmount(amount = amount * mass.cannon / that.amount / that.unit.cannon,
                    space = space,
                    time = time)

    @JvmName("divM")
    operator fun div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount,
                    space = space,
                    time = time)

    operator fun div(that: SpeedAmount<*, *>) =
        MassAmount(amount = amount * space.cannon / that.amount / that.unit.cannon,
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
                    time2 = that.unit)
}
