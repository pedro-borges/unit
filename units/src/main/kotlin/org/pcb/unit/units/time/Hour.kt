package org.pcb.unit.units.time

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount

class Hour(amount: Number)
    : TimeAmount<Unit<Time>>(
    amount = amount,
    unit = HOUR)
{
    object HOUR : Unit<Time>(Time::class)
    {
        override val symbol = "h"
        override val scale = 3600.toBigBigDecimal()
    }
}