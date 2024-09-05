package org.pcb.unit.units.time

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount

class Minute(amount: Number)
    : TimeAmount<Unit<Time>>(
    amount = amount,
    unit = MINUTE)
{
    object MINUTE : Unit<Time>(Time::class)
    {
        override val symbol = "min"
        override val scale = 60.toBigBigDecimal()
    }
}
