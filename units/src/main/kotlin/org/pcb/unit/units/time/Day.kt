package org.pcb.unit.units.time

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.time.Day.DAY

@Suppress("unused")
class Day(amount: Number)
    : TimeAmount<DAY>(amount, DAY)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object DAY : Unit<Time>(Time::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "D"
        override val scale = 86400.toBigBigDecimal()
    }
}