package org.pcb.unit.units.frequency

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.frequency.Hertz.HERTZ

@Suppress("unused")
class Hertz(amount: Number)
    : FrequencyAmount<HERTZ>(amount.toBigBigDecimal(), HERTZ)
{
    object HERTZ : Unit<Time>(Time::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "Hz"
        override val scale = 1.toBigBigDecimal()
    }
}