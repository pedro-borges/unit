package org.pcb.unit.units.time

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.time.Hour.HOUR

class Hour(amount: Number)
    : TimeAmount<HOUR>(amount = amount,
                       unit = HOUR)
{
    object HOUR : Unit<Time>(Time::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
        override val symbol = "h"
        override val scale = 3600.toBigBigDecimal()
    }
}