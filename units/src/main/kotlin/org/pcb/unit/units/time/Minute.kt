package org.pcb.unit.units.time

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.time.Minute.MINUTE

@Suppress("unused")
class Minute(amount: Number)
    : TimeAmount<MINUTE>(amount, MINUTE)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object MINUTE : Unit<Time>(Time::class)
    {
        override val symbol = "min"
        override val scale = 60.toBigBigDecimal()
    }
}
