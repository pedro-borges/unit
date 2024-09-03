package org.pcb.unit.units.time

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.time.Hour.HOUR

@Suppress("unused")
class Hour(amount: Number)
    : TimeAmount<HOUR>(amount, HOUR)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object HOUR : Unit<Time>(Time::class)
    {
        override val symbol = "h"
        override val scale = 3600.toBigBigDecimal()
    }
}