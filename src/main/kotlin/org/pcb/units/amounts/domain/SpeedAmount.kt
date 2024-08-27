package org.pcb.units.amounts.domain

import org.pcb.units.domain.Space
import org.pcb.units.domain.Speed
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class SpeedAmount(amount: Number,
                       val space: Unit<Space>,
                       val time: Unit<Time>)
    : Amount<Speed>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        units = mapOf(space to 1,
                                      time to -1),
                        domain = Speed::class))
{
    operator fun times(that: TimeAmount) =
        SpaceAmount(amount = this.amount / this.time.cannon * that.amount * that.unit.cannon,
                    space = space)

    operator fun div(that: TimeAmount) =
        AccelerationAmount(amount = this.amount / that.amount,
                           space = space,
                           time1 = time,
                           time2 = that.unit)
}