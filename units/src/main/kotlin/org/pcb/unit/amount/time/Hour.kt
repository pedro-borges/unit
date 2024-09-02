package org.pcb.unit.amount.time

import org.pcb.unit.Unit
import org.pcb.unit.amount.TimeAmount
import org.pcb.unit.amount.time.Hour.HOUR
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Hour(amount: Number)
    : TimeAmount<HOUR>(amount, HOUR)
{
    object HOUR : Unit<Time>(Time::class)
    {
        override val symbol = "h"
        override val scale = 3600.toBigBigDecimal()
    }
}