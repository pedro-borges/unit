package org.pcb.unit.units.time

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount

class Day(amount: Number)
    : TimeAmount<Unit<Time>>(
    amount = amount,
    unit = DAY)
{
    object DAY : Unit<Time>(Time::class, "D", 86400.toBigBigDecimal())
}