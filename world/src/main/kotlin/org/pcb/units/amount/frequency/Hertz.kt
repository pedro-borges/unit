package org.pcb.units.amount.frequency

import org.pcb.units.Unit
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.frequency.Hertz.HERTZ
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Hertz(amount: Number)
    : FrequencyAmount<HERTZ>(amount.toBigBigDecimal(), HERTZ)
{
    object HERTZ : Unit<Time>(Time::class)
    {
        override val symbol = "Hz"
        override val cannon = 1.toBigBigDecimal()
    }
}