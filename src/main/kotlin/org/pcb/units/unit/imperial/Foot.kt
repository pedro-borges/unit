package org.pcb.units.unit.imperial

import org.pcb.units.domain.Space
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.Unit

class Foot : Unit<Space>(Space::class)
{
    override val symbol = "ft"
    override val cannon = 0.3048.toBigDecimal()

    companion object
    {
        val FOOT = Foot()
    }
}