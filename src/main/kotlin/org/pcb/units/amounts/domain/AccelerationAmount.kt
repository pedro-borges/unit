package org.pcb.units.amounts.domain

import org.pcb.units.domain.Acceleration
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class AccelerationAmount(amount: Number,
                              val space: Unit<Space>,
                              val time1: Unit<Time>,
                              val time2: Unit<Time>)
    : Amount<Acceleration>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               units = mapOf(space to 1,
                                             time1 to -1,
                                             time2 to -1),
                               domain = Acceleration::class))
{
    operator fun times(that: TimeAmount) =
        SpeedAmount(amount = this.amount / this.time2.cannon * that.amount * that.unit.cannon,
                    space = space,
                    time = time1)

    operator fun times(that: MassAmount) =
        ForceAmount(amount = this.amount * that.amount,
                    space = space,
                    time1 = time1,
                    time2 = time2,
                    mass = that.unit)
}