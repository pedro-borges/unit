package org.pcb.unit.amount.time

import org.pcb.unit.Unit
import org.pcb.unit.amount.TimeAmount
import org.pcb.unit.amount.time.Minute.MINUTE
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Minute(amount: Number)
    : TimeAmount<MINUTE>(amount, MINUTE)
{
    object MINUTE : Unit<Time>(Time::class)
    {
        override val symbol = "min"
        override val scale = 60.toBigBigDecimal()
    }
}
