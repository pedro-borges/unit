package org.pcb.unit.amount.frequency

import org.pcb.unit.Unit
import org.pcb.unit.amount.FrequencyAmount
import org.pcb.unit.amount.frequency.Hertz.HERTZ
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Hertz(amount: Number)
    : FrequencyAmount<HERTZ>(amount.toBigBigDecimal(), HERTZ)
{
    object HERTZ : Unit<Time>(Time::class)
    {
        override val symbol = "Hz"
        override val scale = 1.toBigBigDecimal()
    }
}