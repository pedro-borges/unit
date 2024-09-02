package org.pcb.unit.amount.base

import org.pcb.unit.Unit
import org.pcb.unit.amount.TimeAmount
import org.pcb.unit.amount.base.Second.SECOND
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal

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
