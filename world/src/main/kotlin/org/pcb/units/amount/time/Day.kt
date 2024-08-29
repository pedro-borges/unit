package org.pcb.units.amount.time

import org.pcb.units.Unit
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.time.Day.DAY
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Day(amount: Number)
    : TimeAmount<DAY>(amount, DAY)
{
    object DAY : Unit<Time>(Time::class)
    {
        override val symbol = "D"
        override val cannon = 86400.toBigBigDecimal()
    }
}