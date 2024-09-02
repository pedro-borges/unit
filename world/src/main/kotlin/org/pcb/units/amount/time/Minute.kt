package org.pcb.units.amount.time

import org.pcb.units.Unit
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.dimension.Time
import org.pcb.units.toBigBigDecimal

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
