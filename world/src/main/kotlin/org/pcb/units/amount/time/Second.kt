package org.pcb.units.amount.time

import org.pcb.units.Unit
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.time.Second.SECOND
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Second(amount: Number)
    : TimeAmount<SECOND>(amount, SECOND)
{
    object SECOND : Unit<Time>(Time::class)
    {
        override val symbol = "s"
        override val cannon = 1.toBigBigDecimal()
    }
}
