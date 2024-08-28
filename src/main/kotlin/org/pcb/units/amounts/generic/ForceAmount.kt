package org.pcb.units.amounts.generic

import org.pcb.units.domain.Force
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class ForceAmount<M : Unit<Mass>, S : Unit<Space>, T1 : Unit<Time>, T2 : Unit<Time>>(
        amount: Number,
        val mass: M,
        val space: S,
        val time1: T1,
        val time2: T2)
    : Amount<Force>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        units =
                        if (time1.cannon == time2.cannon)
                            mapOf(mass to 1,
                                  space to 1,
                                  time1 to -2)
                        else
                            mapOf(mass to 1,
                                  space to 1,
                                  time1 to -1,
                                  time2 to -1),
                        domain = Force::class))
{
    operator fun div(that: MassAmount<*>) =
        AccelerationAmount(amount = amount * mass.cannon / that.amount / that.unit.cannon,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    @JvmName("divM")
    operator fun div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    operator fun div(that: AccelerationAmount<*, *, *>) =
        MassAmount(amount = amount * space.cannon * time1.cannon * time2.cannon / that.amount / that.unit.cannon,
                   mass = mass)

    @JvmName("divST1T2")
    operator fun div(that: AccelerationAmount<S, T1, T2>) =
        MassAmount(amount = amount / that.amount,
                   mass = mass)

    @JvmName("divST2T1")
    operator fun div(that: AccelerationAmount<S, T2, T1>) =
        MassAmount(amount = amount / that.amount,
                   mass = mass)

    operator fun times(that: TimeAmount<*>) =
        MomentumAmount(amount = amount / time2.cannon * that.amount * that.unit.cannon,
                       mass = mass,
                       space = space,
                       time = time1)

    @JvmName("timesT1")
    operator fun times(that: TimeAmount<T1>) =
        MomentumAmount(amount = amount * that.amount,
                       mass = mass,
                       space = space,
                       time = time2)

    @JvmName("timesT2")
    operator fun times(that: TimeAmount<T2>) =
        MomentumAmount(amount = amount * that.amount,
                       mass = mass,
                       space = space,
                       time = time1)
}