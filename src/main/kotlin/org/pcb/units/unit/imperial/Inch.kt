package org.pcb.units.unit.imperial

import org.pcb.units.domain.Space
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.Unit

class Inch : Unit<Space>(Space::class)
{
    override val symbol = "\""
    override val cannon = 0.0254.toBigDecimal()

    companion object
    {
        val INCHES = Inch()
    }
}