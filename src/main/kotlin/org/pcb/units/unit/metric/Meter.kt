package org.pcb.units.unit.metric

import org.pcb.units.domain.Space
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.Unit

class Meter : Unit<Space>(Space::class)
{
    override val symbol = "m"
    override val cannon = 1.0.toBigDecimal()

    companion object
    {
        val METER = Meter()
    }
}