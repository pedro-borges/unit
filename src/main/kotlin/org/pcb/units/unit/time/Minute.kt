package org.pcb.units.unit.time

import org.pcb.units.domain.Time
import org.pcb.units.unit.Unit

class Minute : Unit<Time>(Time::class)
{
    override val symbol = "min"
    override val cannon = 60.0.toBigDecimal()

    companion object
    {
        val MINUTE = Minute()
    }
}