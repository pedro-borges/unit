package org.pcb.units.amount.cannon

import org.pcb.units.Unit
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Second(amount: Number)
    : TimeAmount<SECOND>(amount, SECOND)
{
    object SECOND : Unit<Time>(Time::class)
    {
        override val symbol = "s"
        override val scale = 1.toBigBigDecimal()
    }
}
