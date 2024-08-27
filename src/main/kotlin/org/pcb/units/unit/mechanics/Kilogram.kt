package org.pcb.units.unit.mechanics

import org.pcb.units.domain.Mass
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.Unit

class Kilogram : Unit<Mass>(Mass::class)
{
    override val symbol = "Kg"
    override val cannon = 1.0.toBigDecimal()

    companion object
    {
        val KILOGRAM = Kilogram()
    }
}