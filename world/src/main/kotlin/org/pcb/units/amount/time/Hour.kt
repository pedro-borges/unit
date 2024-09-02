package org.pcb.units.amount.time

import org.pcb.units.Unit
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.time.Hour.HOUR
import org.pcb.units.dimension.Time
import org.pcb.units.toBigBigDecimal

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