package org.pcb.units.amounts.generic

import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class TimeAmount(amount: Number,
                      val time: Unit<Time>)
    : Amount<Time>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       unit = time,
                       domain = Time::class))
{
    operator fun div(that: SpaceAmount) =
        PaceAmount(amount = amount / that.amount,
                   time = time,
                   space = that.space)
}
