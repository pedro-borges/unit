package org.pcb.units.amounts.domain

import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class TimeAmount(amount: Number,
                      unit: Unit<Time>)
    : Amount<Time>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       unit = unit,
                       domain = Time::class))
{

}