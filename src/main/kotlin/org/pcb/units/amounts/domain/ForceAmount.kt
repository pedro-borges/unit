package org.pcb.units.amounts.domain

import org.pcb.units.domain.Acceleration
import org.pcb.units.domain.Force
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class ForceAmount(amount: Number,
                       val space: Unit<Space>,
                       val mass: Unit<Mass>,
                       val time1: Unit<Time>,
                       val time2: Unit<Time>)
    : Amount<Force>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        units = mapOf(space to 1,
                                      mass to 1,
                                      time1 to -1,
                                      time2 to -1),
                        domain = Force::class))
{
    operator fun div(that: MassAmount) =
        AccelerationAmount(amount = amount * mass.cannon / that.amount / that.unit.cannon,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    operator fun div(that: AccelerationAmount) =
        MassAmount(amount = amount * space.cannon * time1.cannon * time2.cannon / that.amount / that.unit.cannon,
                   mass = mass)
}