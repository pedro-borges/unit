package org.pcb.units.unit.metric

import org.pcb.units.domain.Time
import org.pcb.units.unit.Unit

class Minute : Unit<Time>(Time::class)
{
    override val symbol = "m"
    override val cannon = 60.toBigDecimal()

    companion object
    {
        val MINUTES = Minute()
    }
}