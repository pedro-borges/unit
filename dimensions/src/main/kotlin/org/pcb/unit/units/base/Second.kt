package org.pcb.unit.units.base

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount

class Second(amount: Number)
    : TimeAmount<Unit<Time>>(
    amount = amount,
    unit = SECOND)
{
    object SECOND : Unit<Time>(Time::class)
    {
        override val symbol = "s"
        override val scale = 1.toBigBigDecimal()
    }
}
