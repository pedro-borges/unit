package org.pcb.units.amounts.generic

import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class TimeAmount<T : Unit<Time>>(
        amount: Number,
        val time: T)
    : Amount<Time>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       unit = time,
                       domain = Time::class))
{
    operator fun div(that: SpaceAmount<*>) =
        PaceAmount(amount = amount / that.amount,
                   time = time,
                   space = that.space)
}
