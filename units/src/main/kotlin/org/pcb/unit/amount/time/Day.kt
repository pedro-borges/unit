package org.pcb.unit.amount.time

import org.pcb.unit.Unit
import org.pcb.unit.amount.TimeAmount
import org.pcb.unit.amount.time.Day.DAY
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Day(amount: Number)
    : TimeAmount<DAY>(amount, DAY)
{
    object DAY : Unit<Time>(Time::class)
    {
        override val symbol = "D"
        override val scale = 86400.toBigBigDecimal()
    }
}