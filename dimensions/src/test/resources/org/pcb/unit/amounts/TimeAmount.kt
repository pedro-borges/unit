package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal

open class TimeAmount<T : Unit<Time>>(
        amount: Number,
        unit: T)
    : Amount<Time>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Time::class,
                       units = mapOf(unit to 1)))
{
    val time = unit

    $operators
}
